package com.studomia.studomia.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

//    @Autowired
//    private UserRepository userRepository;

    private String homeUrl = "http://localhost:8080/profile";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException
    {
        if (response.isCommitted()) {
            return;
        }

        DefaultOidcUser oidcUser = (DefaultOidcUser) authentication.getPrincipal();

        if(!Optional.ofNullable(oidcUser).isEmpty())
        {
            //social media login
            Map attributes = oidcUser.getAttributes();
            String email = (String) attributes.get("email");

            //print the access token from external authorization server
            System.out.println("access token :"+ oidcUser.getIdToken());
        }
        else
            {
              // the user login by regular login: username and password
               System.out.println("user login by username and password:");
            }

        //User user = userRepository.findByEmail(email);
        //String token = JwtTokenUtil.generateToken(user);
        String redirectionUrl = UriComponentsBuilder.fromUriString(homeUrl)
                //.queryParam("auth_token", token)
                .build().toUriString();
        getRedirectStrategy().sendRedirect(request, response, redirectionUrl);


    }
}
