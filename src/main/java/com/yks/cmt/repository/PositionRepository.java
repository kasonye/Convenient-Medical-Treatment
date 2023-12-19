package com.yks.cmt.repository;

import com.yks.cmt.entity.Department;
import com.yks.cmt.entity.Position;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface PositionRepository extends CrudRepository<Position,Integer> {
    @Modifying
    @Query(value = "update position set is_deleted = '1' where position_id = :id",nativeQuery = true)
    public int deletedById(@Param("id") int id);

    @Query(value = "select * from position where is_deleted = '0'",nativeQuery = true)
    public List<Position> getPositionByIsDeleted();
}
