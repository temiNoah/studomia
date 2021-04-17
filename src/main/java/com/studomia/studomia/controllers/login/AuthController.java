package com.studomia.studomia.controllers.login;

import com.studomia.studomia.dto.request.Login;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthController {


    @PostMapping("/signIn")
    public ResponseEntity<?> singIn(@RequestBody Login request, HttpServletRequest req, HttpServletResponse res) //@Valid
    {

        return null;
    }


    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody  Login request, HttpServletRequest req, HttpServletResponse res) //@Valid
    {

        return null;
    }

}
