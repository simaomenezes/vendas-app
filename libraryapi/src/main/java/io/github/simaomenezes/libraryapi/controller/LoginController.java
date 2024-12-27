package io.github.simaomenezes.libraryapi.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String pageLogin(){
        return "login";
    }

    @GetMapping("/")
    @ResponseBody
    public String pageBegin(Authentication authentication){
        return "Name: " + authentication.getName() + " roles: " + authentication.getAuthorities();
    }
}
