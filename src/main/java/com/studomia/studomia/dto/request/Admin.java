package com.studomia.studomia.dto.request;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="admin")
public class Admin {


    private static final long serialVersionUID= -3009157732242241606L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="phone_number")
    private String phoneNumber ;

    @Column(name="age")
    private String age;

    @Override
    public String toString(){
        return String.format("Admin[id=%d , first name='%s' , last name='%s' , age='%d']",id,firstName,lastName,age);
    }
}
