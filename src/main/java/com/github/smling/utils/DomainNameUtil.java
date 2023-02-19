package com.github.smling.utils;

import java.net.URL;
import java.util.Objects;

/**
 * Utility class for domain name.
 */
public enum DomainNameUtil {
    /**
     * Utility instance.
     */
    INSTANCE;

    private static final String WWW = "www.";

    /**
     * Get domain name suffix.
     * @param domainName Domain name.
     * @return Domain name suffix or empty string if not found.
     */
    public String getDomainNameSuffix(String domainName) {
        if(StringUtil.INSTANCE.isNullOrBlank(domainName)) {
            return StringUtil.EMPTY_STRING;
        }
        int index = domainName.lastIndexOf(".");
        if(index < 0) {
            return StringUtil.EMPTY_STRING;
        }
        return domainName.substring(index+1);
    }

    /**
     * Get domain name by URL.
     * @param url URL
     * @return domain name or empty string if not found.
     */
    public String getDomainNameByUrl(URL url) {
        if(Objects.isNull(url)) {
            return StringUtil.EMPTY_STRING;
        }
        String domain = url.getHost();
        if (domain.startsWith(WWW)) {
            domain = domain.substring(WWW.length());
        }
        return domain;
    }
}
