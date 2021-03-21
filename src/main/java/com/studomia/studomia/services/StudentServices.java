package com.studomia.studomia.services;

import com.studomia.studomia.dto.request.Student;

import java.util.List;
import java.util.Optional;

public interface StudentServices {

    public List<Student> getStudents();
    public Student addStudent(Student student);
    public String deleteStudent( Student student);
    public String editStudent(  Student student);
    public Optional<Student> getStudent(String id);
}
