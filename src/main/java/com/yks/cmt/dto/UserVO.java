package com.yks.cmt.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserVO {
    private String userId;
    private String userLoginName;
    private String userName;
    private String userPassword;
    private String token;
}
