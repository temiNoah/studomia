package com.studomia.studomia.services.Impl;

import com.studomia.studomia.dao.RoleRepository;
import com.studomia.studomia.dto.request.signup.Admin;
import com.studomia.studomia.dto.response.AdminResponse;
import com.studomia.studomia.dto.response.Response;
import com.studomia.studomia.dao.AdminRepository;
import com.studomia.studomia.dto.response.RoleResponse;
import com.studomia.studomia.dto.response.StudentResponse;
import com.studomia.studomia.exceptions.NotFoundException;
import com.studomia.studomia.services.AdminServices;
import com.studomia.studomia.utils.DomainConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminServices {

     Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private DomainConverter<Object, Object> domainConverter;


     public AdminServiceImpl(){}
//    AdminServiceImpl(@Qualifier("adminRepo")AdminRepository adminRepository)
//    {
//        adminRepository = adminRepository;
//    }

    public List<Admin> getAdmins() throws IOException
    {
        List<Admin>  admins= new ArrayList<Admin>();

        for( com.studomia.studomia.dao.entities.Admin adminDao : adminRepository.findAll())
        {
            Admin admin= new Admin();
            domainConverter.convertBtwDtoDao(adminDao,admin,com.studomia.studomia.dao.entities.Admin.class,Admin.class);
            admins.add(admin);
        }

        return admins;
    }

    public Admin addAdmin(Admin adminDto) throws IOException
    {
        com.studomia.studomia.dao.entities.Admin adminDao = new com.studomia.studomia.dao.entities.Admin();

        logger.info("amin:" + adminDao.toString());

        domainConverter.convertBtwDtoDao(adminDto,adminDao,Admin.class, com.studomia.studomia.dao.entities.Admin.class);

        logger.info("admin:" + adminDao.toString());

        adminRepository.save(adminDao ) ;

        domainConverter.convertBtwDtoDao(adminDao,adminDto,com.studomia.studomia.dao.entities.Admin.class,Admin.class);

        return adminDto;
    }

//    @Override
//    public String deleteStudent(Student student) {
//        return null;
//    }

    public String deleteAdmin( Long adminId) throws NotFoundException
    {
        Optional<com.studomia.studomia.dao.entities.Admin> adminOpt= adminRepository.findById(adminId);


        if(adminOpt.isEmpty())
            throw new NotFoundException("Admin not found");

        adminRepository.deleteById(adminId);

        return "deleted";
    }

    @Override
    public Admin editAdmin( Admin admin,Long adminId) throws IOException

    {
        Optional<com.studomia.studomia.dao.entities.Admin> adminOpt= adminRepository.findById(adminId);


        if(adminOpt.isEmpty())
            throw new NotFoundException("Admin not found");


        com.studomia.studomia.dao.entities.Admin adminDao = adminOpt.get();

        domainConverter.convertBtwDtoDao(admin,adminDao,Admin.class,com.studomia.studomia.dao.entities.Admin.class);

        adminDao.setAdminId(adminId);

        adminRepository.save(adminDao);

        domainConverter.convertBtwDtoDao(adminDao,admin,com.studomia.studomia.dao.entities.Admin.class,Admin.class);

        return admin;
    }

    public Optional<Admin> getAdmin(Long id) throws  NotFoundException,IOException
    {

        Admin adminDto = new Admin();

        Optional<com.studomia.studomia.dao.entities.Admin> adminOpt= adminRepository.findById(id);


        if(adminOpt.isEmpty())
            throw new NotFoundException("Admin not Found");

        com.studomia.studomia.dao.entities.Admin adminDao= adminOpt.get();

        domainConverter.convertBtwDtoDao(adminDao, adminDto,com.studomia.studomia.dao.entities.Admin.class,Admin.class);

        Optional<Admin> optional= Optional.of(adminDto);

        return optional;//.orElseThrow(new Exception(""));
    }

    @Override
    public AdminResponse assignAdminToRole(Long adminId, Long roleId) throws NotFoundException, IOException {

        Optional<com.studomia.studomia.dao.entities.Admin> adminOpt = adminRepository.findById(adminId);

        logger.info("Service: fetching Admin from repository. admin Id:" + adminId);

        Optional<com.studomia.studomia.dao.entities.Role> roleOpt  = roleRepository.findById(roleId);

        logger.info("Service: fetching role from repository. role Id:" + roleId);

        if(adminOpt.isEmpty() )
            throw new NotFoundException("Admin Not Found ,id=" + adminId);

        if(roleOpt.isEmpty())
            throw new NotFoundException("Role Not Found ,id=" + roleId);

        com.studomia.studomia.dao.entities.Admin adminDao = adminOpt.get();

        com.studomia.studomia.dao.entities.Role roleDao = roleOpt.get();

        adminDao.getRoles().add(roleDao);

        adminRepository.save(adminDao);

        logger.info("Service: assigned admin to role");

        AdminResponse adminResponse = new AdminResponse();

        domainConverter.convertBtwDtoDao(adminDao,adminResponse,com.studomia.studomia.dao.entities.Admin.class,AdminResponse.class);

        return adminResponse;

    }


    @Override
    public Response getAllMaleStudents(){

        //adminRepository.g

        return null;
    }

    @Override
    public Response getAllFemaleStudent(){

        return null;
    }


}
