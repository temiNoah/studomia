package com.studomia.studomia.exceptions;

import com.studomia.studomia.utils.ResponseCode;

public class NotFoundException extends RuntimeException{

    private String message;
    private ResponseCode code;
   public NotFoundException(String msg)
    {
        super(msg);
    }

    public NotFoundException(String msg,ResponseCode code)
    {
       super(msg);

       this.message=msg;
       this.code = code;


    }
}
