package com.github.smling.exceptions;

/**
 * Exception for Casting Json to DAO or DTO.
 */
public class JsonCastingException extends RuntimeException{
    /**
     * Exception for Casting Json to DAO or DTO.
     * @param exception Inner exception.
     */
    public JsonCastingException(Exception exception) {
        super("Error occurred when deserializing JSON to DAO. ", exception);
    }
}
