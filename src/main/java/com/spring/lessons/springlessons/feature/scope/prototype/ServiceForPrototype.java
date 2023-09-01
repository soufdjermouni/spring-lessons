package com.spring.lessons.springlessons.feature.scope.prototype;

import org.springframework.stereotype.Service;

@Service
public class ServiceForPrototype {

    private final PrototypeScope prototype1;
    private final PrototypeScope prototype2;
    public ServiceForPrototype(PrototypeScope prototype1, PrototypeScope prototype2) {
        this.prototype1 = prototype1;
        this.prototype2 = prototype2;
        System.out.println("prototype="+prototype1);
        System.out.println("prototype="+prototype2);
    }
}
