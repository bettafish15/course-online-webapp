/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.swp490_g25_sse.repository;

import com.example.swp490_g25_sse.model.Course;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vupham
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Page<Course> findAll(Pageable pageable);

    Course findFirstById(long id);
}
