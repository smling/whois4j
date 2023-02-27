package io.github.smling.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataFileAccessExceptionTest {
    @Test
    void canBeCreateWithMessageAndInnerException() {
        assertThrowsExactly(DataFileAccessException.class, ()-> {
            throw new DataFileAccessException("This is test exception", new RuntimeException());
        });
    }
}