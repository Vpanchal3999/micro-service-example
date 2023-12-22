package com.synoverge.authservice.model.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "auth")
public class Auth {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="token")
    private String token;

    @Column(name="expire_time")
    private Timestamp expireTime;

    @Column(name="isActive")
    private Boolean isActiveToken;

    public Auth() {
    }

    public Auth(String token, Timestamp expireTime, Boolean isActiveToken) {
        this.token = token;
        this.expireTime = expireTime;
        this.isActiveToken = isActiveToken;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Timestamp expireTime) {
        this.expireTime = expireTime;
    }

    public Boolean getActiveToken() {
        return isActiveToken;
    }

    public void setActiveToken(Boolean activeToken) {
        isActiveToken = activeToken;
    }
}
