package com.rest.assignment.daofab.service;

import com.rest.assignment.daofab.dao.ChildPojoRequest;
import com.rest.assignment.daofab.dao.ChildPojoResponse;
import com.rest.assignment.daofab.dao.ParentPojoResponse;
import com.rest.assignment.daofab.entity.Child;
import com.rest.assignment.daofab.entity.Parent;
import com.rest.assignment.daofab.repository.ChildRepository;
import com.rest.assignment.daofab.repository.ParentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChildService {
    private ChildRepository childRepository;
    private ParentRepository parentRepository;

    public ChildService(ChildRepository childRepository, ParentRepository parentRepository) {
        this.childRepository = childRepository;
        this.parentRepository = parentRepository;
    }

    public ChildPojoRequest save(ChildPojoRequest childPojoRequest) {
        Child child;
        if (childPojoRequest.getId() != null){
            child = childRepository.findOneById(childPojoRequest.getId());
            if (child == null){
                return null;
            }
            child.setParentId(childPojoRequest.getParentId() == null ? child.getParentId() : childPojoRequest.getParentId());
            child.setPaidAmount(childPojoRequest.getPaidAmount() == null ? child.getPaidAmount() : childPojoRequest.getPaidAmount());

        }else{
            child = Child.builder()
                    .paidAmount(childPojoRequest.getPaidAmount())
                    .parentId(childPojoRequest.getParentId())
                    .build();
        }

        Child result = childRepository.save(child);
        return ChildPojoRequest.builder()
                .id(result.getId())
                .parentId(result.getParentId())
                .paidAmount(result.getPaidAmount())
                .build();    }

    public ChildPojoResponse findOneById(long id) {
        Child child = childRepository.findOneById(id);
        if (child == null){
            return null;
        }
        return childMapper(child, getParentData(child.getParentId()));
    }

    private Parent getParentData(long parentId) {
        Parent parent = parentRepository.findOneById(parentId);
        if (parent == null){
            return new Parent();
        }
        return parent;
    }

    public void delete(long id) {
        childRepository.deleteById(id);
    }

    public Long getTotalAmountById(long id){
        List<Child> childList = childRepository.findAllByParentId(id);
        if (childList == null || childList.isEmpty()){
            return 0L;
        }
        Long totalPaidAmount = 0L;
        for (Child child : childList) {
            totalPaidAmount+=child.getPaidAmount();
        }
        return totalPaidAmount;
    }

    public List<ChildPojoResponse> findAll() {
        List<Child> childList = childRepository.findAll();
        return childListMapper(childList);
    }

    public List<ChildPojoResponse> findAllByParentId(long id) {
        List<Child> childList = childRepository.findAllByParentId(id);
        return childListMapper(childList);
    }

    private List<ChildPojoResponse> childListMapper(List<Child> childList){
        List<ChildPojoResponse> responses = new ArrayList<>();
        for (Child ch : childList) {
            Parent parent = getParentData(ch.getParentId());
            ChildPojoResponse child = childMapper(ch, parent);
            responses.add(child);
        }
        return responses;
    }

    private ChildPojoResponse childMapper(Child child, Parent parent) {
        return ChildPojoResponse.builder()
                .id(child.getId())
                .parentId(child.getParentId())
                .paidAmount(child.getPaidAmount())
                .sender(parent.getSender())
                .receiver(parent.getReceiver())
                .totalAmount(parent.getTotalAmount())
                .build();
    }

}
