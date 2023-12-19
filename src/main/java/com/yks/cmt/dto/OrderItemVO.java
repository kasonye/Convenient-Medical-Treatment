package com.yks.cmt.dto;

import com.yks.cmt.entity.Medicine;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemVO {
    private String orderitemId;
    private String orderId;
    private String medicineId;
    private BigDecimal price;
    private Integer quantity;

    Medicine medicine;
}
