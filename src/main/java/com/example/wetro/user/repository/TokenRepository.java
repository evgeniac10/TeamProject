package com.example.wetro.user.repository;

import com.example.wetro.user.dto.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token,Long> {
}
