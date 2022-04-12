package com.example.swp490_g25_sse.service;

import com.example.swp490_g25_sse.dto.FeedbackDto;
import com.example.swp490_g25_sse.model.Course;
import com.example.swp490_g25_sse.model.Feedback;
import com.example.swp490_g25_sse.model.Student;
import com.example.swp490_g25_sse.repository.FeedbackRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService {

  @Autowired
  private FeedbackRepository feedbackRepository;

  @Override
  public Feedback createNewFeedback(FeedbackDto feedbackDto, Student student, Course course) {
    Feedback feedback = new Feedback(course, student, feedbackDto.getRating(), feedbackDto.getContentKey(),
        feedbackDto.getContent());

    feedbackRepository.save(feedback);

    return feedback;
  }

}
