package com.spring.lessons.springlessons.feature.scope.session;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PreDestroy;

@Service
@SessionScope
public class SessionScopeBean {

    @PreDestroy
    public void preDestroy(){
        System.out.println("Destroying bean: "+ this);
    }
}