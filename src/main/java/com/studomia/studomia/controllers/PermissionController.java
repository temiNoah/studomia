package com.studomia.studomia.controllers;

import com.studomia.studomia.dto.request.Permission;
import com.studomia.studomia.dto.response.PermissionResponse;
import com.studomia.studomia.dto.response.Response;
import com.studomia.studomia.exceptions.EmptyRecordsException;
import com.studomia.studomia.services.AdminServices;
import com.studomia.studomia.services.ExpertServices;
import com.studomia.studomia.services.PermissionService;
import com.studomia.studomia.services.StudentServices;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/permissions")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private StudentServices studentService;
    @Autowired
    private ExpertServices expertService;
    @Autowired
    private AdminServices adminServices;


    @GetMapping("/all")
    public List<PermissionResponse> getPermission() throws IOException , EmptyRecordsException
    {
        return permissionService.getAllPermissions();
    }

    @PostMapping("/add")
    public PermissionResponse addPermission(@RequestBody Permission permission) throws IOException
    {
        return permissionService.addPermission(permission);
    }

    @DeleteMapping("/{permissionId}/delete")
    public Response deletePermission(@PathVariable("permissionId") Long permissionId) throws NotFoundException
    {
        String mesesage = permissionService.deletePermission(permissionId);

        Response response = new Response() ;
        response.setCode(HttpStatus.OK.getReasonPhrase());
        response.setMessage(mesesage);

        return  response;
    }

    @PutMapping("/{permissionId}/edit")
    public PermissionResponse editPermission(@RequestBody Permission permission, @PathVariable("permissionId") Long permissionId) throws IOException,NotFoundException
    {
        return permissionService.editPermission(permission,permissionId);
    }

    @GetMapping("/{permissionId}")
    public PermissionResponse getPermission(@PathVariable("permissionId") Long permissionId,
                                    @RequestHeader(name = "X-COM-PERSIST",required = true) String headerPersist,
                                    @RequestHeader(name="X-COM-LOCATION",defaultValue = "ASIA") String headerLocation
    ) throws  IOException, NotFoundException {
        return permissionService.getPermission(permissionId);
    }



}
