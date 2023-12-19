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
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity

@Table(name = "MEDICINE")
public class Medicine {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEDICINE_ID",nullable = false)
    private int medicineId;

    @Size(max = 32)
    @NotNull
    @Column(name = "MEDICINE_NAME",length = 32,nullable = false)
    private String medicineName;

    @Size(max = 10)
    @NotNull
    @Column(name = "STOCK_NUMBER",length = 10,nullable = false)
    private String stockNumber;

    @Size(max = 30)
    @NotNull
    @Column(name = "MANUFACTURER",length = 30,nullable = false)
    private String manufacturer;

    @NotNull
    @Column(name = "COST_PRICE",nullable = false)
    private BigDecimal costPrice;

    @NotNull
    @Column(name = "SELLING_PRICE",nullable = false)
    private BigDecimal sellingPrice;

    @NotNull
    @Column(name = "CATEGORY_ID",nullable = false)
    private int categoryId;

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
