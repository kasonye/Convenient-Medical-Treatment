package com.yks.cmt.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "CART")
public class Cart {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CART_ID",nullable = false)
    private int cartId;

    @NotNull
    @Column(name = "MEDICINE_ID",nullable = false)
    private int medicineId;

    @NotNull
    @Column(name = "USER_ID",nullable = false)
    private String userId;

    @NotNull
    @Column(name = "COUNT",nullable = false)
    private int count;
}
