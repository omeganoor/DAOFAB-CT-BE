package com.rest.assignment.daofab.repository;

import com.rest.assignment.daofab.entity.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {

    long countPaidAmountByParentId(long parentId);

    List<Child> findAllByParentId(long id);

    Child findOneById(Long id);
}
