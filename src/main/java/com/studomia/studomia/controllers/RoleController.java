package com.studomia.studomia.controllers;

import com.studomia.studomia.dto.request.Role;
import com.studomia.studomia.dto.response.Response;
import com.studomia.studomia.dto.response.RoleResponse;
import com.studomia.studomia.exceptions.EmptyRecordsException;
import com.studomia.studomia.services.AdminServices;
import com.studomia.studomia.services.ExpertServices;
import com.studomia.studomia.services.RoleService;
import com.studomia.studomia.services.StudentServices;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController
{
    Logger  logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RoleService roleService;
    @Autowired
    private StudentServices studentService;
    @Autowired
    private ExpertServices expertService;
    @Autowired
    private AdminServices adminServices;


    @GetMapping("/all")
    public List<RoleResponse> getRoles() throws IOException, EmptyRecordsException
    {
        return roleService.getRoles();
    }

    @PostMapping("/add")
    public RoleResponse addRole(@RequestBody Role role) throws IOException
    {
        return roleService.addRole(role);
    }

    @DeleteMapping("/{roleId}/delete")
    public Response deleteRole(@PathVariable("roleId") Long roleId) throws NotFoundException
    {
        Response response = new Response() ;
        response.setCode(HttpStatus.OK.getReasonPhrase());
        response.setMessage(roleService.deleteRole(roleId));

        return  response;
    }

    @PutMapping("/{roleId}/edit")
    public RoleResponse editRole(@RequestBody Role role, @PathVariable("roleId") Long roleId) throws IOException,NotFoundException
    {
        return roleService.editRole(role,roleId);
    }

    @GetMapping("/{roleId}")
    public RoleResponse getRole(@PathVariable("roleId") Long roleId,
                                    @RequestHeader(name = "X-COM-PERSIST",required = true) String headerPersist,
                                    @RequestHeader(name="X-COM-LOCATION",defaultValue = "ASIA") String headerLocation
    ) throws  IOException, NotFoundException {
        return roleService.getRole(roleId);
    }


    @PutMapping("/{roleId}/permission/{permissionId}")
    public RoleResponse addPermissionToRole(@PathVariable("roleId") Long roleId, @PathVariable("permissionId") Long permissionId) throws NotFoundException, IOException
    {
        return  roleService.assignPermissionToRole(permissionId,roleId);// courseService.editCourse(course);
    }

}
