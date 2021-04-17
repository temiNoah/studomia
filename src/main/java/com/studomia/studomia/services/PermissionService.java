package com.studomia.studomia.services;

import com.studomia.studomia.dto.request.Permission;
import com.studomia.studomia.dto.response.PermissionResponse;
import com.studomia.studomia.exceptions.EmptyRecordsException;
import javassist.NotFoundException;

import java.io.IOException;
import java.util.List;

public interface PermissionService {

    public List<PermissionResponse> getAllPermissions() throws IOException, EmptyRecordsException;
    public PermissionResponse addPermission(Permission permission) throws IOException;
    public String deletePermission( Long permissionid) throws NotFoundException;
    public PermissionResponse editPermission (Permission  permission,Long permissionId) throws IOException, NotFoundException;
    public PermissionResponse getPermission(Long permissionId) throws IOException , NotFoundException;
   // public PermissionResponse assignPermissionToRole(Long permissionId , Long roleId) throws NotFoundException;


}
