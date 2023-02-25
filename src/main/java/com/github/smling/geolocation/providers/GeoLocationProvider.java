package com.github.smling.geolocation.providers;

import java.util.List;

/**
 * Interface of Geo location provider
 */
public interface GeoLocationProvider {
    /**
     * Get country codes.
     * @param domainName domain name.
     * @return List of lookup country code.
     */
    List<String> getCountryCodes(String domainName);
}
