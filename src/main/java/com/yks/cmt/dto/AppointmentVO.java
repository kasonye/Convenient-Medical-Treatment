package com.yks.cmt.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class AppointmentVO {
    private int appointmentId;

    private String doctorId;

    private String userId;

    private String appointmentStatus;

    private Date appointmentDATE;

    private Date createTime;

    private Date updateTime;

    private String userName;

    private String doctorName;

}
