package com.github.smling.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DomainNameLookupExceptionTest {
    @Test
    void canBeCreateWithMessage() {
        assertThrowsExactly(DomainNameLookupException.class, ()-> {
            throw new DomainNameLookupException("This is test exception.");
        });
    }

    @Test
    void canBeCreateWithMessageAndInnerException() {
        assertThrowsExactly(DomainNameLookupException.class, ()-> {
            throw new DomainNameLookupException("This is test exception", new RuntimeException());
        });
    }
}