package com.rest.assignment.daofab.service;

import com.rest.assignment.daofab.repository.ParentRepository;
import org.springframework.stereotype.Service;

@Service
public class ParentService {

    private ParentRepository parentRepository;

    public ParentService(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }
}

