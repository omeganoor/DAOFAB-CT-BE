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
public class ChildPojoResponse {

    private Long id;

    private Long parentId;

    private Long paidAmount;

    private String sender;

    private String receiver;

    private Long totalAmount;
}
