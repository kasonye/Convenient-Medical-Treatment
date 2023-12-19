package com.yks.cmt.controller;


import com.yks.cmt.entity.Department;
import com.yks.cmt.entity.WebUser;
import com.yks.cmt.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService service;
    @GetMapping
    public ResponseEntity<?> getAllDepartment(){
        return ResponseEntity.ok(service.getAllDepartment());
    }
    @GetMapping("/isDeleted")
    public ResponseEntity<?> getAllDepartmentByIsDeleted(){
        return ResponseEntity.ok(service.getAllDepartmentByIsDeleted());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable int id){
        return ResponseEntity.ok(service.getDepartmentById(id));
    }
    @PostMapping
    public ResponseEntity<?> addDepartment(@RequestBody Department department){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addDepartment(department));
    }
    @PutMapping
    public ResponseEntity<?> updateDepartment(@RequestBody Department department){
        return ResponseEntity.ok(service.updateDepartment(department));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable int id){
        return ResponseEntity.ok(service.deleteDepartment(id));
    }
}
