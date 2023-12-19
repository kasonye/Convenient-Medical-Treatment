package com.yks.cmt.controller;


import com.yks.cmt.common.JwtUtils;
import com.yks.cmt.dto.AdminVO;
import com.yks.cmt.entity.Admin;
import com.yks.cmt.exception.UnauthorizedException;
import com.yks.cmt.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AdminVO adminVO) throws UnauthorizedException {
        if(adminVO == null){
            throw new UnauthorizedException();
        }else{
            AdminVO admin = adminService.login(adminVO);
            if(admin==null){
                throw new UnauthorizedException();
            }


            adminVO.setAdminId(admin.getAdminId());
            String username = admin.getAdminName();
            String password = admin.getPassword();

            if (adminVO.getAdminName().equals(username) && adminVO.getPassword().equals(password)){
                adminVO.setToken(JwtUtils.createAdminToken(adminVO));
                return ResponseEntity.ok(adminVO);
            }else {
                throw new UnauthorizedException();
            }
        }
    }
}
