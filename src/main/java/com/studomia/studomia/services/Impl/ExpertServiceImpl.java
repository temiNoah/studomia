package com.studomia.studomia.services.Impl;

import com.studomia.studomia.dao.RoleRepository;
import com.studomia.studomia.dto.request.signup.Expert;
import com.studomia.studomia.dao.ExpertRepository;
import com.studomia.studomia.dto.response.ExpertResponse;
import com.studomia.studomia.exceptions.NotFoundException;
import com.studomia.studomia.services.ExpertServices;
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
public class ExpertServiceImpl implements ExpertServices {

    Logger logger = LoggerFactory.getLogger(ExpertServiceImpl.class);

    @Autowired
    ExpertRepository expertRepository;

    @Autowired
    DomainConverter domainConverter;

    @Autowired
    RoleRepository roleRepository;

    public ExpertServiceImpl(){}

    @Override
    public List<ExpertResponse> getExperts() throws IOException {

        List<ExpertResponse> experts = new ArrayList<ExpertResponse>();

        for(com.studomia.studomia.dao.entities.Expert expertDao : expertRepository.findAll())
        {
            ExpertResponse expertResponse = new ExpertResponse();

            domainConverter.convertBtwDtoDao(expertDao , expertResponse,com.studomia.studomia.dao.entities.Expert.class,ExpertResponse.class);

            experts.add(expertResponse);

        }

        return experts;
    }

    @Override
    public Expert addExpert(Expert expert) throws IOException
    {

        com.studomia.studomia.dao.entities.Expert expertDao = new com.studomia.studomia.dao.entities.Expert();

        domainConverter.convertBtwDtoDao(expert,expertDao,Expert.class,com.studomia.studomia.dao.entities.Expert.class);

        expertRepository.save(expertDao);

        domainConverter.convertBtwDtoDao(expertDao,expert,com.studomia.studomia.dao.entities.Expert.class,Expert.class);

        return expert;
    }

    @Override
    public String deleteExpert(Long expertId) throws NotFoundException
    {

        Optional<com.studomia.studomia.dao.entities.Expert> expertOpt= expertRepository.findById(expertId);

        if(!expertOpt.isPresent())
            throw new NotFoundException("Expert not found");


        logger.info("Studiomia: fetching .... course from db");

        expertRepository.deleteById(expertId);

        return "deleted";
    }

    @Override
    public Expert editExpert(Expert expert, Long expertId) throws IOException,NotFoundException
    {
        Optional<com.studomia.studomia.dao.entities.Expert> expertOpt= expertRepository.findById(expertId);

        if(!expertOpt.isPresent())
            throw new NotFoundException("Expert not found");

        com.studomia.studomia.dao.entities.Expert expertDao = expertOpt.get();


        logger.info("Studiomia: fetching .... course from db");

        domainConverter.convertBtwDtoDao(expert,expertDao,Expert.class,com.studomia.studomia.dao.entities.Expert.class);

        expertDao.setExpertId(expertId);

        expertRepository.save(expertDao);

        domainConverter.convertBtwDtoDao(expertDao,expert,com.studomia.studomia.dao.entities.Expert.class,Expert.class);

        logger.info("Studiomia: return updated course to caller");

        return expert;
    }

    @Override
    public Expert getExpert(Long id) throws IOException, NotFoundException
    {
        Optional<com.studomia.studomia.dao.entities.Expert> expertOpt= expertRepository.findById(id);

        if(!expertOpt.isPresent())
            throw new NotFoundException("Expert not found");

        com.studomia.studomia.dao.entities.Expert expertDao = expertOpt.get();

        Expert expert = new Expert();

        domainConverter.convertBtwDtoDao(expertDao,expert,com.studomia.studomia.dao.entities.Expert.class,Expert.class);

        return  expert;
    }


    @Override
    public ExpertResponse assignExpertToRole(Long expertId, Long roleId) throws NotFoundException, IOException {

        Optional<com.studomia.studomia.dao.entities.Expert> expertOpt = expertRepository.findById(expertId);

        logger.info("Service: fetching expert from repository. expert Id:" + expertId);

        Optional<com.studomia.studomia.dao.entities.Role> roleOpt  = roleRepository.findById(roleId);

        logger.info("Service: fetching role from repository. role Id:" + roleId);

        if(!expertOpt.isPresent() )
            throw new NotFoundException("Expert Not Found ,id=" + expertId);

        if(!roleOpt.isPresent())
            throw new NotFoundException("Role Not Found ,id=" + roleId);

        com.studomia.studomia.dao.entities.Expert expertDao = expertOpt.get();

        com.studomia.studomia.dao.entities.Role roleDao = roleOpt.get();

        expertDao.getRoles().add(roleDao);

        expertRepository.save(expertDao);

        logger.info("Service: assigned expert to role");

        ExpertResponse expertResponse = new ExpertResponse();

        domainConverter.convertBtwDtoDao(expertDao,expertResponse,com.studomia.studomia.dao.entities.Expert.class,ExpertResponse.class);

        return expertResponse;

    }


}
