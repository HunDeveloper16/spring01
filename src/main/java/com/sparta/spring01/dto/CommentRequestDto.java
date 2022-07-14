package com.sparta.spring01.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {

    private String contents;
    private Long blogId;
}