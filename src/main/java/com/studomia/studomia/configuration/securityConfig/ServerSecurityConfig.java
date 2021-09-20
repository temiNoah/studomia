package com.studomia.studomia.configuration.securityConfig;

import com.studomia.studomia.security.AuthenticationFilter;
import com.studomia.studomia.security.CustomAuthenticationSuccessHandler;
import com.studomia.studomia.security.RestAuthenticationEntryPoint;
import com.studomia.studomia.security.AuthorizationFilter;
import com.studomia.studomia.security.regularLogin.model.UserDetailsServiceImpl;
import com.studomia.studomia.security.socialLogin.CustomOidcJwtUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity//(debug = true)
//@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class ServerSecurityConfig extends WebSecurityConfigurerAdapter {


    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/v2/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/csrf",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/css/**","/js/**","/images/**"
            // other public endpoints of your API may be appended to this array
    };

//    @Autowired
//    UserDetailsService userDetailsService;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService);
//    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/console/**",
                "/v2/api-docs",
                "/v2/api-docs/**",
                "/swagger-resources",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**",
                "/csrf",
                "/v3/api-docs/**",
                "/swagger-ui/**",
                "/css/**","/js/**","/images/**",
                "/console/**/*.jsp",
                "/console/**/*.do"

        );
    }

    @Autowired
    CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private CustomOidcJwtUserService customOAuth2UserService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
//                .antMatchers("/login/**","/register/**","/view-students/**","/view-experts/**").permitAll()
//                .antMatchers("/students/**","/experts/**").permitAll()

        http
                .authorizeRequests()
                       .antMatchers("/login/**","/register/**","/students/**","/signIn/**").permitAll()
                       .antMatchers("/signUp").permitAll()
                       .anyRequest().authenticated()
                       .and()
                       .exceptionHandling()
                       .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                       .and()
                .csrf()
                        .disable()
                .httpBasic()
                        .disable()
                .formLogin().permitAll()
                        .loginPage("/login")
                        //.usernameParameter("username")
                        //.passwordParameter("password")
                        .defaultSuccessUrl("/profile")
                .and()
//                .addFilter(new AuthenticationFilter(authenticationManager()))
//                .addFilter(new AuthorizationFilter())
                .oauth2Login()
                       .loginPage("/login")
                       .redirectionEndpoint()
                              .baseUri("/oauth2/callback/*")
                              .and()
                       .userInfoEndpoint()
                               //.userService(customOAuth2UserService)
                                .oidcUserService(customOAuth2UserService)
                               .and()
                       .authorizationEndpoint()
                                .baseUri("/oauth2/authorize")
                                .authorizationRequestRepository(customAuthorizationRequestRepository())
                                .and()

                       .successHandler(customAuthenticationSuccessHandler)
                       //.failureHandler()
                       //.defaultSuccessUrl("/loginSuccess")
                        //  .failureUrl("/loginFailure");


        ;
        // Add our custom Token based authentication filter
        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        // @      //formatter:on
    }

    @Bean
    public AuthorizationFilter tokenAuthenticationFilter() {
        return new AuthorizationFilter();
    }


    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return  new BCryptPasswordEncoder();//NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthorizationRequestRepository customAuthorizationRequestRepository() {
        return new HttpSessionOAuth2AuthorizationRequestRepository();
    }


    //sometime u might want to use the authentication manager bean in some location so do the below
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder
                                     authManagerBuilder) throws Exception {
        authManagerBuilder.userDetailsService(userDetailsService()).passwordEncoder(getPasswordEncoder());
    }

}


