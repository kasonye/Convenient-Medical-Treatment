package com.yks.cmt.repository;

import com.yks.cmt.entity.Order;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Transactional
@Repository
public interface MedicineOrderRepository extends CrudRepository<Order,String> {
    @Query(value = "select * from medicine_order", nativeQuery = true)
    List<Order> getOrders();

    @Query(value = "select * from medicine_order where is_deleted = '0'", nativeQuery = true)
    List<Order> getOrders_Web();

    @Query(value = "select * from medicine_order where order_id = :id" ,nativeQuery = true)
    Order getOrderByOrderId(@Param("id") String orderId);

    @Query(value = "select * from medicine_order where(order_id = :id or :id is null or :id='') " +
            "and (user_id = :userId or :userId is null or :userId='') " +
            "and is_deleted=0", nativeQuery = true)
    List<Order> getOrdersByUserId(@Param("id") String id,
                               @Param("userId") String userId);

    @Modifying
    @Query(value = "update medicine_order set is_deleted = '1' where order_id = :id",nativeQuery = true)
    public int deletedById(@Param("id") String id);

}
