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
@Table(name = "CATEGORY")
public class Category {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID",nullable = false)
    private int categoryId;

    @NotNull
    @Size(max = 32)
    @Column(name = "CATEGORY_NAME",nullable = false,length = 32)
    private String categoryName;

    @NotNull
    @Column(name = "IS_DELETED",nullable = false)
    private int isDeleted;
}
