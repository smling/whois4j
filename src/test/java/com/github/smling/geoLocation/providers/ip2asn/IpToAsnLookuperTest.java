package com.github.smling.geoLocation.providers.ip2asn;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class IpToAsnLookuperTest {
    @Test
    void canBeCreated() {
        assertDoesNotThrow(()->{
            new IpToAsnLookuper();
        });
    }

    @ParameterizedTest
    @MethodSource("mockGetCountryCodeHappyCase")
    void getCountryCode(String domainName) {
        assertDoesNotThrow(()-> {
            IpToAsnLookuper ipToAsnLookuper = new IpToAsnLookuper();
            List<String> countriesCode = ipToAsnLookuper.getCountryCode(domainName);
            assertNotNull(countriesCode, "Return country code not null. value: "+countriesCode);
            assertTrue(countriesCode.size() > 0, "At least one country code returned");
            System.out.println("lookup country code: "+countriesCode);
        });
    }

    static Stream<Arguments> mockGetCountryCodeHappyCase() {
        return Stream.of(
                Arguments.of("google.com"),
                Arguments.of("lidi.co.uk"),
                Arguments.of("gov.uk"),
                Arguments.of("yahoo.com.hk"),
                Arguments.of("google.co.az"),
                Arguments.of("github.com")
        );
    }
}