package com.example.wetro.bookmark.dto;

import com.example.wetro.user.dto.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookMark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String from;
    private String to;
    private String layover;
    private String alias;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
