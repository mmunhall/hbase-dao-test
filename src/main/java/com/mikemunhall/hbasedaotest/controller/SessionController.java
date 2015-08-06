package com.mikemunhall.hbasedaotest.controller;

import com.mikemunhall.hbasedaotest.dao.SessionDataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class SessionController {

    @Autowired
    private SessionDataDao dao;

    @RequestMapping("/")
    String getSessions() {
        return "Hello Test!";
    }

}
