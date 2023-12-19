package com.yks.cmt.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "DEPARTMENT")
public class Department {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPARTMENT_ID",nullable = false)
    private int departmentId;

    @NotNull
    @Size(max = 32)
    @Column(name = "DEPARTMENT_NAME",length = 32,nullable = false)
    private String departmentName;

    @NotNull
    @Column(name = "IS_DELETED",nullable = false)
    private int isDeleted;
}
