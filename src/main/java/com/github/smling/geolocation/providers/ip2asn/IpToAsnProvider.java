package com.github.smling.geolocation.providers.ip2asn;

import com.github.smling.exceptions.DataFileAccessException;
import com.github.smling.exceptions.GeoLocationLookupException;
import com.github.smling.geolocation.providers.ip2asn.dao.Ipv4ToAsn;
import com.github.smling.utils.IpAddressChecker;
import com.github.smling.utils.StringUtil;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class IpToAsnProvider {
    private final Log logger = LogFactory.getLog(IpToAsnProvider.class);
    private final List<Ipv4ToAsn> ipv4ToAsnList = new ArrayList<>();
    public IpToAsnProvider() {
        this(IpAddressChecker.class.getResourceAsStream("/ip2asn/ip2asn-v4.tsv"));
    }


    public IpToAsnProvider(InputStream inputStream) {
            Reader inputSteamReader = new InputStreamReader(inputStream);
            CSVParser csvParser = new CSVParserBuilder()
                    .withSeparator('\t')
                    .build();
             try(CSVReader reader = new CSVReaderBuilder(inputSteamReader).withCSVParser(csvParser)
                     .build()) {
                 for (String[] item : reader) {
                     Ipv4ToAsn ipv4ToAsn = Ipv4ToAsn.builder()
                             .startRange(InetAddress.getByName(item[0]))
                             .endRange(InetAddress.getByName(item[1]))
                             .asNumber(item[2])
                             .countryCode(item[3])
                             .asDescription(item[4])
                             .build();
                     ipv4ToAsnList.add(ipv4ToAsn);
                 }
             } catch(IOException exception) {
                 throw new DataFileAccessException("Error occurred during reading IP2ASN file.", exception);
             }
    }

    public List<String> getCountryCode(String domainName) {
        List<String> result = new ArrayList<>();
        List<String> ipAddresses = IpAddressChecker.INSTANCE.getIpv4AddressesFromDomainName(domainName);
        logger.debug(domainName+" lookup IP address: "+ipAddresses);
        if(ipAddresses.isEmpty()) {
            return result;
        }
        try {

            for(String o : ipAddresses){
                InetAddress targetIpAddress = InetAddress.getByName(o);
                result.add(ipv4ToAsnList.stream().filter(p->IpAddressChecker.INSTANCE.isInRange(targetIpAddress, p.getStartRange(), p.getEndRange()))
                        .map(Ipv4ToAsn::getCountryCode)
                        .findFirst()
                        .orElse(StringUtil.EMPTY_STRING)
                );
            }
        } catch (UnknownHostException e) {
            throw new GeoLocationLookupException("Error occurred when lookup target IP address.", e);
        }
        return result.stream().distinct().toList();
    }
}
