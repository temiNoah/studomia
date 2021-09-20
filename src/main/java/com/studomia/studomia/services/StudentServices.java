package com.studomia.studomia.services;

import com.studomia.studomia.dto.response.StudentResponse;
import com.studomia.studomia.dto.request.signup.Student;
import com.studomia.studomia.exceptions.NotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface StudentServices {

    public List<StudentResponse> getStudents() throws IOException;
    public StudentResponse addStudent(Student student) throws IOException ;
    public String deleteStudent(Long studentId);
    public StudentResponse editStudent(Student student, Long studentId) throws IOException, NotFoundException;
    public Optional<StudentResponse> getStudent(Long id) throws NotFoundException,IOException;

    public StudentResponse assignStudentToRole(Long expertId , Long roleId) throws NotFoundException, IOException;

    public boolean userExists(String username) throws UsernameNotFoundException;

}
