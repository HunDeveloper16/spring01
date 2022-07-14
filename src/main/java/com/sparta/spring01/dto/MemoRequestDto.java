package com.sparta.spring01.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@RequiredArgsConstructor
@Getter
public class MemoRequestDto {

    private String title;

    private String writer;

    private String contents;

    private String password;

    private Long userId;

}
