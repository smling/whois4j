package io.github.smling.geolocation.providers.ip2asn.dao;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.net.InetAddress;

@Getter
@Setter
@Builder
public class Ipv4ToAsn {

    private InetAddress startRange;
    private InetAddress endRange;
    private String asNumber;
    private String countryCode;
    private String asDescription;
}
