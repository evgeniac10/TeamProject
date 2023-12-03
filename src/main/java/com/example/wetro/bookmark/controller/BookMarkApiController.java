package com.example.wetro.bookmark.controller;


import com.example.wetro.bookmark.dto.BookMark;
import com.example.wetro.bookmark.dto.BookMarkDto;
import com.example.wetro.bookmark.dto.BookMarkTokenDto;
import com.example.wetro.bookmark.service.BookMarkService;
import com.example.wetro.response.bookmarkResponse;
import com.example.wetro.user.dto.TokenDto;
import com.example.wetro.user.dto.User;
import com.example.wetro.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static com.example.wetro.response.Message.*;
import static com.example.wetro.response.bookmarkResponse.fail;
import static com.example.wetro.response.bookmarkResponse.success;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wetro")
public class BookMarkApiController {
    private final BookMarkService bookMarkService;
    private final UserService userService;

    @PostMapping("/bookmark")
    public bookmarkResponse registerBookmark(@RequestBody BookMarkDto bookMarkdto) {
        // 프론트에서 받은 토큰
        String token = bookMarkdto.getToken();

        // 토큰을 이용하여 회원 정보 가져오기
        Optional<User> user = userService.findByToken(token);

        // 토큰에 해당하는 회원 정보가 있는지 확인
        if (user.isPresent()) {
            // 토큰에 해당하는 회원 정보가 있으면 BookMark 객체 생성 및 저장
            BookMark bookmark = BookMark.builder()
                                    .start_location(bookMarkdto.getStart_location())
                                    .end_location(bookMarkdto.getEnd_location())
                                    .layover_location(bookMarkdto.getLayover_location())
                                            .alias(bookMarkdto.getAlias())
                                                    .user(user.get())
                                                            .build();


            bookMarkService.saveBookMark(bookmark);

            return success(SUCCESS_TO_BOOKMARK);
        } else {
            // 토큰에 해당하는 회원 정보가 없으면 실패 응답
            return fail(FAIL_TO_BOOKMARK);
        }
    }

    @PostMapping("/bookmark/lists")
    public List<BookMark> bookMarksLists(@RequestBody BookMarkTokenDto tokenDto) {

        String token = tokenDto.getToken();

        // 토큰을 이용하여 회원 정보 가져오기
        Optional<User> user = userService.findByToken(token);

            return bookMarkService.findAllByUser(user);

    }

}
