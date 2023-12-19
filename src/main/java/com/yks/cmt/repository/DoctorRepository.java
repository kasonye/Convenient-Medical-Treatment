package com.yks.cmt.repository;

import com.yks.cmt.entity.Doctor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
public interface DoctorRepository extends CrudRepository<Doctor, String> {
    @Query(value = "select * from doctor where doctor_id = :id",nativeQuery = true)
    public Doctor getDoctorById(@Param("id") String id);
    public List<Doctor> findByIsDeleted(int isDeleted);
    public Doctor findByDoctorIdAndIsDeleted(String id,int isDeleted);

    @Query(value = "select * from doctor where department_id = :departmentId and is_deleted = '0'",nativeQuery = true)
    public List<Doctor> getByDepartmentIdAndIsDeleted(int departmentId);

    @Modifying
    @Query(value = "update doctor set is_deleted = '1' where doctor_id = :id",nativeQuery = true)
    public int deletedById(@Param("id") String id);
}
