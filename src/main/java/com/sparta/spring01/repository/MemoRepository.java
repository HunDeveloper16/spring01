package com.sparta.spring01.repository;

import com.sparta.spring01.models.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    List<Memo> findAllByOrderByCreatedAtDesc();

}
