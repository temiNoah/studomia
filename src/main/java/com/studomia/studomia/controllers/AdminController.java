package com.studomia.studomia.controllers;

import com.studomia.studomia.dto.response.Response;
import com.studomia.studomia.services.AdminServices;
import com.studomia.studomia.services.ExpertServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminServices adminServices;

    Logger logger= LoggerFactory.getLogger(AdminController.class);

    @GetMapping(value = "/allMale" , produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response getAllMaleStudent(@RequestHeader(name = "X-COM-PERSIST",required = true) String headerPersist,
                               @RequestHeader(name="X-COM-LOCATION",defaultValue = "ASIA") String headerLocation)
    {
        logger.info("X-COM-PERSIST=" + headerPersist +"\n" + "X-COM-LOCATION=" + headerLocation);

        Response response = adminServices.getAllMaleStudents();

        return response;
    }
}
