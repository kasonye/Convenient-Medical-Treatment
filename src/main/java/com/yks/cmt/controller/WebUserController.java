package com.yks.cmt.controller;

import com.yks.cmt.common.JwtUtils;
import com.yks.cmt.dto.UserVO;
import com.yks.cmt.entity.WebUser;
import com.yks.cmt.exception.UnauthorizedException;
import com.yks.cmt.exception.UserExistedException;
import com.yks.cmt.service.WebUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class WebUserController {
    @Autowired
    private WebUserService service;

    @GetMapping
    public ResponseEntity<?> getAllUser(){
        return ResponseEntity.ok(service.getAllUser());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id){
        return ResponseEntity.ok(service.getUserById(id));
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody WebUser webUser) throws UserExistedException {
        return ResponseEntity.ok(service.addUser(webUser));
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserVO userVO) throws UnauthorizedException {
        if(userVO == null){
            throw new UnauthorizedException();
        }else{
            UserVO user = service.login(userVO);
            if (user == null){
                throw new UnauthorizedException();
            }
            userVO.setUserName(user.getUserName());
            userVO.setUserId(user.getUserId());
            String username = user.getUserLoginName();
            String password = user.getUserPassword();

            if (userVO.getUserLoginName().equals(username) && userVO.getUserPassword().equals(password)){
                userVO.setToken(JwtUtils.createToken(userVO));
                return ResponseEntity.ok(userVO);
            }else {
                throw new UnauthorizedException();
            }
        }
    }
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody WebUser webUser){
        return ResponseEntity.ok(service.updateUser(webUser));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id){
        return ResponseEntity.ok(service.deleteUser(id));
    }
}
