package com.github.smling.utils;

import com.github.smling.exceptions.GeoLocationLookupException;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Utility class to check IP address.
 */
public enum IpAddressChecker {
    /**
     * Utility instance.
     */
    INSTANCE;
    private static final String REGULAR_EXPRESSION_0_TO_255 = "([01]?\\d\\d?|2[0-4]\\d|25[0-5])";
    private static final Pattern IPV4_REGULAR_EXPRESSION_PATTERN = Pattern
            .compile("^"+REGULAR_EXPRESSION_0_TO_255+"\\." +
                    REGULAR_EXPRESSION_0_TO_255+"\\." +
                    REGULAR_EXPRESSION_0_TO_255+"\\." +
                    REGULAR_EXPRESSION_0_TO_255+"$");

    /**
     * Check value is IPv4 address or not.
     * @param value value to be checked.
     * @return Boolean to indicate value is valid IPv4 or not.
     */
    public boolean isIPv4Address(String value) {
        if(StringUtil.INSTANCE.isNullOrBlank(value)) {
            return false;
        }
        return IPV4_REGULAR_EXPRESSION_PATTERN.matcher(value).matches();
    }

    /**
     * Get IPv4 addresses from domain name.
     * @param domainName Domain name to be checked.
     * @return IP addresses found or empty list if domain name not found.
     */
    public List<String> getIpv4AddressesFromDomainName(String domainName) {
        List<String> ipAddresses = new ArrayList<>();
        try {
            InetAddress[] addresses = InetAddress.getAllByName(domainName);
            for (InetAddress address : addresses) {
                if(address instanceof Inet4Address) {
                    ipAddresses.add(address.getHostAddress());
                }
            }
        } catch (UnknownHostException ex) {
            return new ArrayList<>();
        }
        return ipAddresses;
    }

    /**
     * Check IP address is in range.
     * @param targetIpAddress IPv4 address to be checked.
     * @param start Start IPv4 address.
     * @param end End IPv4 address.
     * @return Boolean to indicate it is in range.
     */
    public boolean isInRange(InetAddress targetIpAddress, InetAddress start, InetAddress end) {
        try {
            start = Objects.isNull(start) ? InetAddress.getByName("0.0.0.0") : start;
            end = Objects.isNull(end) ? InetAddress.getByName("255.255.255.255") : end;
        } catch (UnknownHostException exception) {
            throw new GeoLocationLookupException("Error occurred when checking IP address is in range.");
        }
        byte[] targetIpAddressByte = targetIpAddress.getAddress();
        byte[] startByte = start.getAddress();
        byte[] endByte = end.getAddress();

        boolean inRange = true;
        for (int i = 0; i < targetIpAddressByte.length; i++) {
            if ((targetIpAddressByte[i] & startByte[i]) != (startByte[i] & endByte[i])) {
                inRange = false;
                break;
            }
        }
        return inRange;
    }
}
