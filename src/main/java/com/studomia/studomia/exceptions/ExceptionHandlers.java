package com.studomia.studomia.exceptions;


import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.studomia.studomia.dto.request.Course;
import com.studomia.studomia.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;



@ControllerAdvice
@Slf4j
public class ExceptionHandlers extends BaseExceptionHandler {

    private  static final Logger log= LoggerFactory.getLogger(ExceptionHandlers.class);

    public ExceptionHandlers() {
        super(log);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleUserNotFoundException(final NotFoundException ex) {
        log.error(ex.getMessage() +" not found thrown", ex);
   //define project codes
        return new ErrorResponse("USER_NOT_FOUND", ex.getMessage());
    }

    @ExceptionHandler(InvalidFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleInvalidFormatException(final InvalidFormatException ex)
    {
        return  new ErrorResponse("invalid numeric data" , ex.getMessage());
    }


}
