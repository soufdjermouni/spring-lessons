package com.spring.lessons.springlessons.feature.scope.singleton;

import org.springframework.stereotype.Service;

@Service
public class ServiceForSingleton {

    private final SingletonScope singleton1;
    private final SingletonScope singleton2;

    public ServiceForSingleton(SingletonScope singleton1, SingletonScope singleton2) {
        this.singleton1 = singleton1;
        this.singleton2 = singleton2;
        System.out.println(singleton1);
        System.out.println(singleton2);
        //com.spring.lessons.springlessons.feature.scope.singleton.Singleton@6a878778
        //com.spring.lessons.springlessons.feature.scope.singleton.Singleton@6a878778
    }
}
