package com.github.smling.exceptions;

/**
 * Exception throw when lookup geolocation.
 */
public class GeoLocationLookupException extends RuntimeException {
    /**
     * Exception throw when lookup geolocation.
     * @param message Message.
     */
    public GeoLocationLookupException(String message) {
        super(message);
    }
    /**
     * Exception throw when lookup geolocation.
     * @param message Message.
     * @param exception Inner exception.
     */
    public GeoLocationLookupException(String message, Exception exception) {
        super(message, exception);
    }
}
