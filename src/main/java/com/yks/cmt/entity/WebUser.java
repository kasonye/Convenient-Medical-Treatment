package com.yks.cmt.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
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
@Table(name = "WEB_USER")
public class WebUser {
    @Id
    @NotNull
    @GeneratedValue(generator="system_uuid")
    @Size(max = 32)
    @GenericGenerator(name="system_uuid",strategy="uuid")
    @Column(name = "USER_ID",nullable = false,length = 32)
    private String userId;

    @Size(max = 32)
    @NotNull
    @Column(name = "USER_LOGIN_NAME",length = 32,nullable = false)
    private String userLoginName;

    @Size(max = 32)
    @NotNull
    @Column(name = "USER_PASSWORD",length = 32,nullable = false)
    private String userPassword;

    @Size(max = 32)
    @Column(name = "USER_NAME",length = 32)
    private String userName;

    @Size(max = 10)
    @Column(name = "AGE",length = 10)
    private String age;

    @Size(max = 10)
    @Column(name = "GENDER",length = 10)
    private String gender;

    @Size(max = 11)
    @Column(name = "PHONE",length = 11)
    private String phone;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "CREATE_TIME",nullable = false)
    private Date createTime;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "UPDATE_TIME",nullable = false)
    private Date updateTime;

    @Size(max = 32)
    @Column(name = "ADDRESS",length = 32)
    private String address;

    @NotNull
    @Column(name = "is_deleted",nullable = false)
    private int isDeleted;

    @Size(max = 255)
    @Column(name = "IMAGEURL")
    private String imageUrl;

}
