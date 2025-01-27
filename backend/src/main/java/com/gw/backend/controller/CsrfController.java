package com.gw.backend.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CsrfController {
    @GetMapping("/csrf")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
      CsrfToken c1 = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        System.out.println("Stuff "+ c1.getToken());
        return (CsrfToken) request.getAttribute(CsrfToken.class.getName());
    }

}
