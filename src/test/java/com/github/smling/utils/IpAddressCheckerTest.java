package com.github.smling.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
}