package com.studomia.studomia.controllers;

import com.studomia.studomia.dao.entities.User;
import com.studomia.studomia.dto.request.Login;
import com.studomia.studomia.dto.request.SignUpRequest;
import com.studomia.studomia.dto.request.StudentSignUpReq;
import com.studomia.studomia.dto.request.signup.Student;
import com.studomia.studomia.dto.response.JwtAuthenticationResponse;
import com.studomia.studomia.dto.response.Response;
import com.studomia.studomia.dto.response.StudentResponse;
import com.studomia.studomia.exceptions.BadRequestException;
import com.studomia.studomia.exceptions.NotFoundException;
import com.studomia.studomia.exceptions.UserLoginException;
import com.studomia.studomia.model.token.RefreshToken;
import com.studomia.studomia.security.AuthService;
import com.studomia.studomia.security.JwtTokenProvider;
import com.studomia.studomia.security.UserPrincipal;
import com.studomia.studomia.security.regularLogin.model.UserDetailsServiceImpl;
import com.studomia.studomia.security.socialLogin.AuthProvider;
import com.studomia.studomia.services.StudentServices;
import org.aspectj.weaver.ast.Not;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentServices studentServices;

    Logger logger= LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthService authService;



    @PostMapping("/signIn")
    public ResponseEntity<?> singIn(@RequestBody Login loginRequest, HttpServletRequest req, HttpServletResponse res)throws Exception //@Valid
    {
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

        @GetMapping(value="/all" , produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<StudentResponse> getStudents(@RequestHeader(name = "X-COM-PERSIST",required = true) String headerPersist,
                                             @RequestHeader(name="X-COM-LOCATION",defaultValue = "ASIA") String headerLocation) throws IOException, NotFoundException
    {
        logger.info("X-COM-PERSIST=" + headerPersist +"\n" + "X-COM-LOCATION=" + headerLocation);

        return studentServices.getStudents();
    }

   // @CrossOrigin(origins = "http://localhost:3003") //done globally
    @PostMapping(value="/add")
    public ResponseEntity addStudent(@RequestBody Student student,
                                     @RequestHeader(name = "X-COM-PERSIST",required = true) String headerPersist,
                                     @RequestHeader(name="X-COM-LOCATION",defaultValue = "ASIA") String headerLocation)
                                     throws NotFoundException,IOException

    {

        logger.info("X-COM-PERSIST=" + headerPersist +"\n" + "X-COM-LOCATION=" + headerLocation);

        if(studentServices.userExists(student.getEmail()) ) {
            throw new BadRequestException("Email address already in use.");
        }


        LocalDateTime today = LocalDateTime.now();
        String formattedDate = today.format(DateTimeFormatter.ofPattern("dd-MMM-yy"));
        student.setCreatedAt(formattedDate);
        student.setUpdatedAt(formattedDate);
        student.setActive(true);
        student.setEmailVerified(false);
        student.setActive(true);
        student.setEmailVerified(false);
       // student.setProvider(AuthProvider.local);


        ResponseEntity responseEntity = new ResponseEntity(studentServices.addStudent(student), HttpStatus.OK);

        return responseEntity;
    }

    @DeleteMapping("/student/{studentId}/delete")
    public ResponseEntity<?> deleteStudent(@PathVariable Long studentId,
                                           @RequestHeader(name = "X-COM-PERSIST",required = true) String headerPersist,
                                           @RequestHeader(name="X-COM-LOCATION",defaultValue = "ASIA") String headerLocation
                                           )throws NotFoundException
    {

        logger.info("X-COM-PERSIST=" + headerPersist +"\n" + "X-COM-LOCATION=" + headerLocation);


        ResponseEntity responseEntity = new ResponseEntity(studentServices.deleteStudent(studentId), HttpStatus.OK);

        return responseEntity;
    }

    @PutMapping("/{studentId}/edit")
    public ResponseEntity editStudent(@RequestBody  Student student,@PathVariable Long studentId,
                                      @RequestHeader(name = "X-COM-PERSIST",required = true) String headerPersist,
                                      @RequestHeader(name="X-COM-LOCATION",defaultValue = "ASIA") String headerLocation
                                      ) throws IOException , NotFoundException
    {
        logger.info("X-COM-PERSIST=" + headerPersist +"\n" + "X-COM-LOCATION=" + headerLocation);

        ResponseEntity responseEntity = new ResponseEntity(studentServices.editStudent(student,studentId), HttpStatus.OK);

        return responseEntity;
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity getStudent(@PathVariable("studentId") Long studentId,
                                     @RequestHeader(name = "X-COM-PERSIST",required = true) String headerPersist,
                                     @RequestHeader(name="X-COM-LOCATION",defaultValue = "ASIA") String headerLocation
                                     )throws IOException, NotFoundException
    {
        logger.info("X-COM-PERSIST=" + headerPersist +"\n" + "X-COM-LOCATION=" + headerLocation);

        ResponseEntity responseEntity = new ResponseEntity(studentServices.getStudent(studentId), HttpStatus.OK);

        return responseEntity;
    }

    @PutMapping("/{studentId}/role/{roleId}")
    public StudentResponse addStudentToRole(@PathVariable("studentId") Long studentId, @PathVariable("roleId") Long roleId) throws NotFoundException, IOException
    {
        return  studentServices.assignStudentToRole(studentId,roleId);
    }
}
