package com.github.smling;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.smling.dao.Server;
import com.github.smling.exceptions.DomainNameLookupException;
import com.github.smling.exceptions.JsonCastingException;
import com.github.smling.utils.DomainNameUtil;
import com.github.smling.utils.IpAddressChecker;
import com.github.smling.utils.StringUtil;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Whois Lookup client.
 */
public class WhoIsClient {
    /**
     * Default Whois Lookup host.
     */
    protected static final String DEFAULT_WHOIS_LOOKUP_HOST = "whois.iana.org";
    /**
     * Default Whois Lookup port.
     */
    protected static final int DEFAULT_WHOIS_LOOKUP_PORT = 43;
    /**
     * Default Whois Lookup query.
     */
    protected static final String DEFAULT_WHOIS_LOOKUP_QUERY = "$addr\r\n";
    /**
     * Default Whois server connection timeout.
     */
    protected static final int DEFAULT_WHOIS_LOOKUP_SOCKET_TIMEOUT = 5000;
    /**
     * Default Whois server connection TCP delay enable or not.
     */
    protected static final boolean DEFAULT_WHOIS_LOOKUP_SOCKET_TCP_NO_DELAY = true;
    /**
     * Deserialize Server lists from servers.json
     */
    protected final List<Server> servers;

    /**
     * Create WhoisClient with default settings.
     * @throws JsonCastingException Throw when error from deserialize servers.json.
     */
    public WhoIsClient() throws JsonCastingException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            InputStream fileInputStream = WhoIsClient.class.getResourceAsStream("/servers.json");
            Server[] serverArray = objectMapper.readValue(fileInputStream, Server[].class);
            servers = Arrays.asList(serverArray);
            if(Objects.nonNull(fileInputStream)) {
                fileInputStream.close();
            }
        } catch(IOException exception) {
            throw new JsonCastingException(exception);
        }
    }

    /**
     * Whois lookup domain name.
     * @param url URL to be lookup.
     * @return Lookup(domainName);
     */
    public String lookup(URL url) {
        String domainName = DomainNameUtil.INSTANCE.getDomainNameByUrl(url);
        if(IpAddressChecker.INSTANCE.isIPv4Address(domainName)) {
            throw new DomainNameLookupException("Domain name could be be IP address.");
        }
        return lookup(domainName);
    }

    /**
     * Whois lookup domain name.
     * @param domainName Domain name to be lookup.
     * @return Lookup response in String.
     */
    public String lookup(String domainName) {
        String domainNameSuffix = DomainNameUtil.INSTANCE.getDomainNameSuffix(domainName);
        Server lookupServer = servers.stream()
                .filter(o->domainNameSuffix.equals(o.getSuffix()))
                .findFirst()
                .orElseThrow(()->new DomainNameLookupException(String.format("Domain name %s could not be lookup.", domainNameSuffix)));
        return this.lookup(domainName, lookupServer);
    }

    /**
     * Whois lookup domain name.
     * @param domainName Domain name to be lookup.
     * @param server Whois server to be connected.
     * @return Lookup response in String.
     */
    private String lookup(String domainName, Server server) {
        if(StringUtil.INSTANCE.isNullOrBlank(domainName)) {
            throw new DomainNameLookupException("domainName could not be null, empty or blank.");
        }
        String lookupServerHostName = getWhoisLookupHost(server);
        String result;
        try(Socket lookupServerSocket = new Socket(lookupServerHostName, DEFAULT_WHOIS_LOOKUP_PORT)) {
            lookupServerSocket.setSoTimeout(DEFAULT_WHOIS_LOOKUP_SOCKET_TIMEOUT);
            lookupServerSocket.setTcpNoDelay(DEFAULT_WHOIS_LOOKUP_SOCKET_TCP_NO_DELAY);
            String query = getWhoisLookupQuery(domainName, server);
            OutputStream outputStream = lookupServerSocket.getOutputStream();
            byte[] queryByte = query.getBytes(StandardCharsets.ISO_8859_1);
            outputStream.write(queryByte);
            BufferedReader reader = new BufferedReader(new InputStreamReader(lookupServerSocket.getInputStream()));
            StringBuilder response = new StringBuilder(StringUtil.EMPTY_STRING);
            String line;
            while((line = reader.readLine()) != null) {
                response.append(line)
                        .append("\n");

            }
            result = response.toString();
        } catch (IOException exception) {
            throw new DomainNameLookupException("Error occurred when create socket connection.", exception);
        }
        return result;
    }

    private String getWhoisLookupHost(Server lookupServer) {
        return StringUtil.INSTANCE.isNullOrBlank(lookupServer.getLookupHost()) ?
                DEFAULT_WHOIS_LOOKUP_HOST :
                lookupServer.getLookupHost()
                ;
    }
    private String getWhoisLookupQuery(String domainName, Server lookupServer) {
        String query = StringUtil.INSTANCE.isNullOrBlank(lookupServer.getQuery()) ?
                DEFAULT_WHOIS_LOOKUP_QUERY :
                lookupServer.getQuery();
        return query.replace("$addr", domainName);

    }
}
