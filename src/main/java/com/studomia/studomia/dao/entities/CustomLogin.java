package com.studomia.studomia.dao.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="custom_Login")
public class CustomLogin {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="custom_login_id")
    private String customLoginId;

    @Column(name="type")
    private String type; //google ,facebook
}
