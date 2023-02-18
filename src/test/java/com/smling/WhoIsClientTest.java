package com.smling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class WhoIsClientTest {
    @Test
    @DisplayName("Constructor test.")
    void canBeCreate() {
        assertDoesNotThrow(() -> {
            new WhoIsClient();
        });
    }

    @ParameterizedTest
    @MethodSource("mockLookUpHappyCases")
    void lookupHappyCase(String domainName) {
        assertDoesNotThrow(()->{
            WhoIsClient whoIsClient = new WhoIsClient();
            String result = whoIsClient.lookup(domainName);
            assertNotNull(result, "whois response:\n" +
                    result);
            assertTrue(result.length() > 0);
            System.out.println("whois lookup response: \n"+result);
        });
    }

    static Stream<Arguments> mockLookUpHappyCases() {
        return Stream.of(
                Arguments.of("google.com"),
                Arguments.of("lidi.co.uk"),
                Arguments.of("gov.uk"),
                Arguments.of("yahoo.com.hk")
        );
    }
}