package com.example.swp490_g25_sse.service;

import java.util.List;

import com.example.swp490_g25_sse.dto.AnswerDto;
import com.example.swp490_g25_sse.model.Answer;

import org.springframework.stereotype.Service;

public interface AnswerService {
    Answer createAnswer(AnswerDto answerDto, long questionId);

    List<Answer> getAnswersByQuestionId(long id);
}
