package com.yks.cmt.repository;

import com.yks.cmt.entity.Cart;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartRepository extends CrudRepository<Cart,Integer> {
    public Cart findByUserIdAndMedicineId(String userId,int MedicineId);
    public List<Cart> findByUserId(String userId);
}
