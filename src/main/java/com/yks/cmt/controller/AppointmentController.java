package com.yks.cmt.controller;


import com.alibaba.fastjson.JSONObject;
import com.yks.cmt.config.Consumer;
import com.yks.cmt.config.Sender;
import com.yks.cmt.dto.AppointmentVO;
import com.yks.cmt.entity.Appointment;
import com.yks.cmt.entity.Message;
import com.yks.cmt.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private Sender sender;
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/web")
    public void saveAppointmentInformation(@RequestBody Appointment appointment){
            Message message = new Message();
            message.setMessage(JSONObject.toJSONString(appointment));
            sender.sendAppointment(message);
    }

    @GetMapping
    public ResponseEntity<?> getAllAppointment(){
        return ResponseEntity.ok(appointmentService.getAllAppointment());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAppointmentById(@PathVariable int id){
        return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }

    @GetMapping("/web/{userId}")
    public ResponseEntity<?> getAppointmentByUserId(@PathVariable("userId") String userId){
        return ResponseEntity.ok(appointmentService.getAppointmentByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<?> addAppointment(@RequestBody Appointment appointment){
        return ResponseEntity.ok(appointmentService.addAppointment(appointment));
    }

    @PutMapping
    public ResponseEntity<?> updateAppointment(@RequestBody Appointment appointment){
        System.out.println(appointment);
        return ResponseEntity.ok(appointmentService.updateAppointment(appointment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable int id){
        return ResponseEntity.ok(appointmentService.deleteAppointment(id));
    }
}
