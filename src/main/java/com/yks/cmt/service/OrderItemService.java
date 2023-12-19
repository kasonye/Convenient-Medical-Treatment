package com.yks.cmt.service;

import com.yks.cmt.config.Sender;
import com.yks.cmt.dto.OrderItemVO;
import com.yks.cmt.entity.Medicine;
import com.yks.cmt.entity.Message;
import com.yks.cmt.entity.OrderItem;
import com.yks.cmt.repository.MedicineRepository;
import com.yks.cmt.repository.OrderItemRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private MedicineRepository medicineRepository;


    public List<OrderItemVO> getOrderItems(String orderId) {
        // 1. 得到订单的每个orderitem对象
        List<OrderItem> orderItemList = orderItemRepository.getOrderItemById(orderId);

        // 2. 将每个orderitem对象转换成流
        List<OrderItemVO> orderitemDTOList = orderItemList.stream().map(orderItem -> {
            // 3. 创建OrderitemDTO对象，复制对象
            OrderItemVO orderitemDTO = new OrderItemVO();
            BeanUtils.copyProperties(orderItem,orderitemDTO);

            // 4. 获得每个orderitem关联的商品id
            String medicineId = orderItem.getMedicineId();

            // 5. 获得关联的商品对象,封装到dto中
            Optional<Medicine> medicineOptional = medicineRepository.findById(Integer.valueOf(medicineId));
            if (!medicineOptional.isEmpty()){
                Medicine medicine = medicineOptional.get();
                orderitemDTO.setMedicine(medicine);
            }
            // 6. 将复制好的订单对象返回
            return orderitemDTO;
        }).collect(Collectors.toList());
        return orderitemDTOList;
    }

    // 新增订单详情
    public OrderItem saveOrderItem(OrderItem orderItem) {
        OrderItem save = orderItemRepository.save(orderItem);
        return save;
    }
}
