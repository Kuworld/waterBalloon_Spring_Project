package com.shop.question.service;

import com.shop.question.entity.Question;
import com.shop.question.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getQuestionsByItemId(Long itemId) {
        return questionRepository.findByItemId(itemId);
    }

    public void addQuestion(Long itemId, String username, String content) {
        Question question = new Question();
        question.setItemId(itemId);
        question.setUserName(username);
        question.setContent(content);
        question.setCreatedDate(LocalDateTime.now());
        questionRepository.save(question);
    }

    public void answerQuestion(Long questionId, String answer) {
        Question question = questionRepository.findById(questionId).orElseThrow();
        question.setAnswer(answer);
        question.setAnsweredDate(LocalDateTime.now());
        questionRepository.save(question);
    }
}
