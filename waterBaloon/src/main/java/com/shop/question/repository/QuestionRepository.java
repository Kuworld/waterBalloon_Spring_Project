package com.shop.question.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.question.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByItemId(Long itemId);
}
