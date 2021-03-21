package com.studomia.studomia.services.Impl;

import com.studomia.studomia.dto.request.Student;
import com.studomia.studomia.repository.StudentRepository;
import com.studomia.studomia.services.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServicesImpl implements StudentServices {

    @Autowired
    StudentRepository studentRepository;

//    public StudentServicesImpl(@Qualifier("studomiaDatasource") StudentRepository datasource)
//    {
//        studentRepository = datasource;
//    }

    public List<Student> getStudents()
    {
        return (List)studentRepository.findAll();
    }

    public Student addStudent(Student student)
    {

        return studentRepository.save(student);
    }

    public String deleteStudent( Student student)
    {

         studentRepository.delete(student);

         return "deleted";
    }

    public String editStudent( Student student)

    {
        //Student student1= studentRepository.updateById(student);

         return "updated";
    }

    public Optional<Student> getStudent( String id)
    {

       Optional<Student> optional= studentRepository.findById(Long.parseLong(id));

        return optional;//.orElseThrow(new Exception(""));
    }
}
