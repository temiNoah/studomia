package com.studomia.studomia.services.Impl;

import com.studomia.studomia.dao.RoleRepository;
import com.studomia.studomia.dto.response.StudentResponse;
import com.studomia.studomia.dto.request.signup.Student;
import com.studomia.studomia.dao.StudentRepository;
import com.studomia.studomia.exceptions.NotFoundException;
import com.studomia.studomia.services.StudentServices;
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
public class StudentServicesImpl implements StudentServices {

    Logger logger= LoggerFactory.getLogger(StudentServicesImpl.class);

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    DomainConverter domainConverter;
    @Autowired
    RoleRepository roleRepository;

//    public StudentServicesImpl(@Qualifier("studomiaDatasource") StudentRepository datasource)
//    {
//        studentRepository = datasource;
//    }

    public List<StudentResponse> getStudents() throws IOException
    {
        List<StudentResponse>  students= new ArrayList<StudentResponse>();

        for( com.studomia.studomia.dao.entities.Student studentDao : studentRepository.findAll())
        {
              StudentResponse studentResponse= new StudentResponse();
              domainConverter.convertBtwDtoDao(studentDao,studentResponse,com.studomia.studomia.dao.entities.Student.class,StudentResponse.class);
              students.add(studentResponse);
        }

        return students;
    }

    public StudentResponse addStudent(Student studentDto) throws IOException
    {
        com.studomia.studomia.dao.entities.Student studentDao = new com.studomia.studomia.dao.entities.Student();

        logger.info("Noah:" + studentDto.toString());

        domainConverter.convertBtwDtoDao(studentDto,studentDao,Student.class, com.studomia.studomia.dao.entities.Student.class);

        logger.info("temi:" + studentDao.toString());

         studentRepository.save(studentDao ) ;

         StudentResponse studentResponse=new StudentResponse();

        domainConverter.convertBtwDtoDao(studentDao,studentResponse,com.studomia.studomia.dao.entities.Student.class,StudentResponse.class);

        return studentResponse;
    }

//    @Override
//    public String deleteStudent(Student student) {
//        return null;
//    }

    public String deleteStudent( Long studentId) throws NotFoundException
    {
        Optional<com.studomia.studomia.dao.entities.Student> studentOpt= studentRepository.findById(studentId);

        if(!studentOpt.isPresent())
            throw new NotFoundException("Student not found");

        studentRepository.deleteById(studentId);

         return "deleted";
    }

    @Override
    public StudentResponse editStudent( Student student,Long studentId) throws IOException,NotFoundException

    {
        Optional<com.studomia.studomia.dao.entities.Student> studentOpt= studentRepository.findById(studentId);


        if(!studentOpt.isPresent())
            throw new NotFoundException("Student not found");


        com.studomia.studomia.dao.entities.Student studentDao = studentOpt.get();

        domainConverter.convertBtwDtoDao(student,studentDao,Student.class,com.studomia.studomia.dao.entities.Student.class);

        studentDao.setStudentId(studentId);

        studentRepository.save(studentDao);

        StudentResponse studentResponse = new StudentResponse();

        domainConverter.convertBtwDtoDao(studentDao,studentResponse,com.studomia.studomia.dao.entities.Student.class,StudentResponse.class);

         return studentResponse;
    }

    public Optional<StudentResponse> getStudent( Long id) throws  NotFoundException,IOException
    {

        StudentResponse studentResponse = new StudentResponse();

        Optional<com.studomia.studomia.dao.entities.Student> studentOpt=studentRepository.findById(id);


        if(!studentOpt.isPresent())
            throw new NotFoundException("Student not found");

        com.studomia.studomia.dao.entities.Student studentDao = studentOpt.get();

        domainConverter.convertBtwDtoDao(studentDao, studentResponse,com.studomia.studomia.dao.entities.Student.class,StudentResponse.class);

        Optional<StudentResponse> optional= Optional.of(studentResponse);

        return optional;//.orElseThrow(new Exception(""));
    }

    @Override
    public StudentResponse assignStudentToRole(Long studentId, Long roleId) throws NotFoundException, IOException {

        Optional<com.studomia.studomia.dao.entities.Student> studentOpt = studentRepository.findById(studentId);

        logger.info("Service: fetching student from repository. student Id:" + studentId);

        Optional<com.studomia.studomia.dao.entities.Role> roleOpt  = roleRepository.findById(roleId);

        logger.info("Service: fetching role from repository. role Id:" + roleId);

        if(!studentOpt.isPresent() )
            throw new NotFoundException("Student Not Found ,id=" + studentId);

        if(!roleOpt.isPresent())
            throw new NotFoundException("Role Not Found ,id=" + roleId);

        com.studomia.studomia.dao.entities.Student studentDao = studentOpt.get();

        com.studomia.studomia.dao.entities.Role roleDao = roleOpt.get();

        studentDao.getRoles().add(roleDao);

        studentRepository.save(studentDao);

        logger.info("Service: assigned student to role");

        StudentResponse studentResponse = new StudentResponse();

        domainConverter.convertBtwDtoDao(studentDao,studentResponse,com.studomia.studomia.dao.entities.Student.class,StudentResponse.class);

        return studentResponse;

    }


//    private   com.studomia.studomia.dao.entities.Student getStudentDao(Student student ){
//        com.studomia.studomia.dao.entities.Student studentDao = new com.studomia.studomia.dao.entities.Student();
//        BeanUtils.copyProperties(student,studentDao);
//
//            return studentDao;
//    }
//
//    private Optional<Student> getStudentDTO(Optional<com.studomia.studomia.dao.entities.Student> studentDao){
//        Student student = new Student();
//
//        BeanUtils.copyProperties(studentDao , student);
//
//
//        return Optional.of(student);
//
//    }
}
