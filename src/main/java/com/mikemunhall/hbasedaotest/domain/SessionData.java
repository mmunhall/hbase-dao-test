package com.mikemunhall.hbasedaotest.domain;

import java.util.Date;

public class SessionData {

    private String sessionId;
    private String identity;
    private String providerId;
    private String platform;
    private Date datetime;

    public SessionData() { }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getIdentity() {
        return identity;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPlatform() {
        return platform;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Date getDatetime() {
        return datetime;
    }
}
