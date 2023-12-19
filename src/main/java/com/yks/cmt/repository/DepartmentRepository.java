package com.yks.cmt.repository;

import com.yks.cmt.entity.Department;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface DepartmentRepository extends CrudRepository<Department,Integer> {
    @Modifying
    @Query(value = "update department set is_deleted = '1' where department_id = :id",nativeQuery = true)
    public int deletedById(@Param("id") int id);

    @Query(value = "select * from department where is_deleted = '0'",nativeQuery = true)
    public List<Department> getDepartmentByIsDeleted();
}
