package com.yks.cmt.controller;

import com.alibaba.fastjson.JSONObject;
import com.yks.cmt.config.Sender;
import com.yks.cmt.dto.OrderItemVO;
import com.yks.cmt.entity.Message;
import com.yks.cmt.entity.OrderItem;
import com.yks.cmt.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderItem")
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private Sender sender;

    /**
     * 通过orderId查询订单详情
     * @param orderId
     * @return
     */
    @GetMapping("/{orderId}")
    public ResponseEntity getOrderItemsById(@PathVariable String orderId){
        return ResponseEntity.ok(orderItemService.getOrderItems(orderId));
    }

    /**
     * 新增订单详情
     * @param orderItems
     * @return
     */
    @PostMapping
    public ResponseEntity saveOrderItems(@RequestBody OrderItem orderItems){
        return ResponseEntity.ok(orderItemService.saveOrderItem(orderItems));
    }
}
