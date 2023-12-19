package com.yks.cmt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.boot.context.properties.bind.DefaultValue;
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
@Table(name = "DOCTOR")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Doctor {
    @Id
    @NotNull
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="system_uuid",strategy="uuid")
    @Column(name = "DOCTOR_ID",nullable = false)
    private String doctorId;


    @NotNull
    @Size(max = 32)
    @Column(name = "DOCTOR_NAME",nullable = false,length = 32)
    private String doctorName;

    @Size(max = 10)
    @NotNull
    @Column(name = "GENDER",length = 10,nullable = false)
    private String gender;

    @NotNull
    @Column(name = "POSITION_ID",nullable = false)
    private int positionId;

    @Size(max = 10)
    @NotNull
    @Column(name = "AGE",length = 10,nullable = false)
    private String age;

    @Size(max = 11)
    @NotNull
    @Column(name = "PHONE",length = 11,nullable = false)
    private String phone;

    @NotNull
    @Column(name = "DEPARTMENT_ID",nullable = false)
    private int DepartmentId;

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

    @Size(max = 255)
    @Column(name = "IMAGEURL")
    private String imageUrl;


}
