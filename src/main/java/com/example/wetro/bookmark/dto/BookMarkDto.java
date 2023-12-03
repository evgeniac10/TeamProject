package com.example.wetro.bookmark.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.time.LocalTime;

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
