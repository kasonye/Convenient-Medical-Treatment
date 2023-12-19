package com.yks.cmt.repository;

import com.yks.cmt.entity.WebUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Repository
public interface WebUserRepository extends CrudRepository<WebUser,String> {
    @Query(value = "Select * from web_user",nativeQuery = true)
    public List<WebUser> findAll();
    @Query(value = "select * from web_user where user_id = :id",nativeQuery = true)
    public WebUser getUserById(@Param("id") String id);
    public List<WebUser> findByIsDeleted(int isDeleted);
    public WebUser  findByUserIdAndIsDeleted(String id,int isDeleted);
    @Modifying
    @Query(value = "update web_user set is_deleted = '1' where user_id = :id",nativeQuery = true)
    public int deletedById(@Param("id") String id);
    @Query(value = "select * from web_user where user_login_name = :username",nativeQuery = true)
    public WebUser findByUsername(@Param("username") String username);
}
