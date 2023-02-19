package com.github.smling.utils;

import java.util.regex.Pattern;

public enum IpAddressChecker {
    INSTANCE;
    private static final String REGULAR_EXPRESSION_0_TO_255 = "([01]?\\d\\d?|2[0-4]\\d|25[0-5])";
    private static final Pattern IPV4_REGULAR_EXPRESSION_PATTERN = Pattern
            .compile("^"+REGULAR_EXPRESSION_0_TO_255+"\\." +
                    REGULAR_EXPRESSION_0_TO_255+"\\." +
                    REGULAR_EXPRESSION_0_TO_255+"\\." +
                    REGULAR_EXPRESSION_0_TO_255+"$");
    public boolean isIPv4Address(String value) {
        if(StringUtil.INSTANCE.isNullOrBlank(value)) {
            return false;
        }
        return IPV4_REGULAR_EXPRESSION_PATTERN.matcher(value).matches();
    }
}
