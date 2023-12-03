package com.example.wetro.bookmark.controller;


import com.example.wetro.bookmark.dto.BookMark;
import com.example.wetro.bookmark.dto.BookMarkDto;
import com.example.wetro.bookmark.service.BookMarkService;
import com.example.wetro.response.bookmarkResponse;
import com.example.wetro.user.dto.TokenDto;
import com.example.wetro.user.dto.User;
import com.example.wetro.user.service.UserService;
import lombok.RequiredArgsConstructor;
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

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = ((UserDetails) principal).getUsername();

        // 여기서 userId를 이용하여 사용자 정보를 가져오거나,
        // 이미 가지고 있는 경우 생략할 수 있습니다.

        Optional<User> user = userService.findByUserid(userId);

        // BookMark 객체를 생성하고 연관된 사용자 정보를 설정합니다.
        BookMark bookmark = BookMark.builder()
                .from(bookMarkdto.getFrom())
                .to(bookMarkdto.getTo())
                .layover(bookMarkdto.getLayover())
                .alias(bookMarkdto.getAlias())
                .user(user.orElse(null))
                .build();

        bookMarkService.saveBookMark(bookmark);

        return success(SUCCESS_TO_BOOKMARK);
    }

    @PostMapping("/bookmark/lists")
    public List<BookMark> bookMarksLists(@RequestBody TokenDto tokenDto) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = ((UserDetails) principal).getUsername();

        // 여기서 userId를 이용하여 사용자 정보를 가져오거나,
        // 이미 가지고 있는 경우 생략할 수 있습니다.

        Optional<User> user = userService.findByUserid(userId);
        // 사용자 정보가 존재하는지 확인


            return bookMarkService.findAllByUser(user);

    }

}
