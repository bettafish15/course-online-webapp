/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.swp490_g25_sse.repository;

import java.util.List;

import com.example.swp490_g25_sse.model.Course;
import com.example.swp490_g25_sse.model.StudentCourseEnrollment;
import com.example.swp490_g25_sse.model.Test;
import com.example.swp490_g25_sse.model.TestResult;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author bettafish15
 */
public interface TestRepository extends JpaRepository<Test, Long> {
  Integer countByCourseAndWeek(Course course, String week);

  List<Test> findByCourseAndWeek(Course course, String week);
}
