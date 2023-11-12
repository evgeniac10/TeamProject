package com.example.wetro.user.repository;

import com.example.wetro.user.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.userid = :userid AND u.password = :password")
    User findByUseridAndPassword(@Param("userid") String userid, @Param("password") String password);
}
