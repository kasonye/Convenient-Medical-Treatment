package com.yks.cmt.controller;

import com.yks.cmt.entity.Department;
import com.yks.cmt.entity.Position;
import com.yks.cmt.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/position")
public class PositionController {
    @Autowired
    private PositionService positionService;
    @GetMapping
    public ResponseEntity<?> getAllDepartment(){
        return ResponseEntity.ok(positionService.getAllPostion());
    }
    @GetMapping("/isDeleted")
    public ResponseEntity<?> getAllDepartmentByIsDeleted(){
        return ResponseEntity.ok(positionService.getAllPositionByIsDeleted());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable int id){
        return ResponseEntity.ok(positionService.getPositionById(id));
    }
    @PostMapping
    public ResponseEntity<?> addDepartment(@RequestBody Position position){
        return ResponseEntity.status(HttpStatus.CREATED).body(positionService.addPostion(position));
    }
    @PutMapping
    public ResponseEntity<?> updateDepartment(@RequestBody Position position){
        return ResponseEntity.ok(positionService.updatePosition(position));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable int id){
        return ResponseEntity.ok(positionService.deletePosition(id));
    }
}
