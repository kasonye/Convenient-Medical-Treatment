package com.yks.cmt.service;


import com.yks.cmt.config.Consumer;
import com.yks.cmt.config.Sender;
import com.yks.cmt.dto.OrderItemVO;
import com.yks.cmt.dto.OrderVO;
import com.yks.cmt.entity.Medicine;
import com.yks.cmt.entity.Message;
import com.yks.cmt.entity.Order;
import com.yks.cmt.entity.OrderItem;
import com.yks.cmt.repository.MedicineOrderRepository;
import com.yks.cmt.repository.MedicineRepository;
import com.yks.cmt.repository.OrderItemRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicineOrderService {
    @Autowired
    private MedicineOrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private MedicineRepository medicineRepository;
    @PersistenceContext
    private EntityManager em;

    public BigInteger getOrderCount(String date) throws ParseException {
        String sql = "select count(*) from medicine_order where create_time like '"+date+"%' and is_sent != 'Cancelled'";
        BigInteger count = (BigInteger) em.createNativeQuery(sql).getSingleResult();
        return count;
    }

    // 通过顾客id查询顾客订单
    public List<OrderVO> getOrders() {

        List<Order> ordersList= orderRepository.getOrders();

        // 2. 将订单列表变为steam流
        List<OrderVO> ordersDTOList = ordersList.stream().map(orders -> {

            // 3. 创建DTO对象,将查询出来的orders对象封装到dto的orders对象中
            OrderVO ordersDTO = new OrderVO();
            BeanUtils.copyProperties(orders,ordersDTO);

            String orderId = orders.getOrderId();

            List<OrderItem> orderItemById = orderItemRepository.getOrderItemById(orderId);

            // 2. 将每个orderitem对象转换成流
            List<OrderItemVO> orderitemDTOList = orderItemById.stream().map(orderItem -> {
                // 3. 创建OrderitemDTO对象，复制对象
                OrderItemVO orderitemDTO = new OrderItemVO();
                BeanUtils.copyProperties(orderItem,orderitemDTO);

                // 4. 获得每个orderitem关联的商品id
                String medicineId = orderItem.getMedicineId();

                // 5. 获得关联的商品对象,封装到dto中
                Optional<Medicine> medicineOptional = medicineRepository.findById(Integer.valueOf(medicineId));
                if (!medicineOptional.isEmpty()) {
                    orderitemDTO.setMedicine(medicineOptional.get());
                }

                // 6. 将复制好的订单对象返回
                return orderitemDTO;
            }).collect(Collectors.toList());

            ordersDTO.setOrderItems(orderitemDTOList);

            // 5. 将封装好的dto对象返回
            return ordersDTO;
        }).collect(Collectors.toList());

        // 6. 返回已封装好的订单对象
        return ordersDTOList;
    }

    public List<OrderVO> getOrders_Web() {

        List<Order> ordersList= orderRepository.getOrders();

        // 2. 将订单列表变为steam流
        List<OrderVO> ordersDTOList = ordersList.stream().map(orders -> {

            // 3. 创建DTO对象,将查询出来的orders对象封装到dto的orders对象中
            OrderVO ordersDTO = new OrderVO();
            BeanUtils.copyProperties(orders,ordersDTO);

            String orderId = orders.getOrderId();

            List<OrderItem> orderItemById = orderItemRepository.getOrderItemById(orderId);

            // 2. 将每个orderitem对象转换成流
            List<OrderItemVO> orderitemDTOList = orderItemById.stream().map(orderItem -> {
                // 3. 创建OrderitemDTO对象，复制对象
                OrderItemVO orderitemDTO = new OrderItemVO();
                BeanUtils.copyProperties(orderItem,orderitemDTO);

                // 4. 获得每个orderitem关联的商品id
                String medicineId = orderItem.getMedicineId();

                // 5. 获得关联的商品对象,封装到dto中
                Optional<Medicine> medicineOptional = medicineRepository.findById(Integer.valueOf(medicineId));
                if (!medicineOptional.isEmpty()) {
                    orderitemDTO.setMedicine(medicineOptional.get());
                }

                // 6. 将复制好的订单对象返回
                return orderitemDTO;
            }).collect(Collectors.toList());

            ordersDTO.setOrderItems(orderitemDTOList);

            // 5. 将封装好的dto对象返回
            return ordersDTO;
        }).collect(Collectors.toList());

        // 6. 返回已封装好的订单对象
        return ordersDTOList;
    }


    public List<OrderVO> getOrdersByUserId(String id, String customerId) {

        List<Order> ordersList= orderRepository.getOrdersByUserId(id,customerId);

        // 2. 将订单列表变为steam流
        List<OrderVO> ordersDTOList = ordersList.stream().map(orders -> {

            // 3. 创建DTO对象,将查询出来的orders对象封装到dto的orders对象中
            OrderVO ordersDTO = new OrderVO();
            BeanUtils.copyProperties(orders,ordersDTO);

            String orderId = orders.getOrderId();

            List<OrderItem> orderItemById = orderItemRepository.getOrderItemById(orderId);

            // 2. 将每个orderitem对象转换成流
            List<OrderItemVO> orderitemDTOList = orderItemById.stream().map(orderItem -> {
                // 3. 创建OrderitemDTO对象，复制对象
                OrderItemVO orderitemDTO = new OrderItemVO();
                BeanUtils.copyProperties(orderItem,orderitemDTO);

                // 4. 获得每个orderitem关联的商品id
                String medicineId = orderItem.getMedicineId();

                // 5. 获得关联的商品对象,封装到dto中
                Medicine medicine = medicineRepository.findById(Integer.valueOf(medicineId)).get();
                if (medicine!=null) {
                    orderitemDTO.setMedicine(medicine);
                }

                // 6. 将复制好的订单对象返回
                return orderitemDTO;
            }).collect(Collectors.toList());

            ordersDTO.setOrderItems(orderitemDTOList);

            // 5. 将封装好的dto对象返回
            return ordersDTO;
        }).collect(Collectors.toList());

        // 6. 返回已封装好的订单对象
        return ordersDTOList;
    }

    public OrderVO getOrderByOrderId(String orderId){
        List<OrderItem> orderItemById = orderItemRepository.getOrderItemById(orderId);
        Order order = orderRepository.getOrderByOrderId(orderId);
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(order,orderVO);
        List<OrderItemVO> orderitemDTOList = orderItemById.stream().map(orderItem -> {
            // 3. 创建OrderitemDTO对象，复制对象
            OrderItemVO orderitemDTO = new OrderItemVO();
            BeanUtils.copyProperties(orderItem,orderitemDTO);

            // 4. 获得每个orderitem关联的商品id
            String medicineId = orderItem.getMedicineId();

            // 5. 获得关联的商品对象,封装到dto中
            Medicine medicine = medicineRepository.getMedicineById(Integer.parseInt(medicineId));
            orderitemDTO.setMedicine(medicine);

            // 6. 将复制好的订单对象返回
            return orderitemDTO;
        }).collect(Collectors.toList());
        orderVO.setOrderItems(orderitemDTOList);
        return orderVO;
    }

    // 新增订单
    public OrderVO saveOrder(OrderVO ordersResultDTO) {
        Order orders = new Order();
        BeanUtils.copyProperties(ordersResultDTO,orders);
        orders.setCreateTime(new Date());
        orders.setUpdateTime(new Date());
        orders.setIsSent("To be delivered");
        orders.setIsDeleted(0);
        Order save = orderRepository.save(orders);
        String orderId = save.getOrderId();
        ordersResultDTO.setOrderId(orderId);
        return ordersResultDTO;
    }

    // 删除订单
    public ResponseEntity<?> deleteOrder(String id) {

        return ResponseEntity.ok(orderRepository.deletedById(id));
    }

    // 修改订单
    public OrderVO updateOrder(OrderVO orderStatusDTO) {
        System.out.println(orderStatusDTO);
        String orderId = orderStatusDTO.getOrderId();
        Order orders = orderRepository.getOrderByOrderId(orderId);
        BeanUtils.copyProperties(orderStatusDTO,orders);
        orders.setUpdateTime(new Date());
        orderRepository.save(orders);
        return orderStatusDTO;
    }

}
