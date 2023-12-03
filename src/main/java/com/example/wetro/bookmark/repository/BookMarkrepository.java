package com.example.wetro.bookmark.repository;

import com.example.wetro.bookmark.dto.BookMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookMarkrepository extends JpaRepository<BookMark,Long> {
}
