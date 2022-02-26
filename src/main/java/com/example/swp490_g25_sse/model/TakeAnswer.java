/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.swp490_g25_sse.model;

/**
 *
 * @author ADMIN
 */
public class TakeAnswer {
    private int id;
    private int takeId;
    private int questionId;
    private int userId;

    public TakeAnswer() {
    }

    public TakeAnswer(int id, int takeId, int questionId, int userId) {
        this.id = id;
        this.takeId = takeId;
        this.questionId = questionId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTakeId() {
        return takeId;
    }

    public void setTakeId(int takeId) {
        this.takeId = takeId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
}
