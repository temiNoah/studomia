package com.studomia.studomia.controllers;

import com.studomia.studomia.dto.request.signup.Admin;
import com.studomia.studomia.dto.response.Response;
import com.studomia.studomia.exceptions.EmptyRecordsException;
import com.studomia.studomia.exceptions.NotFoundException;
import com.studomia.studomia.services.AdminServices;
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
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminServices adminServices;

    Logger logger= LoggerFactory.getLogger(AdminController.class);




    @GetMapping(value="/all" , produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Admin> getAdmins(@RequestHeader(name = "X-COM-PERSIST",required = true) String headerPersist,
                                 @RequestHeader(name="X-COM-LOCATION",defaultValue = "ASIA") String headerLocation) throws IOException, EmptyRecordsException
    {
        logger.info("X-COM-PERSIST=" + headerPersist +"\n" + "X-COM-LOCATION=" + headerLocation);

        return adminServices.getAdmins();
    }

    @PostMapping("/add")
    public ResponseEntity addAdmin(@RequestBody Admin admin,
                                   @RequestHeader(name = "X-COM-PERSIST",required = true) String headerPersist,
                                   @RequestHeader(name="X-COM-LOCATION",defaultValue = "ASIA") String headerLocation)
            throws NotFoundException,IOException

    {

        logger.info("X-COM-PERSIST=" + headerPersist +"\n" + "X-COM-LOCATION=" + headerLocation);

        ResponseEntity responseEntity = new ResponseEntity(adminServices.addAdmin(admin), HttpStatus.OK);

        return responseEntity;
    }

    @DeleteMapping("/{adminId}/delete")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long adminId,
                                           @RequestHeader(name = "X-COM-PERSIST",required = true) String headerPersist,
                                           @RequestHeader(name="X-COM-LOCATION",defaultValue = "ASIA") String headerLocation
    )throws NotFoundException
    {

        logger.info("X-COM-PERSIST=" + headerPersist +"\n" + "X-COM-LOCATION=" + headerLocation);


        ResponseEntity responseEntity = new ResponseEntity(adminServices.deleteAdmin(adminId), HttpStatus.OK);

        return responseEntity;
    }

    @PutMapping("/{adminId}/edit")
    public ResponseEntity editAdmin(@RequestBody  Admin admin,@PathVariable Long adminId,
                                      @RequestHeader(name = "X-COM-PERSIST",required = true) String headerPersist,
                                      @RequestHeader(name="X-COM-LOCATION",defaultValue = "ASIA") String headerLocation
    ) throws IOException , NotFoundException
    {
        logger.info("X-COM-PERSIST=" + headerPersist +"\n" + "X-COM-LOCATION=" + headerLocation);

        ResponseEntity responseEntity = new ResponseEntity(adminServices.editAdmin(admin,adminId), HttpStatus.OK);

        return responseEntity;
    }

    @GetMapping("/{adminId}")
    public ResponseEntity getAdmin(@PathVariable("adminId") Long adminId,
                                     @RequestHeader(name = "X-COM-PERSIST",required = true) String headerPersist,
                                     @RequestHeader(name="X-COM-LOCATION",defaultValue = "ASIA") String headerLocation
    )throws IOException, NotFoundException
    {
        logger.info("X-COM-PERSIST=" + headerPersist +"\n" + "X-COM-LOCATION=" + headerLocation);

        ResponseEntity responseEntity = new ResponseEntity(adminServices.getAdmin(adminId), HttpStatus.OK);

        return responseEntity;
    }


    @GetMapping(value = "/allMale" , produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response getAllMaleStudent(@RequestHeader(name = "X-COM-PERSIST",required = true) String headerPersist,
                                      @RequestHeader(name="X-COM-LOCATION",defaultValue = "ASIA") String headerLocation)
    {
        logger.info("X-COM-PERSIST=" + headerPersist +"\n" + "X-COM-LOCATION=" + headerLocation);

        Response response = adminServices.getAllMaleStudents();

        return response;
    }
}
