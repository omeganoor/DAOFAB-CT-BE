package com.rest.assignment.daofab.controller;

import com.rest.assignment.daofab.service.ChildService;

public class ChildController {

    private ChildService childService;

    public ChildController(ChildService childService) {
        this.childService = childService;
    }
}
