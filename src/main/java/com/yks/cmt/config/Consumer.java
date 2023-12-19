package com.yks.cmt.config;

import com.alibaba.fastjson.JSONObject;
import com.yks.cmt.entity.Appointment;
import com.yks.cmt.entity.Message;
import com.yks.cmt.entity.Order;
import com.yks.cmt.entity.OrderItem;
import com.yks.cmt.repository.AppointmentRepository;
import com.yks.cmt.repository.MedicineOrderRepository;
import com.yks.cmt.repository.MedicineRepository;
import com.yks.cmt.repository.OrderItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class Consumer {
    @Autowired
    private MedicineOrderRepository orderRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    @RabbitListener(queues = "queue",containerFactory = "singleListenerContainer")
    public void consumeMsg(Message message){
        log.info("\nreceived: {}", message);
        Order order = new Order();
        JSONObject jsonObject = JSONObject.parseObject(message.getMessage());
        order.setUserId(jsonObject.getString("userId"));
        order.setUserName(jsonObject.getString("userName"));
        order.setIsSent("To be delivered");
        order.setIsDeleted(0);
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        order.setPhone(jsonObject.getString("phone"));
        order.setAddress(jsonObject.getString("address"));
        order.setTotalPrice(jsonObject.getBigDecimal("totalPrice"));
        Order save = orderRepository.save(order);

    }

    @RabbitListener(queues = "queue2",containerFactory = "singleListenerContainer",concurrency = "2")
    public void consumeItemMsg(Message message){
        log.info("\nreceived: {}", message);
        String json = message.getMessage();
        JSONObject jsonObject = JSONObject.parseObject(message.getMessage());
        Appointment appointment = new Appointment();
        appointment.setUserId(jsonObject.getString("userId"));
        appointment.setAppointmentDate(jsonObject.getDate("appointmentDate"));
        appointment.setDoctorId(jsonObject.getString("doctorId"));
        appointment.setIsDeleted(0);
        appointment.setAppointmentStatus("Successful Appointment");
        appointment.setCreateTime(new Date());
        appointment.setUpdateTime(new Date());
        appointmentRepository.save(appointment);
    }
}
