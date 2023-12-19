package com.yks.cmt.dto;

import com.yks.cmt.entity.Medicine;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderVO {
    private String orderId;
    private String isSent;
    private int amount;
    private BigDecimal totalPrice;
    private String userId;
    private String userName;
    private String phone;
    private String address;
    private Date createTime;
    private Date updateTime;
    private int isDeleted;

    List<OrderItemVO> orderItems;
}
