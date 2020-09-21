package com.rest.assignment.daofab.service;

import com.rest.assignment.daofab.repository.ChildRepository;
import org.springframework.stereotype.Service;

@Service
public class ChildService {
    private ChildRepository childRepository;

    public ChildService(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }
}
