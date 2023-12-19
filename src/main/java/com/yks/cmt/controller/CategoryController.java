package com.yks.cmt.controller;

import com.yks.cmt.entity.Category;
import com.yks.cmt.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService service;
    @GetMapping
    public ResponseEntity<?> getAllCategory(){
        return ResponseEntity.ok(service.getAllCategory());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable int id){
        return ResponseEntity.ok(service.getCategoryById(id));
    }
    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody Category category){
        return ResponseEntity.ok(service.addCategory(category));
    }
}
