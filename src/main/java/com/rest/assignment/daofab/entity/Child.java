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
@Table(name = "child")
public class Child {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private long parentId;

    private long paidAmount;
}
