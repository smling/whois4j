package com.github.smling.geolocation.providers.ip2asn;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class IpToAsnProviderTest {
    @Test
    void canBeCreated() {
        assertDoesNotThrow(()->{
            new IpToAsnProvider();
        });
    }

    @ParameterizedTest
    @MethodSource("mockGetCountryCodeHappyCase")
    void getCountryCodeHappyCase(String domainName, int expectedResult) {
        assertDoesNotThrow(()-> {
            IpToAsnProvider ipToAsnProvider = new IpToAsnProvider();
            List<String> countriesCode = ipToAsnProvider.getCountryCode(domainName);
            assertNotNull(countriesCode, "Return country code not null. value: "+countriesCode);
            assertEquals(expectedResult, countriesCode.size(), "At least one country code returned");
            System.out.println("lookup country code for domain "+domainName+": "+countriesCode);
        });
    }

    static Stream<Arguments> mockGetCountryCodeHappyCase() {
        return Stream.of(
                Arguments.of("google.com", 1),
                Arguments.of("lidi.co.uk", 1),
                Arguments.of("gov.uk", 1),
                Arguments.of("yahoo.com.hk", 2),
                Arguments.of("google.co.az", 1),
                Arguments.of("github.com", 1),
                Arguments.of("", 1),
                Arguments.of(null, 1),
                Arguments.of("hostinghk.com", 1),
                Arguments.of("hksar.gov.hk", 0)
        );
    }
}