package com.github.smling.utils;

import java.util.Objects;

/**
 * Utility class for String related function/
 */
public enum StringUtil {
    /**
     * Utility instance.
     */
    INSTANCE;

    /**
     * Check value is Null or string only have space.
     * @param value Check value.
     * @return Boolean to indicate it is null or string which only have space.
     */
    public boolean isNullOrBlank(String value) {
        if(Objects.isNull(value)) {
            return true;
        }
        return isNullOrEmpty(value.replace(" ", EMPTY_STRING));
    }

    /**
     * Check value is Null or empty string.
     * @param value Check value.
     * @return Boolean to indicate it is null or empty string.
     */
    public boolean isNullOrEmpty(String value) {
        if(Objects.isNull(value)) {
            return true;
        }
        return value.length() == 0;
    }

    /**
     * Centralised property to define empty string.
     */
    public static final String EMPTY_STRING = "";
}
