package com.mikemunhall.hbasedaotest.controller;

import com.mikemunhall.hbasedaotest.domain.SessionData;
import com.mikemunhall.hbasedaotest.service.SessionDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/session")
public class SessionController {

    @Autowired
    private SessionDataService service;

    @RequestMapping(method=RequestMethod.GET)
    public SessionData getSessions() {
        SessionData sd = new SessionData();
        sd.setSessionId("session1");
        sd.setIdentity("identity1");
        sd.setPlatform("Comcast");
        sd.setProviderId("nbcu");
        sd.setDatetime(new Date());

        return sd;
    }

    @RequestMapping(method=RequestMethod.POST)
    public SessionData postSession(@RequestParam Map<String, String> params) {
        SessionData sd = service.saveSession(params);
        return sd;
    }

}
