package com.studomia.studomia.utils;

public enum ResponseCode
{
   SUCCESSFUL("SUCCESSFUL","operation successfully");
    private String code;
    private String message;

    ResponseCode(String code ,String message)
    {
        this.code = code;
        this.message = message;
    }
}
