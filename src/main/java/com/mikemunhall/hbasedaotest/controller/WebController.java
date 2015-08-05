package com.mikemunhall.hbasedaotest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class WebController {

    @RequestMapping("/test")
    String home() {
        return "Hello World!";
    }
}
