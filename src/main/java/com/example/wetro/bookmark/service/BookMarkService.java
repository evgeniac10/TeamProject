package com.example.wetro.bookmark.service;

import com.example.wetro.bookmark.dto.BookMark;
import com.example.wetro.bookmark.repository.BookMarkrepository;
import com.example.wetro.user.dto.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookMarkService {
    private BookMarkrepository bookMarkrepository;

    public void saveBookMark(BookMark bookMark) {
        bookMarkrepository.save(bookMark);
    }


    public List<BookMark> findAllByUser(Optional<User> user) {
        // 사용자 아이디에 해당하는 즐겨찾기 목록을 가져옴
        return bookMarkrepository.findAllByUserId(user.get().getId());
    }

}
