package com.spring.lessons.springlessons.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api")
public class DemoSecurityController {

    @GetMapping("/")
    public String home() {
        return
                "<html>\n" +
                        "  <head>\n" +
                        "    <title>Home</title>\n" +
                        "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                        "  </head>\n" +
                        "  <body>\n" +
                        "    <p>\n" +
                        "      <a href=\"http://localhost:8080/users\">Users</a>\n" +
                        "    </p>\n" +
                        "    <p>\n" +
                        "      <a href=\"http://localhost:8080/admins\">Admins</a>\n" +
                        "    </p>\n" +
                        "    <p>\n" +
                        "      <a href=\"http://localhost:8080/logout\">Log out</a>\n" +
                        "    </p>\n" +
                        "  </body>\n" +
                        "</html>\n";
    }

    @GetMapping("/users")
    public String getUsers() {
        return "Only users can see this";
    }

    @GetMapping("/admins")
    public String getAdmins() {
        return "Only admins can see this";
    }


}
