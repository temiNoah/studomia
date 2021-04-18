package com.studomia.studomia.security;

import com.studomia.studomia.StudomiaApplication;
import com.studomia.studomia.configuration.securityConfig.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

//    @Autowired
//    private UserRepository userRepository;

    AppConfig appConfig;


    public CustomAuthenticationSuccessHandler( AppConfig appConfig)
    {
       this.appConfig = appConfig;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException
    {
        if (response.isCommitted()) {
            return;
        }


        DefaultOidcUser oidcUser = (DefaultOidcUser) authentication.getPrincipal();

        if(Optional.ofNullable(oidcUser).isPresent())
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


        response.setHeader("Location", StudomiaApplication.baseUrl );
        response.setStatus(302);

        String redirectionUrl = UriComponentsBuilder.fromUriString(StudomiaApplication.path)
                //.queryParam("auth_token", token)
                .build().toUriString();


//        response.sendRedirect(baseUrl);
        getRedirectStrategy().sendRedirect(request, response, redirectionUrl);


    }
}
