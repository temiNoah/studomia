package com.studomia.studomia.controllers.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class PageController {

    @GetMapping("/login")
    public String loginPage(){

        System.out.println("sending.. login page");

        return "customLogin.html";
    }
    @GetMapping("/index")
    public  String index(){

        System.out.println("sending... the index page");

        return  "index.html";
    }

    @GetMapping("/profile")
    public  void profile(HttpServletResponse httpServletResponse){
        httpServletResponse.setHeader("Location", "http://localhost:3003/profile");
        httpServletResponse.setStatus(302);

       // return  "profile.html";
    }

    @GetMapping("/register")
    public String registration()
    {
        return "Registration.html";
    }

    @GetMapping("/view-students")
    public String studentView()
    {
        return "StudentsView.html";
    }

    @GetMapping("/view-experts")
    public String expertView()
    {
        return "ExpertView.html";
    }
}
