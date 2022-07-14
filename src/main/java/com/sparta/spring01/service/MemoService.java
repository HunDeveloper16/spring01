package com.sparta.spring01.service;

import com.sparta.spring01.models.Memo;
import com.sparta.spring01.repository.MemoRepository;
import com.sparta.spring01.dto.MemoRequestDto;
import com.sparta.spring01.repository.UserRepository;
import com.sparta.spring01.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    private final UserRepository userRepository;


    @Transactional
    public Long update(Long id, MemoRequestDto requestDto){

        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 없습니다.")
        );

        if(!requestDto.getUserId().equals(memo.getUserId())){
            new IllegalArgumentException("자신의 글만 변경할수 있습니다.");
        }
        else{
            memo.update(requestDto, memo.getUserId());
        }

        return id;
    }

    @Transactional
    public Long delete(Long id, Long userId){

        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 없습니다.")
        );
        memoRepository.findById(id);

        if(!memo.getUserId().equals(userId)){
            new IllegalArgumentException("자신의 글만 변경할수 있습니다.");
        }
        else {
            memoRepository.deleteById(id);
        }
        return id;
    }

}
