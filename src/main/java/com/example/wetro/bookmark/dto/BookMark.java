package com.example.wetro.bookmark.dto;

import com.example.wetro.user.dto.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book_mark")
public class BookMark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String start_location;
    private String end_location;
    private String layover_location;
    private String alias;
    private String type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
