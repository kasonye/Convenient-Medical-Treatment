package com.yks.cmt.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@SQLDelete(sql = "update order set is_deleted = 1 where order_id = ?")
@Where(clause = "is_deleted = 0")
@Table(name = "MEDICINE_ORDER")
public class Order {
    @Id
    @NotNull
    @GeneratedValue(generator="system_uuid")
    @Size(max = 32)
    @GenericGenerator(name="system_uuid",strategy="uuid")
    @Column(name = "ORDER_ID",nullable = false)
    private String orderId;

    @NotNull
    @Size(max = 32)
    @Column(name = "USER_ID",nullable = false,length = 32)
    private String userId;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "CREATE_TIME",nullable = false)
    private Date createTime;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "UPDATE_TIME",nullable = false)
    private Date updateTime;

    @NotNull
    @Size(max = 32)
    @Column(name = "USER_NAME",length = 32,nullable = false)
    private String userName;

    @NotNull
    @Size(max = 11)
    @Column(name = "PHONE",length = 11,nullable = false)
    private String phone;

    @NotNull
    @Size(max = 32)
    @Column(name = "ADDRESS",length = 32,nullable = false)
    private String address;

    @NotNull
    @Column(name = "IS_SENT",nullable = false)
    private String isSent;


    @NotNull
    @Column(name = "TOTAL_PRICE",nullable = false)
    private BigDecimal totalPrice;

    @NotNull
    @Column(name = "IS_DELETED",nullable = false)
    private int isDeleted;
}
