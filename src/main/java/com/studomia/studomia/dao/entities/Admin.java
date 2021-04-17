package com.studomia.studomia.dao.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="admin")
public class Admin {


    private static final long serialVersionUID= -3009157732242241606L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="admin_id")
    private long adminId;

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

    @ManyToMany(targetEntity = Role.class, cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH} )
    @JoinTable(
            name = "t_admin_role",
            joinColumns = @JoinColumn(name = "admin_id", updatable=true ,referencedColumnName = "admin_id",nullable = true),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "role_id",updatable = true,nullable = true )
    )
    private Set<Role> roles;


    public Set<Role> getRoles() {
        return roles;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }
    public String getUsername()
    {
        return this.username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getPassword()
    {
        return this.password;
    }



    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long id) {
        this.adminId = id;
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

    @Override
    public String toString(){
        return String.format("Admin[id=%d , first name='%s' , last name='%s' , age='%d']",adminId,firstName,lastName,age);
    }
}
