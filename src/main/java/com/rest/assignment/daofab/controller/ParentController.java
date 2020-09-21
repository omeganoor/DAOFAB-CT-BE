package com.rest.assignment.daofab.controller;

import com.rest.assignment.daofab.dao.Parent;
import com.rest.assignment.daofab.dao.constans.ApiPath;
import com.rest.assignment.daofab.service.ParentService;
import com.rest.assignment.daofab.utility.PageableHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(ApiPath.PARENT)
@Slf4j
@Api(value = "Parent Controller")
public class ParentController {

    private ParentService parentService;

    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    @ApiOperation(value = "Get.Parent", notes = "Put all mandatory parameter")
    @GetMapping
    public ResponseEntity<Page<Parent>> getParentData() {

        List<Parent> parentList = new ArrayList<>();
        Pageable pageable = PageableHelper.setPageable(1, 1, null, null);
        long totalSizeInt = 1;
        return ResponseEntity.ok(new PageImpl<>(parentList, pageable, totalSizeInt));
    }


}
