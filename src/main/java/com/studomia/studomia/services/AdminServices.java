package com.studomia.studomia.services;

import com.studomia.studomia.dto.request.signup.Admin;
import com.studomia.studomia.dto.response.AdminResponse;
import com.studomia.studomia.dto.response.Response;
import com.studomia.studomia.dto.response.RoleResponse;
import com.studomia.studomia.exceptions.NotFoundException;


import java.io.IOException;
import java.util.List;
import java.util.Optional;


public interface AdminServices {

    public Response getAllMaleStudents();

    public Response getAllFemaleStudent();

     List<Admin> getAdmins() throws IOException;

    Admin addAdmin(Admin admin) throws IOException;

    String deleteAdmin(Long adminId);

    Admin editAdmin(Admin admin, Long adminId) throws IOException, NotFoundException;

    Optional<Admin> getAdmin(Long adminId) throws IOException, NotFoundException;

    public AdminResponse assignAdminToRole(Long adminId , Long roleId) throws NotFoundException, IOException;

}
