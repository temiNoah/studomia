package com.studomia.studomia.services;

import com.studomia.studomia.dto.request.Role;
import com.studomia.studomia.dto.response.RoleResponse;
import com.studomia.studomia.exceptions.EmptyRecordsException;
import com.studomia.studomia.exceptions.NotFoundException;


import java.io.IOException;
import java.util.Collection;
import java.util.List;

public interface RoleService {

    public List<RoleResponse> getRoles() throws IOException, EmptyRecordsException;
    public RoleResponse addRole(Role role) throws IOException;
    public String deleteRole( Long id) throws NotFoundException;
    public RoleResponse editRole(  Role role,Long roleId) throws IOException, NotFoundException;
    public RoleResponse getRole(Long courseId) throws IOException , NotFoundException, NotFoundException;
    /**
     * Find all roles from the database
     */
    public Collection<com.studomia.studomia.dao.entities.Role> findAll();

    public RoleResponse assignPermissionToRole(Long permissionId , Long roleId) throws NotFoundException, IOException;

}
