package com.studomia.studomia.dao.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.studomia.studomia.dto.request.Request;
import com.studomia.studomia.security.socialLogin.AuthProvider;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@AllArgsConstructor
@Data
@Entity
@Table(name="Student")
public class Student extends User
{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="student_id")
    private long studentId;
//
//    @Column(name="first_name")
//    private String firstName;
//
//    @Column(name="last_name")
//    private String lastName;
//
//    @Column(name="email",unique = true)
//    private String email;
//
//    @Column(name="phone_number")
//    private String phoneNumber ;
//
//    @Column(name="age")
//    private Integer age;
//
//    @Column(name="username")
//    protected String username;
//
//    @Column(name="password")
//    protected String password;

    @JsonIgnore
    @ManyToMany(mappedBy = "enrolledStudents")
    private Set<Course> courses = new HashSet<>();

    @ManyToMany(targetEntity = Role.class, cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH} )
    @JoinTable(
            name = "student_role",
            joinColumns = @JoinColumn(name = "student_id", updatable=true ,referencedColumnName = "student_id",nullable = true),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "role_id",updatable = true,nullable = true )
    )
    private Set<Role> roles;



    //@Override
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }
}
