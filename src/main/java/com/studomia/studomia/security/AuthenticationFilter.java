package com.studomia.studomia.security;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.studomia.studomia.dto.request.Login;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;



public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    public AuthenticationFilter(AuthenticationManager authenticationManager ) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            Login applicationUser = new ObjectMapper().readValue(req.getInputStream(), Login.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(applicationUser.getUsername(),
                            applicationUser.getPassword(), new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

//        Date exp = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
//        Key key = Keys.hmacShaKeyFor(KEY.getBytes());
//        Claims claims = Jwts.claims().setSubject(((User) auth.getPrincipal()).getUsername());
        //String token =Jwts.builder().setClaims(claims).signWith(key, SignatureAlgorithm.HS512).setExpiration(exp).compact();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        res.addHeader("token", tokenProvider.createToken(authentication));
    }
}