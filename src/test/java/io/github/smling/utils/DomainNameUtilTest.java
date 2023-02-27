package io.github.smling.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DomainNameUtilTest {

    @ParameterizedTest
    @MethodSource("mockDomainNameSuffixHappyCase")
    void getDomainNameSuffix(String value, String expectedResult) {
        assertDoesNotThrow(()-> assertEquals(expectedResult, DomainNameUtil.INSTANCE.getDomainNameSuffix(value)));
    }

    @ParameterizedTest
    @MethodSource("mockDomainNameByUrlHappyCase")
    void getDomainNameByUrl(URL value, String expectedResult) {
        assertDoesNotThrow(()-> assertEquals(expectedResult, DomainNameUtil.INSTANCE.getDomainNameByUrl(value)));
    }

    static Stream<Arguments> mockDomainNameSuffixHappyCase() {
        return Stream.of(
                Arguments.of("google.com", "com"),
                Arguments.of("gov.uk", "uk"),
                Arguments.of("yahoo.com.hk", "hk"),
                Arguments.of("apple", ""),
                Arguments.of("", ""),
                Arguments.of(null, "")
        );
    }

    static Stream<Arguments> mockDomainNameByUrlHappyCase() throws MalformedURLException {
        return Stream.of(
                Arguments.of(new URL("http://google.com"), "google.com"),
                Arguments.of(new URL("https://www.google.com"), "google.com"),
                Arguments.of(new URL("https://gov.uk"), "gov.uk"),
                Arguments.of(new URL("ftp://yahoo.com.hk"), "yahoo.com.hk"),
                Arguments.of(new URL("http://192.168.0.1"), "192.168.0.1"),
                Arguments.of(new URL("http://192.168.0.1:8964"), "192.168.0.1"),
                Arguments.of(null, "")
        );
    }
}