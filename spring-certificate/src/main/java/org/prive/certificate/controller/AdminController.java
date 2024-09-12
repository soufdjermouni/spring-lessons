package org.prive.certificate.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @PreAuthorize("hasRole('ADMIN')")  // Only users with 'ADMIN' role can access this
    @GetMapping("/admin")
    public String adminEndpoint() {
        return "Admin Content";
    }

    @GetMapping("/user")
    public String userEndpoint() {
        return "User Content";
    }

    @GetMapping("/")
    public String publicEndpoint() {
        return "Public Content";
    }
}