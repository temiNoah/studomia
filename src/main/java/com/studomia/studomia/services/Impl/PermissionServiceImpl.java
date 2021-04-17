package com.studomia.studomia.services.Impl;

import com.studomia.studomia.dao.PermissionRepository;
import com.studomia.studomia.dao.RoleRepository;
import com.studomia.studomia.dto.request.Permission;
import com.studomia.studomia.dto.response.PermissionResponse;
import com.studomia.studomia.exceptions.EmptyRecordsException;
import com.studomia.studomia.services.PermissionService;
import com.studomia.studomia.utils.DomainConverter;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PermissionServiceImpl implements PermissionService {

    Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    DomainConverter domainConverter;

    @Autowired
    PermissionRepository permissionRepository;

    @Override
    public List<PermissionResponse> getAllPermissions() throws IOException, EmptyRecordsException
    {
        List<com.studomia.studomia.dao.entities.Permission> permissions = permissionRepository.findAll();

        if(Optional.ofNullable(permissions).isEmpty())
            throw new EmptyRecordsException();

        List<PermissionResponse>  permissionResponses= new ArrayList<PermissionResponse>();

        for(com.studomia.studomia.dao.entities.Permission permissionDao : permissions)
        {
            PermissionResponse permissionResponse=new PermissionResponse();

            domainConverter.convertBtwDtoDao(permissionDao,permissionResponse,com.studomia.studomia.dao.entities.Permission.class,PermissionResponse.class);

            permissionResponses.add(permissionResponse);
        }

        return permissionResponses;
    }

    @Override
    public PermissionResponse addPermission(Permission permissionDto) throws IOException {

        com.studomia.studomia.dao.entities.Permission permissionDao = new com.studomia.studomia.dao.entities.Permission();

        logger.info("New Permission created:" + permissionDao.toString());

        domainConverter.convertBtwDtoDao(permissionDto,permissionDao, PermissionResponse.class, com.studomia.studomia.dao.entities.Permission.class);

        permissionRepository.save(permissionDao) ;

        logger.info("Saved : Permission :" + permissionDao.toString());

        PermissionResponse permissionResponse= new PermissionResponse();

        domainConverter.convertBtwDtoDao(permissionDao,permissionResponse,com.studomia.studomia.dao.entities.Permission.class, PermissionResponse.class);

        return permissionResponse;
    }

    @Override
    public String deletePermission(Long permissionId) throws NotFoundException {
        Optional<com.studomia.studomia.dao.entities.Permission> permissionOpt= permissionRepository.findById(permissionId) ;

        if(permissionOpt.isEmpty())
            throw new NotFoundException("Permission not found");

        permissionRepository.deleteById(permissionId);

        return "deleted";
    }

    @Override
    public PermissionResponse editPermission(Permission permissionDto, Long permissionId) throws IOException, NotFoundException
    {

        Optional<com.studomia.studomia.dao.entities.Permission> permissionOpt= permissionRepository.findById(permissionId);

        if(permissionOpt.isEmpty())
            throw new NotFoundException("Permission not Found to edit");


        com.studomia.studomia.dao.entities.Permission  permissionDao  = permissionOpt.get();

        domainConverter.convertBtwDtoDao(permissionDto,permissionDao, Permission.class,com.studomia.studomia.dao.entities.Permission.class);

        permissionDao.setPermissionId(permissionId);

        permissionRepository.save(permissionDao);

        PermissionResponse permissionResponse = new PermissionResponse();

        domainConverter.convertBtwDtoDao(permissionDao,permissionResponse,com.studomia.studomia.dao.entities.Permission.class,PermissionResponse.class);

        return permissionResponse;
    }

    @Override
    public PermissionResponse getPermission(Long permissionId) throws IOException, NotFoundException
    {
        PermissionResponse permissionResponse  = new PermissionResponse();

        Optional<com.studomia.studomia.dao.entities.Permission> permissionOpt= permissionRepository.findById(permissionId);

        if(permissionOpt.isEmpty())
            throw new NotFoundException("Permission not Found");

        com.studomia.studomia.dao.entities.Permission permissionDao = permissionOpt.get();

        domainConverter.convertBtwDtoDao(permissionDao, permissionResponse,com.studomia.studomia.dao.entities.Permission.class,PermissionResponse.class);

        Optional<PermissionResponse> optional= Optional.of(permissionResponse);

        return optional.get();
    }

//    @Override
//    public PermissionResponse assignPermissionToRole(Long permissionId, Long roleId) throws NotFoundException
//    {
//
//    }
}
