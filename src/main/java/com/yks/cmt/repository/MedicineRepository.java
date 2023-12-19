package com.yks.cmt.repository;

import com.yks.cmt.entity.Medicine;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Repository
public interface MedicineRepository extends CrudRepository<Medicine,Integer> {
    @Query(value = "Select * from medicine",nativeQuery = true)
    public List<Medicine> findAll();
    public List<Medicine>  findByIsDeleted(int isDeleted);
    @Query(value = "Select * from medicine where medicine_id = :id",nativeQuery = true)
    public Medicine getMedicineById(@Param("id") int id);
    public Medicine findByMedicineIdAndIsDeleted(int id,int isDeleted);
    @Modifying
    @Query(value = "update medicine set is_deleted = '1' where medicine_id = :id",nativeQuery = true)
    public int deletedById(@Param("id") int id);
    @Query(value = "select * from medicine where category_id = :categoryId",nativeQuery = true)
    public List<Medicine> getMedicineListByCategoryId(int categoryId);
}
