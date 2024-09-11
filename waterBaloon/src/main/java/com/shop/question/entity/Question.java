package com.shop.question.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long itemId;

    private String userName;

    private String content;

    private String answer;
    
    private LocalDateTime createdDate;
    
    private LocalDateTime answeredDate;
    
}
