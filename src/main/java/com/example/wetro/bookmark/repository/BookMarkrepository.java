package com.example.wetro.bookmark.repository;

import com.example.wetro.bookmark.dto.BookMark;
import com.example.wetro.user.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookMarkrepository extends JpaRepository<BookMark,Long> {
    List<BookMark> findAllByUser(Optional<User> user);
}
