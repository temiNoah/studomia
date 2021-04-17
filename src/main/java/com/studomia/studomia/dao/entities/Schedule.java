package com.studomia.studomia.dao.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="schedule_id")
    private long scheduleId;

    @Column(name="date")
    private Date date;

}
