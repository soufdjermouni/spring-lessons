package com.spring.lessons.springlessons.feature.scope.request;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PreDestroy;

/**
 * Going forward, if you want to understand how this works, you can start the application and go to
 * http://localhost:8080/hello
 *
 */
@Component
@RequestScope  //@Scope(scopeName = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestScopeBean {

    //Note that we also added the @PreDestroy annotation to print just before the destruction of the bean
    @PreDestroy
    public void preDestroy(){
        System.out.println("Destroying bean: "+ this);
    }
}
