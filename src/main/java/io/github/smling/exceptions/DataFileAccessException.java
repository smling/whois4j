package io.github.smling.exceptions;

/**
 * Exception for accessing data file.
 */
public class DataFileAccessException extends RuntimeException {
    /**
     * Exception for accessing data file.
     * @param message Message.
     * @param exception Inner exception.
     */
    public DataFileAccessException(String message, Exception exception) {
        super(message, exception);
    }
}
