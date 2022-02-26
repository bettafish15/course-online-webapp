/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.swp490_g25_sse.model;

import org.springframework.stereotype.Component;

/**
 *
 * @author ADMIN
 */
@Component
public class TestQuestion {
    private int questionId;
    private String questionText;
    private int questionScore;
    private int questionType;
    private int testId;

    public TestQuestion() {
    }

    public TestQuestion(int questionId, String questionText, int questionScore, int questionType, int testId) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.questionScore = questionScore;
        this.questionType = questionType;
        this.testId = testId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public int getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(int questionScore) {
        this.questionScore = questionScore;
    }

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }
    
}
