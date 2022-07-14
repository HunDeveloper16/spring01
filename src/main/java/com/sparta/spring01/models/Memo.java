package com.sparta.spring01.models;

import com.sparta.spring01.dto.MemoRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Memo extends Timestamped{

    @GeneratedValue(strategy = GenerationType.AUTO )
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private Long userId;

    public Memo(MemoRequestDto requestDto, Long UserId){
        this.title = requestDto.getTitle();
        this.writer = requestDto.getWriter();
        this.contents = requestDto.getContents();
        this.userId = UserId;
    }

    public void update(MemoRequestDto requestDto, Long UserId){
        this.title = requestDto.getTitle();
        this.writer = requestDto.getWriter();
        this.contents = requestDto.getContents();
        this.userId = UserId;

    }

}
