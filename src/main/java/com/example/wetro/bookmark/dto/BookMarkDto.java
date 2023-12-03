package com.example.wetro.bookmark.dto;

import lombok.*;

@Data
@Builder
public class BookMarkDto {

    String token;
    String from;
    String layover;
    String to;
    String type;//min**
    String alias;

}
