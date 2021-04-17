package com.studomia.studomia.dao.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="fee")
public class Fee {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="fee_id")
    private long feeId;

    @Column(name="amount")
    private long amount;


}
