package com.mikemunhall.hbasedaotest.service;

import com.mikemunhall.hbasedaotest.dao.SessionDataDao;
import com.mikemunhall.hbasedaotest.domain.SessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SessionDataService {

    @Autowired
    private SessionDataDao dao;

    public SessionDataService() { }

    public SessionData saveSession(String sessionId, String identity, String platform, String providerId) {
        SessionData sd = new SessionData();
        sd.setSessionId(sessionId);
        sd.setIdentity(identity);
        sd.setPlatform(platform);
        sd.setProviderId(providerId);

        dao.save(sd);

        return sd;
    }

    public int saveSessions(String sessionId, int start, int end, String identity, String platform, String providerId) {
        int count = 0;

        // The naive way to do this is to iterate from start to end, creating a new Put for each object.
        // TODO: Save in batches. Possibly spawn multiple threads.
        for (int i=start; i<=end; i++) {
            SessionData sd = new SessionData();
            sd.setSessionId(sessionId + String.format("%020d", i));
            sd.setIdentity(identity);
            sd.setPlatform(platform);
            sd.setProviderId(providerId);
            dao.save(sd);

            count++;
        }

        return count;
    }

    public SessionData getSession(String sessionId) {
        return dao.findOne(sessionId);
    }

    public List<SessionData> getSessions() {
        return dao.findAll();
    }

    public void recreateTable() throws IOException {
        dao.drop();
        dao.init();
    }
}
