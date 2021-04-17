package com.studomia.studomia.dao.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="payment")
public class Payment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="payment_id")
    private long paymentId;

    @Column(name="method")
    private String method;
}
