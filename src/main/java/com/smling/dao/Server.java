package com.smling.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
/*
  Data Access Object (DAO) from server.json.
  Store the mapping between suffix, whois lookup server and server settings.
 */
public class Server {
    /**
     * Domain name suffix.
     */
    @JsonProperty("suffix")
    private String suffix;
    /**
     * Corresponding Whois server.
     */
    @JsonProperty("lookupHost")
    private String lookupHost;
    /**
     * Whois server specific query.
     */
    @JsonProperty("query")
    private String query;
    /**
     * Whois server option to
     */
    @JsonProperty("punyCode")
    private boolean punyCode;
}
