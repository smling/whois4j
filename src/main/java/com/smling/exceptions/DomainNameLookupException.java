package com.smling.exceptions;

/**
 * Exception for error occurred during lookup domain name.
 */
public class DomainNameLookupException extends RuntimeException {
    /**
     * Exception for error occurred during lookup domain name.
     * @param message Custom exception message.
     */
    public DomainNameLookupException(String message) {
        super(message);
    }

    /**
     * Exception for error occurred during lookup domain name.
     * @param message Custom exception message.
     * @param exception Inner exception.
     */
    public DomainNameLookupException(String message, Exception exception) {
        super(message, exception);
    }
}
