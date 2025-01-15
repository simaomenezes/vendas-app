package io.github.simaomenezes.libraryapi.controller;

import io.github.simaomenezes.libraryapi.security.CustomAuthentication;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        if (authentication instanceof CustomAuthentication customAuthentication){
            System.out.println(customAuthentication.getName());
        }
        return "Name: " + authentication.getName() + " roles: " + authentication.getAuthorities();
    }

    @GetMapping("/authorized")
    @ResponseBody
    public  String getCode(@RequestParam("code") String code){
        return "Your authorization code is:  " + code;
    }
}
