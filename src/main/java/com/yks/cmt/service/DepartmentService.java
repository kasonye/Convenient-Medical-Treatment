package com.yks.cmt.service;

import com.yks.cmt.entity.Department;
import com.yks.cmt.entity.WebUser;
import com.yks.cmt.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository repository;
    @PersistenceContext
    private EntityManager em;

    public ResponseEntity<?> getAllDepartment(){
        return ResponseEntity.ok(repository.findAll());
    }

    public ResponseEntity<?> getAllDepartmentByIsDeleted(){
        return ResponseEntity.ok(repository.getDepartmentByIsDeleted());
    }

    public ResponseEntity<?> getDepartmentById(int id){
        return ResponseEntity.ok(repository.findById(id).get());
    }

    public ResponseEntity<?> addDepartment(Department department){
        department.setIsDeleted(0);
        return ResponseEntity.ok(repository.save(department));
    }

    public ResponseEntity<?> updateDepartment(Department department){
        return ResponseEntity.ok(repository.save(department));
    }

    public ResponseEntity<?> deleteDepartment(int id){

        return ResponseEntity.ok(repository.deletedById(id));
    }
}
