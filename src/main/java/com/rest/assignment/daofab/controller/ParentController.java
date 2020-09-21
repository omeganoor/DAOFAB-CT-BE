package com.rest.assignment.daofab.controller;

import com.rest.assignment.daofab.dao.constans.ApiPath;
import com.rest.assignment.daofab.service.ParentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPath.PARENT)
@Slf4j
public class ParentController {

    private ParentService parentService;

    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }
}
