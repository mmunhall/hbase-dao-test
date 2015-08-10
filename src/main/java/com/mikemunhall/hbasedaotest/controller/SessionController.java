package com.mikemunhall.hbasedaotest.controller;

import com.mikemunhall.hbasedaotest.domain.SessionData;
import com.mikemunhall.hbasedaotest.service.SessionDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/session")
public class SessionController {

    @Autowired
    private SessionDataService service;

    @RequestMapping(value="/createOne", method=RequestMethod.POST)
    public SessionData postSession(@RequestParam Map<String, String> params) {
        SessionData sd = service.saveSession(params);
        return sd;
    }

    @RequestMapping(value="/getOne", method=RequestMethod.GET)
    public SessionData getSession(@RequestParam("sessionId") String sessionId) {
        return service.getSession(sessionId);
    }

    @RequestMapping(value="/getAll", method=RequestMethod.GET)
    public List<SessionData> getSessions() {
        return service.getSessions();
    }

}
