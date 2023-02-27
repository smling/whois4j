package io.github.smling.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeoLocationLookupExceptionTest {
    @Test
    void canBeCreateWithMessage() {
        assertThrowsExactly(GeoLocationLookupException.class, ()-> {
            throw new GeoLocationLookupException("This is test exception.");
        });
    }

    @Test
    void canBeCreateWithMessageAndInnerException() {
        assertThrowsExactly(GeoLocationLookupException.class, ()-> {
            throw new GeoLocationLookupException("This is test exception", new RuntimeException());
        });
    }
}