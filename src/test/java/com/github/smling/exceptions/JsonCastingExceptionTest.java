package com.github.smling.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonCastingExceptionTest {
    @Test
    void canBeCreateWithInnerException() {
        assertDoesNotThrow(()-> {
            new JsonCastingException(new RuntimeException());
        });
    }
}