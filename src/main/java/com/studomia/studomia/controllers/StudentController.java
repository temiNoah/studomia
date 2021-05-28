package com.studomia.studomia.controllers;

import com.studomia.studomia.dto.request.signup.Student;
import com.studomia.studomia.dto.response.StudentResponse;
import com.studomia.studomia.exceptions.NotFoundException;
import com.studomia.studomia.services.StudentServices;
import org.aspectj.weaver.ast.Not;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentServices studentServices;

    Logger logger= LoggerFactory.getLogger(StudentController.class);

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
