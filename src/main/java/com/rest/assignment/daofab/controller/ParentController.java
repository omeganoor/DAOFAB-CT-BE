package com.rest.assignment.daofab.controller;

import com.rest.assignment.daofab.dao.ParentPojoRequest;
import com.rest.assignment.daofab.dao.ParentPojoResponse;
import com.rest.assignment.daofab.dao.constans.ApiPath;
import com.rest.assignment.daofab.service.ParentService;
import com.rest.assignment.daofab.utility.PageableHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(ApiPath.PARENT)
@Slf4j
@Api(value = "ParentPojoResponse Controller")
public class ParentController {

    private ParentService parentService;

    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    @ApiOperation(value = "Get.ParentPojoResponse", notes = "Put all mandatory parameter")
    @GetMapping
    public ResponseEntity<Page<ParentPojoResponse>> getParentData(
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Sort.Direction direction,
            @RequestParam(required = false) String sortedField
    ) {
        Pageable pageable = PageableHelper.setPageable(pageNumber, size, direction, sortedField);
        Page<ParentPojoResponse> parentPage = this.parentService.findAll(pageable);
        return ResponseEntity.ok(parentPage);
    }

    @ApiOperation(value = "Create.ParentPojoResponse", notes = "Put all mandatory parameter")
    @PostMapping
    public ResponseEntity<ParentPojoRequest> createParent(@RequestBody ParentPojoRequest parentPojoRequest)
            throws URISyntaxException {
        log.debug("REST request to Insert Parent : {}");

        if (parentPojoRequest.getId() != null){
            return ResponseEntity.badRequest().build();
        }
        ParentPojoRequest result = parentService.save(parentPojoRequest);
        return ResponseEntity.created(new URI(ApiPath.PARENT + result.getId())).body(result);
    }

    @ApiOperation(value = "Update.ParentPojoResponse", notes = "Put all mandatory parameter")
    @PutMapping(ApiPath.ID)
    public ResponseEntity<ParentPojoRequest> updateParent(@PathVariable long id, @RequestBody ParentPojoRequest parentPojoRequest)
            throws URISyntaxException {
        log.debug("REST request to Update Parent by Id : {}, {}",id , parentPojoRequest);
        parentPojoRequest.setId(id);
        ParentPojoRequest result = parentService.save(parentPojoRequest);
        if (result == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @ApiOperation(value = "Get.ParentPojoById", notes = "Put all mandatory parameter")
    @GetMapping(ApiPath.ID)
    public ResponseEntity<ParentPojoResponse> getParentById(@PathVariable long id) {
        log.debug("REST request to get Parent By Id : {}", id);
        ParentPojoResponse parentPojoResponse = parentService.findOneById(id);
        if (parentPojoResponse == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(parentPojoResponse);
    }

    @ApiOperation(value = "Delete.ParentPojoResponse", notes = "Put all mandatory parameter")
    @DeleteMapping(ApiPath.ID)
    public ResponseEntity<Void> deleteParentById(@PathVariable long id) {
        log.debug("REST request to delete Parent by Id : {}", id);
        parentService.delete(id);
        return ResponseEntity.ok().build();
    }
}
