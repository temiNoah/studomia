package com.studomia.studomia.services.Impl;

import com.studomia.studomia.dto.request.Admin;
import com.studomia.studomia.dto.response.Response;
import com.studomia.studomia.repository.AdminRepository;
import com.studomia.studomia.services.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminServices {

    @Autowired
    AdminRepository adminRepository;

//    AdminServiceImpl(@Qualifier("adminRepo")AdminRepository adminRepository)
//    {
//        adminRepository = adminRepository;
//    }

    public Response getAllMaleStudents(){

        //adminRepository.g

        return null;
    }

    public Response getAllFemaleStudent(){

        return null;
    }
}
