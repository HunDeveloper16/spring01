package com.sparta.spring01.service;

import com.sparta.spring01.models.Memo;
import com.sparta.spring01.models.MemoRepository;
import com.sparta.spring01.models.MemoRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public Long update(Long id,MemoRequestDto memoRequestDto){

        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 없습니다.")
        );
        memo.update(memoRequestDto);

        return id;
    }

}
