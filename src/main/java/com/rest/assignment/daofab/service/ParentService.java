package com.rest.assignment.daofab.service;

import com.rest.assignment.daofab.dao.ParentPojoRequest;
import com.rest.assignment.daofab.dao.ParentPojoResponse;
import com.rest.assignment.daofab.entity.Parent;
import com.rest.assignment.daofab.repository.ParentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParentService {

    private ParentRepository parentRepository;

    private ChildService childService;

    public ParentService(ParentRepository parentRepository, ChildService childService) {
        this.parentRepository = parentRepository;
        this.childService = childService;
    }

    public Page<ParentPojoResponse> findAll(Pageable pageable) {
        List<ParentPojoResponse> parentsData = new ArrayList<>();

        Page<Parent> parents = parentRepository.findAll(pageable);
        for (Parent parent : parents.getContent()) {
            long totalPaidAmount = childService.getTotalAmountById(parent.getId());
            ParentPojoResponse parPojo = parentMapper(parent, totalPaidAmount);
            parentsData.add(parPojo);
        }
        Page<ParentPojoResponse> resultPage = new PageImpl<>(parentsData, pageable, parents.getTotalElements());
        return resultPage;
    }

    public ParentPojoRequest save(ParentPojoRequest parentPojoRequest) {
        Parent parent;
        if (parentPojoRequest.getId() != null){
            parent = parentRepository.findOneById(parentPojoRequest.getId());
            if (parent == null){
                return null;
            }
            parent.setSender(parentPojoRequest.getSender() == null ? parent.getSender() : parentPojoRequest.getSender());
            parent.setReceiver(parentPojoRequest.getReceiver() == null ? parent.getReceiver() : parentPojoRequest.getReceiver());
            parent.setTotalAmount(parentPojoRequest.getTotalAmount() == null ? parent.getTotalAmount() : parentPojoRequest.getTotalAmount());

        }else{
            parent = Parent.builder()
                    .sender(parentPojoRequest.getSender())
                    .receiver(parentPojoRequest.getReceiver())
                    .totalAmount(parentPojoRequest.getTotalAmount())
                    .build();
        }

        Parent result = parentRepository.save(parent);
        return parentPojoRequest.builder()
                .id(result.getId())
                .receiver(result.getReceiver())
                .sender(result.getSender())
                .totalAmount(result.getTotalAmount())
                .build();
    }


    public ParentPojoResponse findOneById(long id) {
        Parent parent = parentRepository.findOneById(id);
        if(parent == null){
            return null;
        }
        long paidAmount = childService.getTotalAmountById(id);
        return parentMapper(parent, paidAmount);
    }

    public void delete(long id) {
        parentRepository.deleteById(id);
    }

    private ParentPojoResponse parentMapper(Parent parent, long totalPaidAmount){
        return ParentPojoResponse.builder()
                .id(parent.getId())
                .sender(parent.getSender())
                .receiver(parent.getReceiver())
                .totalAmount(parent.getTotalAmount())
                .totalPaidAmount(totalPaidAmount)
                .build();
    }
}

