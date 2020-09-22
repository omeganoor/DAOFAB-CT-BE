package com.rest.assignment.daofab.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParentPojoResponse {

    private Long id;

    private String sender;

    private String receiver;

    private Long totalAmount;

    private Long totalPaidAmount;

}
