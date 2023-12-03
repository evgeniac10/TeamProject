package com.example.wetro.user.dto;
import com.example.wetro.bookmark.dto.BookMark;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;


@Entity
@Table(name = "user")
@Data
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String email;
    @NotEmpty
    private String userid;
    @NotEmpty
    private String password;

    public String getUserid() {
        return userid;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<BookMark> bookMarks = new ArrayList<>();



    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities = new HashSet<>(Arrays.asList(new Authority("ROLE_USER")));


    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "user")
    private Token token;
}
