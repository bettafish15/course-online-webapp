package com.example.swp490_g25_sse.service;

import com.example.swp490_g25_sse.model.Student;
import com.example.swp490_g25_sse.model.User;
import com.example.swp490_g25_sse.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;


  @Override
  public Student getStudentInfo(User user) {
    Student student = studentRepository.findFirstByUserId(user.getId());

    return student;
  }

}

