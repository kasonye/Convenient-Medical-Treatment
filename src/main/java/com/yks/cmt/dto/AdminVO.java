package com.yks.cmt.dto;


import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AdminVO {
    private int adminId;
    private String adminName;
    private String password;
    private String token;
}
