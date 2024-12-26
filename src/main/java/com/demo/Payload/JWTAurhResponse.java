package com.demo.Payload;

public class JWTAurhResponse {

    private String token;
    private String tokenType = "Bearer";  // Default token type

    public JWTAurhResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}


