package com.yks.cmt.controller;

import com.alibaba.fastjson.JSONObject;
import com.yks.cmt.config.Consumer;
import com.yks.cmt.dto.OrderVO;
import com.yks.cmt.entity.Appointment;
import com.yks.cmt.entity.Medicine;
import com.yks.cmt.entity.Message;
import com.yks.cmt.entity.Order;
import com.yks.cmt.service.MedicineOrderService;
import com.yks.cmt.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;

@RestController
@RequestMapping("/order")
public class MedicineOrderController {
    @Autowired
    private MedicineOrderService orderService;

    @GetMapping("/count")
    public ResponseEntity<?> getOrderCount(@RequestParam  String date) throws ParseException {
        System.out.println(date);
        String[] split = date.split("-");
        System.out.println(split);
        if (Integer.parseInt(split[2])<10){
            date = split[0]+"-"+split[1]+"-0"+split[2];
        }
        return ResponseEntity.ok(orderService.getOrderCount(date));
    }

    /**
     * @param
     * @return
     */
    @GetMapping
    public ResponseEntity<?> getOrders(){
        return ResponseEntity.ok(orderService.getOrders());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderByid(@PathVariable String orderId){
        return ResponseEntity.ok(orderService.getOrderByOrderId(orderId));
    }

    /**
     * 通过顾客id查询顾客订单
     * @param userId
     * @return
     */
    @GetMapping("/user")
    public ResponseEntity<?> getOrdersByUserId(@RequestParam(value = "id",required = false) String id,
                                    @RequestParam(value = "userId",required = false) String userId){
        return ResponseEntity.ok(orderService.getOrdersByUserId(id, userId));
    }

    /**
     * 新增订单
     * @param orders
     * @return
     */
    @PostMapping
    public ResponseEntity<?> saveOrder(@RequestBody OrderVO orders){
        return ResponseEntity.ok(orderService.saveOrder(orders));
    }

    /**
     * 通过订单id删除订单
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable String id){
        return ResponseEntity.ok(orderService.deleteOrder(id));
    }

    /**
     * 更新订单状态
     * @param orders
     * @return
     */
    @PutMapping
    public ResponseEntity<?> updateOrder(@RequestBody OrderVO orders){
        return ResponseEntity.ok(orderService.updateOrder(orders));
    }
}
