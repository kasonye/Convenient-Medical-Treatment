package com.yks.cmt.service;

import com.yks.cmt.dto.AdminVO;
import com.yks.cmt.dto.UserVO;
import com.yks.cmt.entity.Admin;
import com.yks.cmt.entity.WebUser;
import com.yks.cmt.repository.AdminRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepository repository;

    public AdminVO login(AdminVO adminVO){
        Admin admin = repository.findByAdminName(adminVO.getAdminName());
        if (admin==null){
            return null;
        }
        AdminVO loginAdmin = new AdminVO();
        BeanUtils.copyProperties(admin,loginAdmin);
        return loginAdmin;
    }
}
