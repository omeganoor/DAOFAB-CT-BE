package com.rest.assignment.daofab.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParentPojoRequest {

    private Long id;

    private String sender;

    private String receiver;

    private Long totalAmount;

}
