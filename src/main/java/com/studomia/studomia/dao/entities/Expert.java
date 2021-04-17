package com.studomia.studomia.dao.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.studomia.studomia.dao.User;
import com.studomia.studomia.dto.request.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@Data
@Entity
@Table(name="Expert")
public class Expert extends User
{
    //private static final long serialVersionUID= -3009157732242241606L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="expert_id")
    private long expertId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="phone_number")
    private String phoneNumber ;

    @Column(name="age")
    private Integer age;

    @Column(name="username")
    protected String username;

    @Column(name="password")
    protected String password;

    @JsonIgnore
    @OneToMany(mappedBy = "expert")
    private Set<Course> courses;

    @ManyToMany(targetEntity = Role.class, cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH} )
    @JoinTable(
            name = "t_expert_role",
            joinColumns = @JoinColumn(name = "expert_id", updatable=true ,referencedColumnName = "expert_id",nullable = true),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "role_id",updatable = true,nullable = true )
    )
    private Set<Role> roles;


    public Expert()
    {}
    public Set<Role> getRoles() {
        return roles;
    }

    public void setUsername(String username)
    {
         this.username=username;
    }

    public String getUsername()
    {
        return this.username;
    }

    public void setPassword(String password)
    {
        this.password= password;
    }
    public String getPassword()
    {
        return this.password;
    }
    public long getExpertId() {
        return expertId;
    }

    public void setExpertId(long expertId) {
        this.expertId = expertId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString(){
        return String.format("Expert[id=%d , first name='%s' , last name='%s' , age='%d']",expertId,firstName,lastName,age);
    }

}
