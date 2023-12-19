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
@Table(name = "POSITION")
public class Position {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POSITION_ID",nullable = false)
    private int positionId;

    @NotNull
    @Size(max = 32)
    @Column(name = "POSITION_NAME",length = 32,nullable = false)
    private String positionName;

    @NotNull
    @Column(name = "IS_DELETED",nullable = false)
    private int isDeleted;
}
