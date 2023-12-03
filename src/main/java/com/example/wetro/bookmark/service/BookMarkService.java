package com.example.wetro.bookmark.service;

import com.example.wetro.bookmark.dto.BookMark;
import com.example.wetro.bookmark.repository.BookMarkrepository;
import org.springframework.stereotype.Service;

@Service
public class BookMarkService {
    private BookMarkrepository bookMarkrepository;

    public void saveBookMark(BookMark bookMark) {
        bookMarkrepository.save(bookMark);
    }

    public BookMark findBookMarkById(Long id) {
        // id를 사용하여 BookMark를 찾아 반환
        return bookMarkrepository.findById(id).orElse(null);
    }
}
