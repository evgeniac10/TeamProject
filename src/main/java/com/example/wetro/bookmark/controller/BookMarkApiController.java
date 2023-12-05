package com.example.wetro.bookmark.controller;


import com.example.wetro.bookmark.dto.BookMark;
import com.example.wetro.bookmark.dto.BookMarkDto;
import com.example.wetro.bookmark.dto.BookMarkTokenDto;
import com.example.wetro.bookmark.repository.BookMarkrepository;
import com.example.wetro.bookmark.service.BookMarkService;
import com.example.wetro.response.bookmarkResponse;
import com.example.wetro.user.dto.TokenDto;
import com.example.wetro.user.dto.User;
import com.example.wetro.user.repository.UserRepository;
import com.example.wetro.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.example.wetro.response.Message.*;
import static com.example.wetro.response.bookmarkResponse.fail;
import static com.example.wetro.response.bookmarkResponse.success;

@Slf4j
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
                                    .type(bookMarkdto.getType())
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
    public bookmarkResponse bookMarksLists(@RequestBody BookMarkTokenDto tokenDto) {
        log.info("입력 받은 토큰 = {}", tokenDto.getToken());
        String token = tokenDto.getToken();

        Optional<User> userOptional = userService.findByToken(token);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            log.info("토큰을 이용한 회원 객체 찾기 = {}",user);

            // 토큰을 이용하여 회원 정보 가져오기
            List<BookMark> bookMarks = bookMarkService.findAllByUser(Optional.of(user));
            return success(SUCCESS_TO_LOAD_BOOKMARKS,bookMarks);
        } else {
            log.info("토큰에 해당하는 사용자가 없습니다.");
            // 사용자가 없는 경우 빈 리스트 반환 또는 다른 처리를 수행할 수 있습니다.
            return fail(FAIL_TO_LOAD_BOOKMARKS);
        }
    }

}
