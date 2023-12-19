package com.yks.cmt.repository;

import com.yks.cmt.entity.Admin;
import com.yks.cmt.entity.WebUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<Admin,Integer> {
    @Query(value = "select * from admin where admin_name = :adminName",nativeQuery = true)
    public Admin findByAdminName(@Param("adminName") String adminName);

}
