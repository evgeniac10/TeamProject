package com.example.wetro.user.repository;

import com.example.wetro.bookmark.dto.BookMark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<BookMark,Long> {
}
