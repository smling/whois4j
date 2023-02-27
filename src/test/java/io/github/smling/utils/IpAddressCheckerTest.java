package io.github.smling.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class IpAddressCheckerTest {

    @ParameterizedTest
    @MethodSource("mockIsIpV4AddressHappyCases")
    void isIPv4Address(String value, boolean expectedResult) {
        assertDoesNotThrow(()-> assertEquals(expectedResult, IpAddressChecker.INSTANCE.isIPv4Address(value)));
    }

    static Stream<Arguments> mockIsIpV4AddressHappyCases() {
        return Stream.of(
                Arguments.of("192.169.0.1", true),
                Arguments.of("0.0.0.0", true),
                Arguments.of("255.255.255.255", true),
                Arguments.of("google.com", false),
                Arguments.of("", false),
                Arguments.of(" ", false),
                Arguments.of(null, false)
        );
    }

    @ParameterizedTest
    @MethodSource("mockGetIpv4AddressesFromDomainNameHappyCases")
    void getIpv4AddressesFromDomainNameHappyCases(String domainName) {
        assertDoesNotThrow(()->{
            List<String> result = IpAddressChecker.INSTANCE.getIpv4AddressesFromDomainName(domainName);
            assertNotNull(result, "Result not null. value: " + result);
            assertTrue(result.size() > 0, "Result not empty.");
            System.out.println("Domain name "+domainName+" IP address lookup response: "+result);
        });
    }

    static Stream<Arguments> mockGetIpv4AddressesFromDomainNameHappyCases() {
        return Stream.of(
                Arguments.of("google.com"),
                Arguments.of("lidi.co.uk"),
                Arguments.of("gov.uk"),
                Arguments.of("yahoo.com.hk"),
                Arguments.of("google.co.az"),
                Arguments.of("github.com")
        );
    }

    @ParameterizedTest
    @MethodSource("mockIsInRangeIpv4HappyCase")
    void isInRangeIpv4HappyCase(Inet4Address target, Inet4Address start, Inet4Address end, boolean expectedResult) {
        assertDoesNotThrow(()-> assertEquals(expectedResult, IpAddressChecker.INSTANCE.isInRange(target, start, end)));
    }

    static Stream<Arguments> mockIsInRangeIpv4HappyCase() throws UnknownHostException {
        return Stream.of(
                Arguments.of(
                        InetAddress.getByName("1.0.0.3"),
                        InetAddress.getByName("1.0.0.0"),
                        InetAddress.getByName("1.0.0.255"),
                        true
                ),
                Arguments.of(
                        InetAddress.getByName("10.2.0.3"),
                        InetAddress.getByName("10.0.0.0"),
                        InetAddress.getByName("10.0.0.255"),
                        true
                ),
                Arguments.of(
                        InetAddress.getByName("192.168.0.3"),
                        InetAddress.getByName("192.168.0.0"),
                        InetAddress.getByName("192.168.1.255"),
                        true
                ),
                Arguments.of(
                        InetAddress.getByName("1.0.0.3"),
                        InetAddress.getByName("1.0.0.5"),
                        InetAddress.getByName("1.0.0.255"),
                        false
                ),
                Arguments.of(
                        InetAddress.getByName("1.0.0.3"),
                        null,
                        InetAddress.getByName("1.0.0.255"),
                        true
                ),
                Arguments.of(
                        InetAddress.getByName("179.61.184.10"),
                        InetAddress.getByName("179.61.184.0"),
                        InetAddress.getByName("179.61.184.255"),
                        true
                ),
                Arguments.of(
                        InetAddress.getByName("1.0.0.3"),
                        InetAddress.getByName("1.0.0.0"),
                        null,
                        true
                )
        );
    }
}