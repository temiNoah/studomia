package com.studomia.studomia.dao.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="regular_Login")
public class RegularLogin {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="regular_login_id")
    private String regularLoginId;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;
}
