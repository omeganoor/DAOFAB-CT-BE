package com.rest.assignment.daofab.dao;

import com.rest.assignment.daofab.entity.Child;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChildPojoRequest {

    private Long id;

    private Long parentId;

    private Long paidAmount;

}
