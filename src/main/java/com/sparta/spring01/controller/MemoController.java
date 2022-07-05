package com.sparta.spring01.controller;

import com.sparta.spring01.models.Memo;
import com.sparta.spring01.models.MemoRepository;
import com.sparta.spring01.models.MemoRequestDto;
import com.sparta.spring01.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemoController {
    private final MemoRepository memoRepository;
    private final MemoService memoService;

    @GetMapping("/api/memos")
    public List<Memo> readMemo(){                                              // 전체 게시글 조회 api
        return memoRepository.findAllByOrderByCreatedAtDesc();
    }

    @GetMapping("/api/memos/{id}")
    public Memo getMemo(@PathVariable Long id){                               // 게시글 조회 api
        return memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디존재하지않음")
        );
    }

    @PostMapping("/api/memos")                                             // 게시글 작성 api
    public Memo createMemo(@RequestBody MemoRequestDto requestDto){
        Memo memo = new Memo(requestDto);
        return memoRepository.save(memo);
    }

    @PutMapping("/api/memos/{id}")                                        // 게시글 수정 api
    public Long updateMemo(@PathVariable Long id,@RequestBody MemoRequestDto requestDto){
        memoService.update(id,requestDto);
        return id;
    }

    @DeleteMapping("/api/memos/{id}")
    public Long deleteMemo(@PathVariable Long id){
        memoRepository.deleteById(id);
        return id;
    }


}
