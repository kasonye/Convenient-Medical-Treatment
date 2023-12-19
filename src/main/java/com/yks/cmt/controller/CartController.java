package com.yks.cmt.controller;

import com.yks.cmt.entity.Cart;
import com.yks.cmt.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<?> addCart(@RequestBody Cart cart){
        return ResponseEntity.ok(cartService.addCart(cart));
    }
    @PutMapping
    public ResponseEntity<?> updateCart(@RequestBody Cart cart){
        return ResponseEntity.ok(cartService.addCart(cart));
    }
    @GetMapping("/{userId}/{medicineId}")
    public ResponseEntity<?> getCartByUserIdAndMedicineId(@PathVariable("userId") String userId, @PathVariable("medicineId") int medicineId){
        return ResponseEntity.ok(cartService.getCartByUserIdAndMedicineId(userId, medicineId));
    }
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserCart(@PathVariable("userId") String userId){
        return ResponseEntity.ok(cartService.getUserCart(userId));
    }
    @DeleteMapping("/{cartId}")
    public ResponseEntity<?> deleteCart(@PathVariable("cartId") int cartId){
        cartService.deleteCart(cartId);
        return ResponseEntity.ok().build();
    }
}
