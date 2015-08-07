package com.mikemunhall.hbasedaotest;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:/application-context.xml")
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}
