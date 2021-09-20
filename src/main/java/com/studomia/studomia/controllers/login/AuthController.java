package com.studomia.studomia.controllers.login;

import com.studomia.studomia.dao.entities.User;
import com.studomia.studomia.dto.request.Login;
import com.studomia.studomia.dto.request.SignUpRequest;
import com.studomia.studomia.dto.request.TokenRefreshRequest;
import com.studomia.studomia.dto.response.JwtAuthenticationResponse;
import com.studomia.studomia.dto.response.Response;
import com.studomia.studomia.exceptions.BadRequestException;
import com.studomia.studomia.exceptions.TokenRefreshException;
import com.studomia.studomia.exceptions.UserLoginException;
import com.studomia.studomia.model.token.RefreshToken;
import com.studomia.studomia.security.AuthService;
import com.studomia.studomia.security.JwtTokenProvider;
import com.studomia.studomia.security.UserPrincipal;
import com.studomia.studomia.security.regularLogin.model.UserDetailsServiceImpl;
import com.studomia.studomia.security.socialLogin.AuthProvider;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.time.Instant;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

//    @Autowired
//    private UserRepository userRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private  AuthService authService;

    private static final Logger logger = Logger.getLogger(AuthController.class);


    @PostMapping("/signIn")
    public ResponseEntity<?> singIn(@RequestBody Login loginRequest, HttpServletRequest req, HttpServletResponse res)throws Exception //@Valid
    {

//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                    loginRequest.getUsername(), loginRequest.getPassword()));
//
//        }
//        catch (DisabledException e) {
//            //throw new Exception("USER_DISABLED", e);
//            e.printStackTrace();
//        }
//        catch (BadCredentialsException e) {
//           // throw new Exception("INVALID_CREDENTIALS", e);
//          //  System.out.println("bad credentials");
//            throw e;
//           // e.printStackTrace();
//        }
//        catch (Exception e){
//           // throw e;
//           // System.out.println("generic exception");
//            throw e;
//           // e.printStackTrace();
//        }

        Authentication authentication = authService.authenticateUser(loginRequest).orElseThrow( () -> new UserLoginException("Couldn't login user [" + loginRequest + "]") );

        UserPrincipal userdetails = (UserPrincipal) userDetailsService.loadUserByUsername(loginRequest.getUsername());

        logger.info("Logged in User returned [API]: " + userdetails.getUsername());

        return authService.createAndPersistRefreshTokenForDevice(authentication, loginRequest)
                .map(RefreshToken::getToken)
                .map(refreshToken -> {
                    //generate new access token
                    String jwtToken = authService.generateToken(userdetails);
                    //then return token and access token
                    return ResponseEntity.ok(new JwtAuthenticationResponse(jwtToken, refreshToken, tokenProvider.getExpiryDuration()));
                })
                .orElseThrow(() -> new UserLoginException("Couldn't create refresh token for: [" + loginRequest + "]"));
    }


    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpRequest, HttpServletRequest req, HttpServletResponse res) //@Valid
    {
        if(userDetailsService.userExists(signUpRequest.getEmail()) ) {
            throw new BadRequestException("Email address already in use.");
        }

        // Creating user's account
//        User user = new User();
//        user.setFirstName(signUpRequest.getFirstName());
//        user.setEmail(signUpRequest.getEmail());
//        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
//        user.setProvider(AuthProvider.local);

        Instant instant1 = Instant.now();  // Instant.now(Clock.systemUTC());  ,System.currentTimeMillis();  , long now = System.currentTimeMillis();Instant instant3 = Instant.ofEpochMilli(now)user.setCreatedAt( instant1);

        // now = now/1000;
        //        Instant instant4 = Instant.ofEpochSecond(now); Instant instant6 = Instant.parse("1980-04-09T10:15:30.00Z")

       // user.setPassword(passwordEncoder.encode(user.getPassword()));

//        user.setCreatedAt(instant1);
//        user.setUpdatedAt(instant1);
//        user.setActive(true);
//        user.setEmailVerified(false);
//
//        User result = userDetailsService.save(user);
//
//        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/me").buildAndExpand(result.getId()).toUri();

        return null;//ResponseEntity.created(location).body(new Response("200", "User registered successfully@") );


    }

    /**
     * Refresh the expired jwt token using a refresh token for the specific device
     * and return a new token to the caller
     */
    @PostMapping("/refresh")
    @ApiOperation(value = "Refresh the expired jwt authentication by issuing a token refresh request and returns the" +
            "updated response tokens")
    public ResponseEntity refreshJwtToken(@ApiParam(value = "The TokenRefreshRequest payload") @Valid @RequestBody TokenRefreshRequest tokenRefreshRequest) {

        return authService.refreshJwtToken(tokenRefreshRequest)
                .map(updatedToken -> {
                    String refreshToken = tokenRefreshRequest.getRefreshToken();
                    logger.info("Created new Jwt Auth token: " + updatedToken);
                    return ResponseEntity.ok(new JwtAuthenticationResponse(updatedToken, refreshToken, tokenProvider.getExpiryDuration()));
                })
                .orElseThrow(() -> new TokenRefreshException(tokenRefreshRequest.getRefreshToken(), "Unexpected error during token refresh. Please logout and login again."));
    }

//    @RequestMapping(value = "/refreshtoken", method = RequestMethod.GET)
//    public ResponseEntity<?> refreshtoken(HttpServletRequest request) throws Exception {
//        // From the HttpRequest get the claims
//        DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute("claims");
//
//        Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
//        String token = tokenProvider.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
//        return ResponseEntity.ok(new AuthResponse(token));
//    }
//
//    public Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
//        Map<String, Object> expectedMap = new HashMap<String, Object>();
//        for (Map.Entry<String, Object> entry : claims.entrySet()) {
//            expectedMap.put(entry.getKey(), entry.getValue());
//        }
//        return expectedMap;
//    }

}
