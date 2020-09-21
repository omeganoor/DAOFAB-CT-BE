package com.rest.assignment.daofab.controller;

import com.rest.assignment.daofab.dao.Child;
import com.rest.assignment.daofab.dao.constans.ApiPath;
import com.rest.assignment.daofab.service.ChildService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(ApiPath.CHILD)
@Slf4j
@Api(value = "Parent Controller")
public class ChildController {

    private ChildService childService;

    public ChildController(ChildService childService) {
        this.childService = childService;
    }

    @ApiOperation(value = "Get.Child", notes = "Put all mandatory parameter")
    @GetMapping
    public ResponseEntity<List<Child>> getChildData() {

        return ResponseEntity.ok(new ArrayList<>());
    }
}
