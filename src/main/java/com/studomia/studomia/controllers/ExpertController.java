package com.studomia.studomia.controllers;

import com.studomia.studomia.dto.request.signup.Expert;
import com.studomia.studomia.dto.response.CourseResponse;
import com.studomia.studomia.dto.response.ExpertResponse;
import com.studomia.studomia.services.ExpertServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.studomia.studomia.exceptions.NotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/experts")
public class ExpertController {
    @Autowired
    ExpertServices expertService;

    Logger logger= LoggerFactory.getLogger(ExpertController.class);

    @GetMapping(value="/all" , produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<ExpertResponse> getExperts(@RequestHeader(name = "X-COM-PERSIST",required = true) String headerPersist,
                                   @RequestHeader(name="X-COM-LOCATION",defaultValue = "ASIA") String headerLocation) throws IOException
    {
        logger.info("X-COM-PERSIST=" + headerPersist +"\n" + "X-COM-LOCATION=" + headerLocation);

        return expertService.getExperts();
    }

    @PostMapping("/add")
    public ResponseEntity addExpert(@RequestBody Expert expert,
                                     @RequestHeader(name = "X-COM-PERSIST",required = true) String headerPersist,
                                     @RequestHeader(name="X-COM-LOCATION",defaultValue = "ASIA") String headerLocation) throws IOException

    {

        logger.info("X-COM-PERSIST=" + headerPersist +"\n" + "X-COM-LOCATION=" + headerLocation);

        ResponseEntity responseEntity = new ResponseEntity(expertService.addExpert(expert), HttpStatus.OK);

        return responseEntity;
    }

    @DeleteMapping("/{expertId}/delete")
    public ResponseEntity<?> deleteExpert(@PathVariable Long expertId,
                                           @RequestHeader(name = "X-COM-PERSIST",required = true) String headerPersist,
                                           @RequestHeader(name="X-COM-LOCATION",defaultValue = "ASIA") String headerLocation
    )        throws NotFoundException
    {

        logger.info("X-COM-PERSIST=" + headerPersist +"\n" + "X-COM-LOCATION=" + headerLocation);

        ResponseEntity responseEntity = new ResponseEntity(expertService.deleteExpert(expertId), HttpStatus.OK);

        return responseEntity;
    }

    @PutMapping("/{expertId}/edit")
    public ResponseEntity editExpert(@RequestBody  Expert expert,@PathVariable("expertId") Long expertId,
                                      @RequestHeader(name = "X-COM-PERSIST",required = true) String headerPersist,
                                      @RequestHeader(name="X-COM-LOCATION",defaultValue = "ASIA") String headerLocation) throws IOException
    {
        logger.info("X-COM-PERSIST=" + headerPersist +"\n" + "X-COM-LOCATION=" + headerLocation);

        ResponseEntity responseEntity = new ResponseEntity(expertService.editExpert(expert,expertId), HttpStatus.OK);

        return responseEntity;
    }

    @GetMapping("/{expertId}")
    public ResponseEntity getExpert(@PathVariable("expertId") Long expertId,
                                     @RequestHeader(name = "X-COM-PERSIST",required = true) String headerPersist,
                                     @RequestHeader(name="X-COM-LOCATION",defaultValue = "ASIA") String headerLocation)throws IOException, NotFoundException
    {
        logger.info("X-COM-PERSIST=" + headerPersist +"\n" + "X-COM-LOCATION=" + headerLocation);

        ResponseEntity responseEntity = new ResponseEntity(expertService.getExpert(expertId), HttpStatus.OK);

        return responseEntity;
    }

    @PutMapping("/{expertId}/role/{roleId}")
    public ExpertResponse addExpertToRole(@PathVariable("expertId") Long expertId, @PathVariable("roleId") Long roleId) throws NotFoundException, IOException
    {
        return  expertService.assignExpertToRole(expertId,roleId);
    }
}
