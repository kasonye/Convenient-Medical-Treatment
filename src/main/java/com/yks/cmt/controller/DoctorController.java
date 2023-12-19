package com.yks.cmt.controller;


import com.yks.cmt.entity.Doctor;
import com.yks.cmt.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @GetMapping
    public ResponseEntity<?> getAllDoctor(){
        return ResponseEntity.ok(doctorService.getAllDoctor());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getDoctorById(@PathVariable String id){
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }
    @GetMapping("/web")
        public ResponseEntity<?> getDoctorWeb(){
            return ResponseEntity.ok(doctorService.getDoctors_Web());
    }
    @GetMapping("/web/{departmentId}")
    public ResponseEntity<?> getDoctorByDepartmentWeb(@PathVariable("departmentId") int departmentId){
        return ResponseEntity.ok(doctorService.getDoctorByDepartmentIdWeb(departmentId));
    }
    @GetMapping(value = "department/{departmentName}")
    public ResponseEntity<?> getDoctorByDepartmentId(@PathVariable String departmentName){
        return ResponseEntity.ok(doctorService.getDoctorByDepartmentName(departmentName));
    }
    @PostMapping
    public ResponseEntity<?> addDoctor(@RequestBody Doctor doctor){
        return ResponseEntity.ok(doctorService.addDoctor(doctor));
    }
    @PutMapping
    public ResponseEntity<?> updateDoctor(@RequestBody Doctor doctor){
        System.out.println(doctor);
        return ResponseEntity.ok(doctorService.updateDoctor(doctor));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable String id){
        return ResponseEntity.ok(doctorService.deleteDoctor(id));
    }
}
