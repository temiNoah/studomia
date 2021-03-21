package com.studomia.studomia.controllers;

import com.studomia.studomia.dto.request.Expert;
import com.studomia.studomia.dto.request.Student;
import com.studomia.studomia.services.ExpertServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expert")
public class ExpertController {
    @Autowired
    ExpertServices expertServices;

    Logger logger= LoggerFactory.getLogger(StudentController.class);

    @GetMapping(value="/all" , produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Expert> getExperts(@RequestHeader(name = "X-COM-PERSIST",required = true) String headerPersist,
                                   @RequestHeader(name="X-COM-LOCATION",defaultValue = "ASIA") String headerLocation)
    {
        logger.info("X-COM-PERSIST=" + headerPersist +"\n" + "X-COM-LOCATION=" + headerLocation);

        return expertServices.getExperts();
    }

    @PostMapping("/add")
    public ResponseEntity addExpert(@RequestBody Expert expert,
                                     @RequestHeader(name = "X-COM-PERSIST",required = true) String headerPersist,
                                     @RequestHeader(name="X-COM-LOCATION",defaultValue = "ASIA") String headerLocation)

    {

        logger.info("X-COM-PERSIST=" + headerPersist +"\n" + "X-COM-LOCATION=" + headerLocation);

        ResponseEntity responseEntity = new ResponseEntity(expertServices.addExpert(expert), HttpStatus.OK);

        return responseEntity;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteExpert(@PathVariable String id,
                                           @RequestHeader(name = "X-COM-PERSIST",required = true) String headerPersist,
                                           @RequestHeader(name="X-COM-LOCATION",defaultValue = "ASIA") String headerLocation
    )
    {

        logger.info("X-COM-PERSIST=" + headerPersist +"\n" + "X-COM-LOCATION=" + headerLocation);

        ResponseEntity responseEntity = new ResponseEntity(expertServices.deleteExpert(id), HttpStatus.OK);

        return responseEntity;
    }

    @PostMapping("/edit")
    public ResponseEntity editExpert(@RequestBody  Expert expert,
                                      @RequestHeader(name = "X-COM-PERSIST",required = true) String headerPersist,
                                      @RequestHeader(name="X-COM-LOCATION",defaultValue = "ASIA") String headerLocation
    )
    {
        logger.info("X-COM-PERSIST=" + headerPersist +"\n" + "X-COM-LOCATION=" + headerLocation);

        ResponseEntity responseEntity = new ResponseEntity(expertServices.editExpert(expert), HttpStatus.OK);

        return responseEntity;
    }

    @GetMapping("/")
    public ResponseEntity getExpert(@RequestParam("id") String id,
                                     @RequestHeader(name = "X-COM-PERSIST",required = true) String headerPersist,
                                     @RequestHeader(name="X-COM-LOCATION",defaultValue = "ASIA") String headerLocation
    )
    {
        logger.info("X-COM-PERSIST=" + headerPersist +"\n" + "X-COM-LOCATION=" + headerLocation);

        ResponseEntity responseEntity = new ResponseEntity(expertServices.getExpert(id), HttpStatus.OK);

        return responseEntity;
    }
}
