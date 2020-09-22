package com.rest.assignment.daofab.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "parent")
public class Parent {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private String sender;

    private String receiver;

    private long totalAmount;
}
