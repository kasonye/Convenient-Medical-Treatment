package com.yks.cmt.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "APPOINTMENT")
public class Appointment {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "APPOINTMENT_ID",nullable = false)
    private int appointmentId;

    @NotNull
    @Size(max = 32)
    @Column(name = "DOCTOR_ID",nullable = false,length = 32)
    private String doctorId;

    @NotNull
    @Size(max = 32)
    @Column(name = "USER_ID",nullable = false,length = 32)
    private String userId;


    @Size(max = 30)
    @NotNull
    @Column(name = "APPOINTMENT_STATUS",length = 30,nullable = false)
    private String appointmentStatus;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "APPOINTMENT_DATE",nullable = false)
    private Date appointmentDate;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "CREATE_TIME",nullable = false)
    private Date createTime;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "UPDATE_TIME",nullable = false)
    private Date updateTime;

    @NotNull
    @Column(name = "is_deleted",nullable = false)
    private int isDeleted;

}
