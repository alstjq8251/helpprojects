package com.sparta.helpproject.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.helpproject.Timestamped;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "member")
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, name = "userid")
    private String userId;
//
    @Column(nullable = false, name ="usernic")
    private String userNic;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    public Member(String userId, String userNic, String password, Authority authority) {
        this.userId = userId;
        this.userNic = userNic;
        this.password = password;
        this.authority = authority;
    }
}

