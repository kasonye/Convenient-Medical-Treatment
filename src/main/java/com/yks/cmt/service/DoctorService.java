package com.yks.cmt.service;

import com.yks.cmt.entity.Doctor;
import com.yks.cmt.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository repository;
    @PersistenceContext
    private EntityManager em;

    public ResponseEntity<?> getAllDoctor(){
        String sql = "SELECT\n" +
                "\td.doctor_id ,\n" +
                "\td.doctor_name,\n" +
                "\tdep.department_name ,\n" +
                "\td.age ,\n" +
                "\td.gender ,\n" +
                "\td.phone ,\n" +
                "\td.create_time,\n" +
                "\td.update_time,\n" +
                "\td.department_id,\n" +
                "\td.is_deleted,\n" +
                "\td.imageUrl,\n" +
                "\tp.position_id,\n" +
                "\tp.position_name\n" +
                "FROM\n" +
                "\tdoctor d\n" +
                "LEFT JOIN department dep on\n" +
                "\td.department_id = dep.department_id LEFT JOIN position p ON p.position_id = d.position_id";
        Query nativeQuery = em.createNativeQuery(sql);
        List<Object[]> resultList = nativeQuery.getResultList();
        return ResponseEntity.ok(resultList);
    }

    public ResponseEntity<?> getDoctors_Web(){
        String sql = "SELECT\n" +
                "\td.doctor_id ,\n" +
                "\td.doctor_name,\n" +
                "\tdep.department_name ,\n" +
                "\td.age ,\n" +
                "\td.gender ,\n" +
                "\td.phone ,\n" +
                "\td.create_time,\n" +
                "\td.update_time,\n" +
                "\td.department_id,\n" +
                "\td.is_deleted,\n" +
                "\td.imageUrl,\n" +
                "\tp.position_id,\n" +
                "\tp.position_name\n" +
                "FROM\n" +
                "\tdoctor d\n" +
                "LEFT JOIN department dep on\n" +
                "\td.department_id = dep.department_id LEFT JOIN position p ON p.position_id = d.position_id where d.is_deleted = '0'";
        Query nativeQuery = em.createNativeQuery(sql);
        List<Object[]> resultList = nativeQuery.getResultList();
        return ResponseEntity.ok(resultList);
    }

    public ResponseEntity<?> getDoctorById(String id){
        return ResponseEntity.ok(repository.getDoctorById(id));
    }

    public ResponseEntity<?> getDoctorByDepartmentIdWeb(int departmentId){
        return ResponseEntity.ok(repository.getByDepartmentIdAndIsDeleted(departmentId));
    }

    public ResponseEntity<?> getDoctorByDepartmentName(String departementName){
        String sql = "SELECT\n" +
                "\td.doctor_id,\n" +
                "\td.doctor_name\n" +
                "FROM\n" +
                "\tdoctor d\n" +
                "LEFT JOIN department dep on\n" +
                "\td.department_id = dep.department_id\n" +
                "where\n" +
                "\tdep.department_name = '"+departementName+"' and d.is_deleted = '0'";
        Query nativeQuery = em.createNativeQuery(sql);
        List resultList = nativeQuery.getResultList();
        return ResponseEntity.ok(resultList);
    }

    public ResponseEntity<?> addDoctor(Doctor doctor){
        doctor.setCreateTime(new Date());
        doctor.setUpdateTime(new Date());
        doctor.setIsDeleted(0);
        return ResponseEntity.ok(repository.save(doctor));
    }

    public ResponseEntity<?> updateDoctor(Doctor doctor){
        doctor.setUpdateTime(new Date());
        return ResponseEntity.ok(repository.save(doctor));
    }

    public ResponseEntity<?> deleteDoctor(String id){

        return ResponseEntity.ok(repository.deletedById(id));
    }

}
