package com.example.swp490_g25_sse.service;

import com.example.swp490_g25_sse.model.StudentCourseEnrollment;
import com.example.swp490_g25_sse.model.Test;
import com.example.swp490_g25_sse.model.TestResult;
import com.example.swp490_g25_sse.repository.TestResultRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestResultServiceImpl implements TestResultService {

  @Autowired
  TestResultRepository testResultRepository;

  @Override
  public TestResult createTestReport(StudentCourseEnrollment enrollment, Test test) {
    return testResultRepository.save(new TestResult(enrollment, test, false, 0));
  }

  @Override
  public TestResult updateTestResult(StudentCourseEnrollment enrollment, Test test, Boolean isFinished, Integer mark) {
    TestResult result = testResultRepository.findFirstByEnrollmentAndTest(enrollment, test);

    result.setIsFinished(true);
    result.setMark(mark);
    return testResultRepository.save(result);
  }

}
