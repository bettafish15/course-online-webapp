package com.example.swp490_g25_sse.service;

import java.util.List;

import com.example.swp490_g25_sse.dto.AnswerDto;
import com.example.swp490_g25_sse.model.Answer;
import com.example.swp490_g25_sse.model.Course;
import com.example.swp490_g25_sse.model.Question;
import com.example.swp490_g25_sse.model.Student;
import com.example.swp490_g25_sse.repository.AnswerRepository;
import com.example.swp490_g25_sse.repository.QuestionRepository;
import com.example.swp490_g25_sse.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public Answer createAnswer(AnswerDto answerDto, long questionId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetailsService currentUser = (CustomUserDetailsService) auth.getPrincipal();
        Student student = studentRepository.findFirstByUserId(currentUser.getUser().getId());
        Question question = questionRepository.findFirstById(questionId).get();
        Answer answer = new Answer(answerDto.getContent(), student, question);
        Answer newAnswer = answerRepository.save(answer);
        return newAnswer;
    }

    @Override
    public List<Answer> getAnswersByQuestionId(long questionId) {
        List<Answer> answers = answerRepository.findByQuestionId(questionId);
        return answers;
    }

}
