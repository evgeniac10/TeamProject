package com.example.wetro.bookmark.controller;


import com.example.wetro.bookmark.dto.BookMarkDto;
import com.example.wetro.response.bookmarkResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.wetro.response.Message.*;
import static com.example.wetro.response.bookmarkResponse.success;

@RestController
@RequestMapping("/wetro")
public class BookMarkApiController {

    @PostMapping("/bookmark")
    public bookmarkResponse registerBookmark(@RequestBody BookMarkDto bookMark){


        return success(SUCCESS_TO_BOOKMARK);

    }
}
