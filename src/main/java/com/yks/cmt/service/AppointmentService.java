package com.yks.cmt.service;

import com.yks.cmt.dto.AppointmentVO;
import com.yks.cmt.entity.Appointment;
import com.yks.cmt.entity.Medicine;
import com.yks.cmt.repository.AppointmentRepository;
import com.yks.cmt.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository repository;

    @PersistenceContext
    private EntityManager em;

    public ResponseEntity<?> getAllAppointment(){
        List<AppointmentVO> resultList;
        String sql = "select\n" +
                "\ta.appointment_id,\n" +
                "\ta.appointment_status,\n" +
                "\td.doctor_id,\n" +
                "\td.doctor_name,\n" +
                "\tdep.department_name,\n" +
                "\tw.user_id,\n" +
                "\tw.user_name,\n" +
                "\ta.create_time,\n" +
                "\ta.update_time,\n" +
                "\ta.appointment_date,\n" +
                "\ta.is_deleted\n" +
                "from\n" +
                "\tcmt.appointment a\n" +
                "left join cmt.web_user w on\n" +
                "\ta.user_id = w.user_id\n" +
                "left join cmt.doctor d on\n" +
                "\ta.doctor_id = d.doctor_id\n" +
                "left join cmt.department dep on\n" +
                "\td.department_id = dep.department_id ";
        Query nativeQuery = em.createNativeQuery(sql);
        resultList = nativeQuery.getResultList();
        return ResponseEntity.ok(resultList);
    }

    public ResponseEntity<?> getAppointmentById(int id){
        return ResponseEntity.ok(repository.findByAppointmentId(id));
    }
    public ResponseEntity<?> getAppointmentByUserId(String userId){
        return ResponseEntity.ok(repository.findByUserIdAndIsDeleted(userId,0));
    }

    public ResponseEntity<?> addAppointment(Appointment appointment){
        appointment.setAppointmentStatus("To be completed");
        appointment.setUpdateTime(new Date());
        appointment.setCreateTime(new Date());
        appointment.setIsDeleted(0);
        return ResponseEntity.ok(repository.save(appointment));
    }

    public ResponseEntity<?> updateAppointment(Appointment appointment){
        appointment.setCreateTime(new Date());
        return ResponseEntity.ok(repository.save(appointment));
    }

    public ResponseEntity<?> deleteAppointment(int id){

        return ResponseEntity.ok(repository.deletedById(id));
    }

}
