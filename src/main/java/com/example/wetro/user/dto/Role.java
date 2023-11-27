package com.example.wetro.user.dto;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @Column(name = "role_id")
    private Long role_id;

    @Column(name = "role")
    private String roleName;

    @MapsId
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Getter, Setter, Constructors, etc.
}

