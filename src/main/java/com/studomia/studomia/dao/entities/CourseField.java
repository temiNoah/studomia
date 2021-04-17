package com.studomia.studomia.dao.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="course_field")
public class CourseField {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="course_field_id")
    private long course_field_id;

    @Column(name="field")
    private String field; // medicine ,art ,music etc
}
