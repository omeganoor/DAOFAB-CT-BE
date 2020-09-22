package com.rest.assignment.daofab.controller;

import com.rest.assignment.daofab.dao.ChildPojoRequest;
import com.rest.assignment.daofab.dao.ChildPojoResponse;
import com.rest.assignment.daofab.dao.constans.ApiPath;
import com.rest.assignment.daofab.service.ChildService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping(ApiPath.CHILD)
@Slf4j
@Api(value = "Child Controller")
public class ChildController {

    private ChildService childService;

    public ChildController(ChildService childService) {
        this.childService = childService;
    }

    @ApiOperation(value = "Get.getAllChild", notes = "Put all mandatory parameter")
    @GetMapping
    public ResponseEntity<List<ChildPojoResponse>> getChildData() {
        return ResponseEntity.ok(childService.findAll());
    }

    @ApiOperation(value = "Get.getAllChildByParentId", notes = "Put all mandatory parameter")
    @GetMapping(ApiPath.PARENT_ID)
    public ResponseEntity<List<ChildPojoResponse>> getAllChildByParentId(@PathVariable long id) {
        log.debug("REST request to get Child By Parent Id : {}", id);
        List<ChildPojoResponse> childPojoResponse = childService.findAllByParentId(id);
        if (childPojoResponse == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(childPojoResponse);
    }

    @ApiOperation(value = "Create.ChildData", notes = "Put all mandatory parameter")
    @PostMapping
    public ResponseEntity<ChildPojoRequest> createChild(@RequestBody ChildPojoRequest childPojoRequest)
            throws URISyntaxException {

        if (childPojoRequest.getId() != null){
            return ResponseEntity.badRequest().build();
        }
        ChildPojoRequest result = childService.save(childPojoRequest);
        return ResponseEntity.created(new URI(ApiPath.CHILD + result.getId())).body(result);
    }

    @ApiOperation(value = "Update.ChildData", notes = "Put all mandatory parameter")
    @PutMapping(ApiPath.ID)
    public ResponseEntity<ChildPojoRequest> updateChild(@PathVariable long id, @RequestBody ChildPojoRequest childPojoRequest) {
        childPojoRequest.setId(id);
        ChildPojoRequest result = childService.save(childPojoRequest);
        if (result == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @ApiOperation(value = "Get.ChildById", notes = "Put all mandatory parameter")
    @GetMapping(ApiPath.ID)
    public ResponseEntity<ChildPojoResponse> getChildById(@PathVariable long id) {
        log.debug("REST request to get Child By Id : {}", id);
        ChildPojoResponse childPojoResponse = childService.findOneById(id);
        if (childPojoResponse == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(childPojoResponse);
    }

    @ApiOperation(value = "Delete.ChildDataById", notes = "Put all mandatory parameter")
    @DeleteMapping(ApiPath.ID)
    public ResponseEntity<Void> deleteChildById(@PathVariable long id) {
        log.debug("REST request to delete Child by Id : {}", id);
        childService.delete(id);
        return ResponseEntity.ok().build();
    }
}
