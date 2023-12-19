package com.yks.cmt.service;

import com.yks.cmt.entity.Doctor;
import com.yks.cmt.entity.Medicine;
import com.yks.cmt.repository.DoctorRepository;
import com.yks.cmt.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MedicineService {
    @Autowired
    private MedicineRepository repository;

    public ResponseEntity<?> getAllMedicine(){
        return ResponseEntity.ok(repository.findAll());
    }
    public ResponseEntity<?> getWebMedicine(){
        return ResponseEntity.ok(repository.findByIsDeleted(0));
    }

    public ResponseEntity<?> getMedicineById(int id){
        return ResponseEntity.ok(repository.getMedicineById(id));
    }

    public ResponseEntity<?> addMedicine(Medicine medicine){
        medicine.setCreateTime(new Date());
        medicine.setUpdateTime(new Date());
        medicine.setIsDeleted(0);
        return ResponseEntity.ok(repository.save(medicine));
    }

    public ResponseEntity<?> updateMedicine(Medicine medicine){
        medicine.setUpdateTime(new Date());
        return ResponseEntity.ok(repository.save(medicine));
    }

    public ResponseEntity<?> deleteMedicine(int id){
        return ResponseEntity.ok(repository.deletedById(id));
    }

}
