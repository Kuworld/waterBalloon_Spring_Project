package com.shop.question.controller;

import com.shop.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/add")
    public String addQuestion(@RequestParam("itemId") Long itemId, 
                              @RequestParam("question") String question,
                              @AuthenticationPrincipal UserDetails userDetails, 
                              Model model) {
        String username = userDetails.getUsername();
        questionService.addQuestion(itemId, username, question);
        return "redirect:/item_details/" + itemId;
    }

    @PostMapping("/answer")
    public String answerQuestion(@RequestParam("questionId") Long questionId, 
                                 @RequestParam("answer") String answer,
                                 @RequestParam("itemId") Long itemId) {
        questionService.answerQuestion(questionId, answer);
        return "redirect:/item_details/" + itemId;
    }
}
