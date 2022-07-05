package com.sparta.spring01.models;

import lombok.Getter;
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

}
