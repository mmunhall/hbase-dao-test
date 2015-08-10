package com.mikemunhall.hbasedaotest.controller;

import com.mikemunhall.hbasedaotest.domain.SessionData;
import com.mikemunhall.hbasedaotest.service.SessionDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/session")
public class SessionController {

    @Autowired
    private SessionDataService service;

    @RequestMapping(value="/addOne", method=RequestMethod.POST)
    public SessionData postSession(@RequestParam Map<String, String> params) {
        String sessionId = params.get("sessionId");
        String identity = params.get("identity");
        String platform = params.get("platform");
        String providerId = params.get("providerId");

        SessionData sd = service.saveSession(sessionId, identity, platform, providerId);

        return sd;
    }

    @RequestMapping(value="/addMany", method=RequestMethod.POST)
    public int postSessions(@RequestParam Map<String, String> params) {
        String sessionIdPrefix = params.get("sessionIdPrefix");
        int start = Integer.parseInt(params.get("start"));
        int end = Integer.parseInt(params.get("end"));
        String identity = params.get("identity");
        String platform = params.get("platform");
        String providerId = params.get("providerId");

        int count = service.saveSessions(sessionIdPrefix, start, end, identity, providerId, platform);

        return count;
    }

    @RequestMapping(value="/getOne", method=RequestMethod.GET)
    public SessionData getSession(@RequestParam("sessionId") String sessionId) {
        return service.getSession(sessionId);
    }

    @RequestMapping(value="/getAll", method=RequestMethod.GET)
    public List<SessionData> getSessions() {
        return service.getSessions();
    }

    @RequestMapping(value="/recreateTable", method=RequestMethod.GET)
    public void recreateTable() throws IOException {
        service.recreateTable();
    }

}
