package com.example.wetro.user.repository;

import com.example.wetro.user.dto.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark,Long> {
}
