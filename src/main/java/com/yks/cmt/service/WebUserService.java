package com.yks.cmt.service;

import com.yks.cmt.dto.UserVO;
import com.yks.cmt.entity.WebUser;
import com.yks.cmt.exception.UserExistedException;
import com.yks.cmt.repository.WebUserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class WebUserService {
    @Autowired
    private WebUserRepository repository;

    public ResponseEntity<?> getAllUser() {
        return ResponseEntity.ok(repository.findAll());
    }

    public ResponseEntity<?> getUserById(String id) {
        return ResponseEntity.ok(repository.getUserById(id));
    }

    public ResponseEntity<?> addUser(WebUser webUser) throws UserExistedException {
        String userName = webUser.getUserLoginName();
        System.out.println(userName);
        if(repository.findByUsername(userName)!=null){
            throw new UserExistedException();
        }
        webUser.setCreateTime(new Date());
        webUser.setUpdateTime(new Date());
        webUser.setIsDeleted(0);
        return ResponseEntity.ok(repository.save(webUser));
    }

    public ResponseEntity<?> updateUser(WebUser webUser) {
        webUser.setUpdateTime(new Date());
        return ResponseEntity.ok(repository.save(webUser));
    }

    public UserVO login(UserVO uservo) {
        WebUser user = repository.findByUsername(uservo.getUserLoginName());
        if (user==null){
            return null;
        }

        UserVO loginUser = new UserVO();
        BeanUtils.copyProperties(user, loginUser);
        return loginUser;
    }

    public ResponseEntity<?> deleteUser(String id) {
        return ResponseEntity.ok(repository.deletedById(id));
    }

}
