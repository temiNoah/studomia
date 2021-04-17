package com.studomia.studomia.services.Impl;

import com.studomia.studomia.dao.PermissionRepository;
import com.studomia.studomia.dao.RoleRepository;
import com.studomia.studomia.dto.request.Role;
import com.studomia.studomia.dto.response.RoleResponse;
import com.studomia.studomia.exceptions.EmptyRecordsException;
import com.studomia.studomia.services.RoleService;
import com.studomia.studomia.utils.DomainConverter;
import com.studomia.studomia.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
   RoleRepository roleRepository;

    @Autowired
    DomainConverter domainConverter;

    @Autowired
    PermissionRepository permissionRepository;



    @Override
    public List<RoleResponse> getRoles() throws IOException,EmptyRecordsException {

        List<com.studomia.studomia.dao.entities.Role> roleList = roleRepository.findAll();

        if(Optional.ofNullable(roleList).isEmpty())
            throw new EmptyRecordsException();


        List<RoleResponse>   roleResponses= new ArrayList<RoleResponse>();

        for(com.studomia.studomia.dao.entities.Role roleDao: roleList)
        {
            RoleResponse roleResponse=new RoleResponse();

            domainConverter.convertBtwDtoDao(roleDao,roleResponse,com.studomia.studomia.dao.entities.Role.class,RoleResponse.class);

            roleResponses.add(roleResponse);
        }

        return roleResponses;
    }

    @Override
    public RoleResponse addRole(Role roleDto) throws IOException
    {
        com.studomia.studomia.dao.entities.Role roleDao = new com.studomia.studomia.dao.entities.Role();

        logger.info("New Role created:" + roleDao.toString());

        domainConverter.convertBtwDtoDao(roleDto,roleDao, RoleResponse.class, com.studomia.studomia.dao.entities.Role.class);

        roleRepository.save(roleDao) ;

        logger.info("Saving .. Role :" + roleDao.toString());

        RoleResponse roleResponse= new RoleResponse();

        domainConverter.convertBtwDtoDao(roleDao,roleResponse,com.studomia.studomia.dao.entities.Role.class, RoleResponse.class);

        return roleResponse;
    }

    @Override
    public String deleteRole(Long roleId) throws NotFoundException
    {

        Optional<com.studomia.studomia.dao.entities.Role> roleOpt= roleRepository.findById(roleId) ;

        if(roleOpt.isEmpty())
            throw new NotFoundException("Role not found");

        roleRepository.deleteById(roleId);

        return "deleted";
    }

    @Override
    public RoleResponse editRole(Role roleDto, Long roleId) throws IOException, NotFoundException
    {
        Optional<com.studomia.studomia.dao.entities.Role> roleOpt= roleRepository.findById(roleId);

        if(roleOpt.isEmpty())
            throw new NotFoundException("Role not Found to edit");


        com.studomia.studomia.dao.entities.Role  roleDao  = roleOpt.get();

        domainConverter.convertBtwDtoDao(roleDto,roleDao,Role.class,com.studomia.studomia.dao.entities.Role.class);

        roleDao.setRoleId(roleId);

        roleRepository.save(roleDao);

        RoleResponse roleResponse = new RoleResponse();

        domainConverter.convertBtwDtoDao(roleDao,roleResponse,com.studomia.studomia.dao.entities.Role.class,RoleResponse.class);

        return roleResponse;
    }

    @Override
    public RoleResponse getRole(Long roleId) throws IOException, NotFoundException
    {
        RoleResponse roleResponse  = new RoleResponse();

        Optional<com.studomia.studomia.dao.entities.Role> roleOpt= roleRepository.findById(roleId);

        if(roleOpt.isEmpty())
            throw new NotFoundException("Role not Found");

        com.studomia.studomia.dao.entities.Role roleDao = roleOpt.get();

        domainConverter.convertBtwDtoDao(roleDao, roleResponse,com.studomia.studomia.dao.entities.Role.class,RoleResponse.class);

        Optional<RoleResponse> optional= Optional.of(roleResponse);

        return optional.get();
    }

    @Override
    public RoleResponse assignPermissionToRole(Long permissionId, Long roleId) throws NotFoundException, IOException
    {
        Optional<com.studomia.studomia.dao.entities.Permission> permissionOpt = permissionRepository.findById(permissionId);

        logger.info("Service: fetching permission from repository. role Id:" + permissionId);

        Optional<com.studomia.studomia.dao.entities.Role> roleOpt  = roleRepository.findById(roleId);

        logger.info("Service: fetching role from repository. role Id:" + roleId);

        if(permissionOpt.isEmpty() )
            throw new NotFoundException("Permission Not Found ,id=" + permissionId);

        if(roleOpt.isEmpty())
            throw new NotFoundException("Role Not Found ,id=" + roleId);

        com.studomia.studomia.dao.entities.Permission permissionDao = permissionOpt.get();

        com.studomia.studomia.dao.entities.Role roleDao = roleOpt.get();

        roleDao.getPermissions().add(permissionDao);

        //permissionDao.getRoles().add(roleDao); //what's the implication in database. Assumption is so when am fetching/loading Permissions to view it will come with roles as well
        roleRepository.save(roleDao);

        logger.info("Service: assigned permission to role");

        RoleResponse roleResponse = new RoleResponse();

        domainConverter.convertBtwDtoDao(roleDao,roleResponse,com.studomia.studomia.dao.entities.Role.class,RoleResponse.class);

        return roleResponse;

    }
}
