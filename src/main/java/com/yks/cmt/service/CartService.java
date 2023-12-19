package com.yks.cmt.service;

import com.yks.cmt.entity.Cart;
import com.yks.cmt.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository repository;
    public Cart addCart(Cart cart){
        return repository.save(cart);
    }
    public Cart getCartByUserIdAndMedicineId(String userId,int medicineId){
        return repository.findByUserIdAndMedicineId(userId,medicineId);
    }
    public List<Cart> getUserCart(String userId){
        return repository.findByUserId(userId);
    }
    public void deleteCart(int cartId){
        repository.deleteById(cartId);
    }
}
