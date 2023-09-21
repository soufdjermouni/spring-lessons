package com.spring.lessons.springlessons;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class SpringlessonsApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext app = SpringApplication.run(SpringlessonsApplication.class, args);
	}

}
