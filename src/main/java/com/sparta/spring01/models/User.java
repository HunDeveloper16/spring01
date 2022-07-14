package com.sparta.spring01.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // nullable: null 허용 여부
    // unique : 중복 허용 여부 (false 일때 중복 허용)
    @Column(nullable = false,unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    public User(String username, String password){
        this.username = username;
        this.password = password;

    }


}
