package com.synoverge.authservice.model.dto;

public class JwtRequest {
    private String userName;
    private String password;

//    private Authentication authentication;

    public JwtRequest() {
    }

    public JwtRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
//        this.authentication = authentication;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public Authentication getAuthentication() {
//        return authentication;
//    }
//
//    public void setAuthentication(Authentication authentication) {
//        this.authentication = authentication;
//    }
}
