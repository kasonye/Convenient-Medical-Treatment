package com.yks.cmt.dto;

import com.yks.cmt.entity.Medicine;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class CategoryVO {
    private int categoryId;
    private String categoryName;
    private List<Medicine> medicineList;
    private int isDeleted;
}
