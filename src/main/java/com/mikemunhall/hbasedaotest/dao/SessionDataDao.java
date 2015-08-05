package com.mikemunhall.hbasedaotest.dao;

import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class SessionDataDao {

    @Autowired
    public SessionDataDao(ApplicationArguments args) {
        // System.out.println(args.getOptionValues("author").get(0));
    }
}
