package com.example.springsecurityoauthendcodingbug.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IntrospectResponse {

    @JsonProperty("active")
    private boolean active;
    @JsonProperty("iss")
    private String iss;
    @JsonProperty("jti")
    private String jti;
    @JsonProperty("sub")
    private String sub;
    @JsonProperty("iat")
    private long iat;
    @JsonProperty("exp")
    private long exp;
    @JsonProperty("client_id")
    private String clientId;
    @JsonProperty("scope")
    private String scope;

    public IntrospectResponse(
            boolean active,
            String iss,
            String jti,
            String sub,
            long iat,
            long exp,
            String clientId,
            String scope
    ) {
        this.active = active;
        this.iss = iss;
        this.jti = jti;
        this.sub = sub;
        this.iat = iat;
        this.exp = exp;
        this.clientId = clientId;
        this.scope = scope;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public long getIat() {
        return iat;
    }

    public void setIat(long iat) {
        this.iat = iat;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
