package com.example.wetro.bookmark.dto;

import lombok.*;

@Data
@Builder
public class BookMarkDto {

    String token;
    String start_location;
    String layover_location;
    String end_location;
    String type;//min**
    String alias;

}
