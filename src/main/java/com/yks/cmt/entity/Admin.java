package com.yks.cmt.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "ADMIN")
public class Admin {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADMIN_ID",nullable = false)
    private int adminId;

    @Size(max = 22)
    @NotNull
    @Column(name = "ADMIN_NAME",length = 22,nullable = false)
    private String adminName;

    @Size(max = 32)
    @NotNull
    @Column(name = "PASSWORD",length = 32, nullable = false)
    private String password;


}
