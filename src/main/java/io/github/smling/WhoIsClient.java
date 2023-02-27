package io.github.smling;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.smling.dao.Server;
import io.github.smling.exceptions.DomainNameLookupException;
import io.github.smling.exceptions.JsonCastingException;
import io.github.smling.utils.DomainNameUtil;
import io.github.smling.utils.IpAddressChecker;
import io.github.smling.utils.StringUtil;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
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

    public static final SocketOption DEFAULT_SOCKET_OPTION = SocketOption.builder()
            .connectionTimeout(3000)
            .idleTimeout(3000)
            .tcpNoDelay(true)
            .keepAlive(false)
            .build();
    /**
     * Deserialize Server lists from servers.json
     */
    protected final List<Server> servers;

    /**
     * Create WhoisClient with default settings.
     * @throws JsonCastingException Throw when error from deserialize servers.json.
     */
    public WhoIsClient() throws JsonCastingException {
        this("/servers.json");
    }

    private WhoIsClient(String lookupServerFilePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            InputStream fileInputStream = WhoIsClient.class.getResourceAsStream(lookupServerFilePath);
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
        return this.lookup(domainName, DEFAULT_SOCKET_OPTION);
    }

    public String lookup(String domainName, SocketOption socketOption) {
        if(Objects.isNull(socketOption)) {
            socketOption = DEFAULT_SOCKET_OPTION;
        }
        String domainNameSuffix = DomainNameUtil.INSTANCE.getDomainNameSuffix(domainName);
        Server lookupServer = servers.stream()
                .filter(o->domainNameSuffix.equals(o.getSuffix()))
                .findFirst()
                .orElseThrow(()->new DomainNameLookupException(String.format("Domain name %s could not be lookup.", domainNameSuffix)));
        return this.lookup(domainName, socketOption, lookupServer);
    }

    /**
     * Whois lookup domain name.
     * @param domainName Domain name to be lookup.
     * @param socketOption Socket option.
     * @param server Whois server to be connected.
     * @return Lookup response in String.
     */
    private String lookup(String domainName, SocketOption socketOption, Server server) {
        if(StringUtil.INSTANCE.isNullOrBlank(domainName)) {
            throw new DomainNameLookupException("domainName could not be null, empty or blank.");
        }
        String lookupServerHostName = getWhoisLookupHost(server);
        String result;
        SocketAddress socketAddress = new InetSocketAddress(lookupServerHostName, DEFAULT_WHOIS_LOOKUP_PORT);
        try(Socket lookupServerSocket = new Socket()) {
            lookupServerSocket.setSoTimeout(socketOption.getIdleTimeout());
            lookupServerSocket.setTcpNoDelay(socketOption.isTcpNoDelay());
            lookupServerSocket.setKeepAlive(socketOption.isKeepAlive());
            lookupServerSocket.connect(socketAddress, socketOption.getConnectionTimeout());
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
