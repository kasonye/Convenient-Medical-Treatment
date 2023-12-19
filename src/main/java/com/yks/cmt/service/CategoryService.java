package com.yks.cmt.service;

import com.yks.cmt.dto.CategoryVO;
import com.yks.cmt.entity.Category;
import com.yks.cmt.entity.Medicine;
import com.yks.cmt.repository.CategoryRepository;
import com.yks.cmt.repository.MedicineRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;
    @Autowired
    private MedicineRepository medicineRepository;

    public ResponseEntity<?> getAllCategory() {
        List<Category> allCategory = repository.getAllCategory();
        List<CategoryVO> categoryVOList = allCategory.stream().map(category -> {
            int categoryId = category.getCategoryId();
            CategoryVO categoryVO = new CategoryVO();
            BeanUtils.copyProperties(category, categoryVO);
            List<Medicine> medicineList = medicineRepository.getMedicineListByCategoryId(categoryId);
            categoryVO.setMedicineList(medicineList);
            return categoryVO;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(categoryVOList);
    }

    public ResponseEntity<?> getCategoryById(int id) {
        Category category = repository.findById(id).get();
        List<Medicine> medicineListByCategoryId = medicineRepository.getMedicineListByCategoryId(id);
        CategoryVO categoryVO = new CategoryVO();
        BeanUtils.copyProperties(category, categoryVO);
        categoryVO.setMedicineList(medicineListByCategoryId);
        return ResponseEntity.ok(categoryVO);
    }

    public ResponseEntity<?> addCategory(Category category) {
        category.setIsDeleted(0);
        return ResponseEntity.ok(repository.save(category));
    }
}
