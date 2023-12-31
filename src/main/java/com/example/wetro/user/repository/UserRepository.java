package com.example.wetro.user.repository;

import com.example.wetro.user.dto.Token;
import com.example.wetro.user.dto.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserid(String userid);
    Optional<User> findByemail(String useremail);
    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesByUserid(String userid);
    Optional<User> findByUseridAndToken(String userid, Token token);
}
