package com.studomia.studomia.services;

import com.studomia.studomia.dto.response.Response;
import org.springframework.stereotype.Service;


public interface AdminServices {

    public Response getAllMaleStudents();

    public Response getAllFemaleStudent();

}
