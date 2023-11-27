package com.example.wetro.user.dto;
import javax.persistence.*;

@Entity
@Table(name = "bookmark")
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookmarkId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String departureStation;
    private String arrivalStation;
    private String time;
    private Double cost;
    private Boolean transfer;

    // Getter, Setter, Constructors, etc.
}
