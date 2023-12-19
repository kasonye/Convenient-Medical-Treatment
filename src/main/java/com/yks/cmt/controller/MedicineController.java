package com.yks.cmt.controller;

import com.yks.cmt.entity.Medicine;
import com.yks.cmt.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicine")
public class MedicineController {
    @Autowired
    private MedicineService medicineService;
    @GetMapping
    public ResponseEntity<?> getAllMedicine(){
        return ResponseEntity.ok(medicineService.getAllMedicine());
    }
    @GetMapping("/web")
    public ResponseEntity<?> getWebMedicine(){
        return ResponseEntity.ok(medicineService.getWebMedicine());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getMedicineById(@PathVariable int id){
        return ResponseEntity.ok(medicineService.getMedicineById(id));
    }
    @PostMapping
    public ResponseEntity<?> addMedicine(@RequestBody Medicine medicine){
        return ResponseEntity.ok(medicineService.addMedicine(medicine));
    }
    @PutMapping
    public ResponseEntity<?> updateMedicine(@RequestBody Medicine medicine){
        return ResponseEntity.ok(medicineService.updateMedicine(medicine));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMedicine(@PathVariable int id){
        return ResponseEntity.ok(medicineService.deleteMedicine(id));
    }
}
