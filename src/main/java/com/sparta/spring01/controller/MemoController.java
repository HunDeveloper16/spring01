package com.sparta.spring01.controller;

import com.sparta.spring01.models.Memo;
import com.sparta.spring01.repository.MemoRepository;
import com.sparta.spring01.dto.MemoRequestDto;
import com.sparta.spring01.security.UserDetailsImpl;
import com.sparta.spring01.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
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
                () -> new IllegalArgumentException("아이디 존재하지않음")
        );
    }

    @PostMapping("/api/memos")                                             // 게시글 작성 api
    public Memo createMemo(@RequestBody MemoRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        if(userDetails.getUser().getId()==null){
            throw new IllegalArgumentException("로그인 한 회원만 작성할 수 있습니다.");
        }

        Long userId = userDetails.getUser().getId();

        requestDto.setWriter(userDetails.getUsername());

        Memo memo = new Memo(requestDto, userId);
        return memoRepository.save(memo);

    }

    @PutMapping("/api/memos/{id}")                                        // 게시글 수정 api
    public Long updateMemo(@PathVariable Long id,@RequestBody MemoRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){

        requestDto.setWriter(userDetails.getUsername());
        requestDto.setUserId(userDetails.getUser().getId());  // MemoRequestDto의 userId값을 현재 접속중인 UserId값으로 설정함

        return memoService.update(id,requestDto);
    }

    @DeleteMapping("/api/memos/{id}")                                       //게시글 삭제 api
    public Long deleteMemo(@PathVariable Long id,@AuthenticationPrincipal UserDetailsImpl userDetails){

        Long userId = userDetails.getUser().getId(); // 현재 접속중인 유저의 userId를 가져온다.

        return memoService.delete(id,userId);

    }


}


