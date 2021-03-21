package com.studomia.studomia.controllers;

import com.studomia.studomia.dto.request.Student;
import com.studomia.studomia.services.StudentServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentServices studentServices;

    Logger logger= LoggerFactory.getLogger(StudentController.class);

    @GetMapping(value="/all" , produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Student> getStudents(@RequestHeader(name = "X-COM-PERSIST",required = true) String headerPersist,
                                     @RequestHeader(name="X-COM-LOCATION",defaultValue = "ASIA") String headerLocation)
    {
        logger.info("X-COM-PERSIST=" + headerPersist +"\n" + "X-COM-LOCATION=" + headerLocation);

        return studentServices.getStudents();
    }

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody Student student,
                                     @RequestHeader(name = "X-COM-PERSIST",required = true) String headerPersist,
                                     @RequestHeader(name="X-COM-LOCATION",defaultValue = "ASIA") String headerLocation)

    {

        logger.info("X-COM-PERSIST=" + headerPersist +"\n" + "X-COM-LOCATION=" + headerLocation);

        ResponseEntity responseEntity = new ResponseEntity(studentServices.addStudent(student), HttpStatus.OK);

        return responseEntity;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteStudent(@PathVariable String id,
                                           @RequestHeader(name = "X-COM-PERSIST",required = true) String headerPersist,
                                           @RequestHeader(name="X-COM-LOCATION",defaultValue = "ASIA") String headerLocation
                                           )
    {

        logger.info("X-COM-PERSIST=" + headerPersist +"\n" + "X-COM-LOCATION=" + headerLocation);

        Student student = studentServices.getStudent(id).orElse(new Student());

        ResponseEntity responseEntity = new ResponseEntity(studentServices.deleteStudent(student), HttpStatus.OK);

        return responseEntity;
    }

    @PostMapping("/edit")
    public ResponseEntity editStudent(@RequestBody  Student student,
                                      @RequestHeader(name = "X-COM-PERSIST",required = true) String headerPersist,
                                      @RequestHeader(name="X-COM-LOCATION",defaultValue = "ASIA") String headerLocation
                                      )
    {
        logger.info("X-COM-PERSIST=" + headerPersist +"\n" + "X-COM-LOCATION=" + headerLocation);

        ResponseEntity responseEntity = new ResponseEntity(studentServices.editStudent(student), HttpStatus.OK);

        return responseEntity;
    }

    @GetMapping("/")
    public ResponseEntity getStudent(@RequestParam("id") String id,
                                     @RequestHeader(name = "X-COM-PERSIST",required = true) String headerPersist,
                                     @RequestHeader(name="X-COM-LOCATION",defaultValue = "ASIA") String headerLocation
                                     )
    {
        logger.info("X-COM-PERSIST=" + headerPersist +"\n" + "X-COM-LOCATION=" + headerLocation);

        ResponseEntity responseEntity = new ResponseEntity(studentServices.getStudent(id), HttpStatus.OK);

        return responseEntity;
    }
}
