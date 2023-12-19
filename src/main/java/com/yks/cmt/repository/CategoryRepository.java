package com.yks.cmt.repository;

import com.yks.cmt.entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Integer> {
    @Query(value = "select * from category",nativeQuery = true)
    public List<Category> getAllCategory();
}
