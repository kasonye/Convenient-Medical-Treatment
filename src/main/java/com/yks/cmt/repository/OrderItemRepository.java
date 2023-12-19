package com.yks.cmt.repository;

import com.yks.cmt.entity.OrderItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Order;
import java.util.List;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem,String> {
    @Query(value = "select * from order_item where order_id = :orderId",nativeQuery = true)
    List<OrderItem> getOrderItemById(@Param("orderId") String orderId);
}
