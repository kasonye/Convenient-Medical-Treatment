package com.yks.cmt.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem implements Serializable {
    @Id
    @GeneratedValue(generator="system_uuid")
    @Size(max = 32)
    @GenericGenerator(name="system_uuid",strategy="uuid")
    private String orderitemId;

    @NotNull
    @Size(max = 32)
    @Column(name = "ORDER_ID",nullable = false)
    private String orderId;

    @NotNull
    @Size(max = 32)
    @Column(name = "MEDICINE_ID",nullable = false)
    private String medicineId;

    @NotNull
    @Column(name = "PRICE",nullable = false)
    private BigDecimal price;

    @NotNull
    @Column(name = "quantity",nullable = false)
    private int quantity;
}
