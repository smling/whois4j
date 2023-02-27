package io.github.smling.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StringUtilTest {

    @ParameterizedTest
    @MethodSource("mockIsNullOrBlankHappyCase")
    void isNullOrBlank(String value, boolean expectedResult) {
        assertDoesNotThrow(()-> assertEquals(expectedResult, StringUtil.INSTANCE.isNullOrBlank(value)));
    }

    @ParameterizedTest
    @MethodSource("mockIsNullOrEmptyHappyCase")
    void isNullOrEmpty(String value, boolean expectedResult) {
        assertDoesNotThrow(()-> assertEquals(expectedResult, StringUtil.INSTANCE.isNullOrEmpty(value)));
    }

    static Stream<Arguments> mockIsNullOrBlankHappyCase() {
        return Stream.of(
                Arguments.of("value", false),
                Arguments.of("", true),
                Arguments.of(" ", true),
                Arguments.of(null, true)
        );
    }

    static Stream<Arguments> mockIsNullOrEmptyHappyCase() {
        return Stream.of(
                Arguments.of("value", false),
                Arguments.of("", true),
                Arguments.of(" ", false),
                Arguments.of(null, true)
        );
    }
}