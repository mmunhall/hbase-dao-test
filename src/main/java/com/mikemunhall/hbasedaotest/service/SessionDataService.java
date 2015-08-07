package com.mikemunhall.hbasedaotest.service;

import com.mikemunhall.hbasedaotest.dao.SessionDataDao;
import com.mikemunhall.hbasedaotest.domain.SessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class SessionDataService {

    @Autowired
    private SessionDataDao dao;

    public SessionDataService() { }

    public SessionData saveSession(Map<String, String> params) {
        SessionData sd = new SessionData();
        sd.setSessionId(params.get("sessionId"));
        sd.setIdentity(params.get("identity"));
        sd.setPlatform(params.get("platform"));
        sd.setProviderId(params.get("providerId"));
        sd.setDatetime(new Date());

        dao.save(sd);

        return sd;
    }


}
