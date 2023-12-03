package com.example.wetro.bookmark.controller;


import com.example.wetro.bookmark.dto.BookMark;
import com.example.wetro.bookmark.dto.BookMarkDto;
import com.example.wetro.bookmark.service.BookMarkService;
import com.example.wetro.response.bookmarkResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.wetro.response.Message.*;
import static com.example.wetro.response.bookmarkResponse.fail;
import static com.example.wetro.response.bookmarkResponse.success;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wetro")
public class BookMarkApiController {
    private final BookMarkService bookMarkService;

    @PostMapping("/bookmark")
    public bookmarkResponse registerBookmark(@RequestBody BookMarkDto bookMarkdto){

        BookMark bookmark = BookMark.builder()
                .from(bookMarkdto.getFrom())
                .to(bookMarkdto.getTo())
                .layover(bookMarkdto.getLayover())
                .alias(bookMarkdto.getAlias())
                .alarm(bookMarkdto.getAlarm())
                .build();

        bookMarkService.saveBookMark(bookmark);

        return success(SUCCESS_TO_BOOKMARK);

    }
    @PostMapping("/bookmark/edit")
    public bookmarkResponse editBookmark(@RequestBody BookMarkDto bookMark){
// 요청으로부터 얻은 id 값
        Long bookmarkId = bookMark.getId();

        // id를 사용하여 DB에서 해당 BookMark 객체를 찾음
        BookMark findBookMark = bookMarkService.findBookMarkById(bookmarkId);

        // 찾은 BookMark가 null이 아닌지 확인
        if (findBookMark != null) {
            // BookMarkDto에서 필요한 정보를 가져와서 수정
            findBookMark.setAlias(bookMark.getAlias());
            findBookMark.setAlarm(bookMark.getAlarm());

            // 수정된 BookMark를 저장
            bookMarkService.saveBookMark(findBookMark);

            return success(SUCCESS_TO_EDIT_BOOKMARK);
        } else {
            // 해당 id로 찾은 BookMark가 없을 경우 에러 처리
            return fail(FAIL_TO_EDIT_BOOKMARK);
        }
    }
}
