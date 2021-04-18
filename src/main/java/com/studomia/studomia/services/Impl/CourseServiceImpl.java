package com.studomia.studomia.services.Impl;

import com.studomia.studomia.dao.CourseRepository;
import com.studomia.studomia.dao.ExpertRepository;
import com.studomia.studomia.dao.StudentRepository;
import com.studomia.studomia.dto.request.Course;
import com.studomia.studomia.dto.response.CourseResponse;
import com.studomia.studomia.services.CourseServices;
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
public class CourseServiceImpl implements CourseServices {

    Logger log = LoggerFactory.getLogger(CourseServiceImpl.class);

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    DomainConverter domainConverter;

    @Autowired
    ExpertRepository expertRepository;

    @Autowired
    StudentRepository studentRepository;


    @Override
    public List<CourseResponse> getCourses() throws IOException
    {

         List<CourseResponse>  courseResonses= new ArrayList<CourseResponse>();

         for( com.studomia.studomia.dao.entities.Course courseDao : courseRepository.findAll() )
        {
            CourseResponse courseResponse = new CourseResponse();
            domainConverter.convertBtwDtoDao( courseDao , courseResponse,com.studomia.studomia.dao.entities.Course.class,CourseResponse.class);

            courseResonses.add(courseResponse);
        }

        return courseResonses;
    }

    @Override
    public CourseResponse addCourse(Course course) throws IOException
    {

        com.studomia.studomia.dao.entities.Course courseDao  =new com.studomia.studomia.dao.entities.Course();

        domainConverter.convertBtwDtoDao(course,courseDao ,Course.class,com.studomia.studomia.dao.entities.Course.class);

        System.out.println(courseDao);

        courseRepository.save(courseDao);

        CourseResponse courseResponse=new CourseResponse();

        domainConverter.convertBtwDtoDao(courseDao , course,com.studomia.studomia.dao.entities.Course.class,CourseResponse.class );

        return courseResponse;
    }

    @Override
    public String deleteCourse(Long courseId) throws NotFoundException
    {
        Optional<com.studomia.studomia.dao.entities.Course> course = courseRepository.findById(courseId);

        if(!course.isPresent())
            throw new NotFoundException("Service: course not found, course id:"+ courseId);

        courseRepository.deleteById(courseId);

        return "deleted";
    }

    @Override
    public CourseResponse editCourse(Course course,Long courseId) throws IOException,NotFoundException
    {

           Optional<com.studomia.studomia.dao.entities.Course> courseOpt = courseRepository.findById(courseId);

           if(!courseOpt.isPresent())
               throw new NotFoundException("Service: course not Found in repo");

           com.studomia.studomia.dao.entities.Course courseDao = courseOpt.get();

           courseId = courseDao.getCourseId();

           domainConverter.convertBtwDtoDao(course,courseDao,Course.class,com.studomia.studomia.dao.entities.Course.class);

           courseDao.setCourseId(courseId);

           courseRepository.save(courseDao);

           CourseResponse courseResponse=new CourseResponse();

           domainConverter.convertBtwDtoDao(courseDao,courseResponse,com.studomia.studomia.dao.entities.Course.class,CourseResponse.class);

           return courseResponse;
    }

    @Override
    public CourseResponse getCourse(Long courseId) throws NotFoundException,IOException
    {

        Optional<com.studomia.studomia.dao.entities.Course> courseOpt= courseRepository.findById(courseId);

        if(!courseOpt.isPresent())
            throw new NotFoundException("Course not Found");

        com.studomia.studomia.dao.entities.Course courseDao = courseOpt.get();

        CourseResponse courseResponse = new CourseResponse(); //course request obj

        domainConverter.convertBtwDtoDao(courseDao ,courseResponse,com.studomia.studomia.dao.entities.Course.class,CourseResponse.class);

       return courseResponse;
    }

    @Override
    public CourseResponse assignCourseToExpert(Long courseId , Long expertId) throws NotFoundException, IOException
     {

         Optional<com.studomia.studomia.dao.entities.Course> courseOpt = courseRepository.findById(courseId);

         log.info("Service: fetching course from repository. course Id:" + courseId);

         Optional<com.studomia.studomia.dao.entities.Expert> expertOpt  = expertRepository.findById(expertId);

         log.info("Service: fetching expert from repository. expert Id:" + expertId);

         if(!courseOpt.isPresent() )
              throw new NotFoundException("Course Not Found ,id=" + courseId);

         if(!expertOpt.isPresent())
             throw new NotFoundException("Expert Not Found ,id=" + expertId);

         com.studomia.studomia.dao.entities.Course courseDao = courseOpt.get();

         com.studomia.studomia.dao.entities.Expert expertDao = expertOpt.get();

         courseDao.setExpert(expertDao);

         courseRepository.save(courseDao);

         log.info("Service: assigned expert to course");

         CourseResponse courseResponse = new CourseResponse();

         domainConverter.convertBtwDtoDao(courseDao,courseResponse,com.studomia.studomia.dao.entities.Course.class,CourseResponse.class);

         return courseResponse;
     }

     public CourseResponse addCourseToStudent(Long courseId , Long studentId) throws IOException,NotFoundException
     {
         Optional<com.studomia.studomia.dao.entities.Course> courseOpt = courseRepository.findById(courseId);

         log.info("Service: fetching course from repository. course Id:" + courseId);

         Optional<com.studomia.studomia.dao.entities.Student> studentOpt  = studentRepository.findById(studentId);

         log.info("Service: fetching student from repository. student Id:" + studentId);

         if(!courseOpt.isPresent() )
             throw new NotFoundException("Course Not Found ,id=" + courseId);

         if(!studentOpt.isPresent())
             throw new NotFoundException("Student Not Found ,id=" + studentId);

         com.studomia.studomia.dao.entities.Course courseDao = courseOpt.get();

         com.studomia.studomia.dao.entities.Student studentDao = studentOpt.get();

         courseDao.getEnrolledStudents().add(studentDao);

         courseRepository.save(courseDao);

         log.info("Service: enrolled student to course");

         CourseResponse courseResponse = new CourseResponse();

         domainConverter.convertBtwDtoDao(courseDao,courseResponse,com.studomia.studomia.dao.entities.Course.class,CourseResponse.class);

         return courseResponse;
     }

}
