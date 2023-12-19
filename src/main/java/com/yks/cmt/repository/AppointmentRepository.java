package com.yks.cmt.repository;

import com.yks.cmt.dto.AppointmentVO;
import com.yks.cmt.entity.Appointment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Repository
public interface AppointmentRepository extends CrudRepository<Appointment,Integer> {
    @Query(value = "select * from appointment where appointment_id = :id",nativeQuery = true)
    public Appointment findByAppointmentId(@Param("id") int id);
    public Appointment findByAppointmentIdAndIsDeleted(int id,int deleted);
    public List<Appointment> findByUserIdAndIsDeleted(String userId,int isDeleted);
    @Modifying
    @Query(value = "update appointment set is_deleted = '1' where appointment_id = :id",nativeQuery = true)
    public int deletedById(@Param("id") int id);
}
