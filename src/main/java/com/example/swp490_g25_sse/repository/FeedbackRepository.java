/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.swp490_g25_sse.repository;

import java.util.List;

import com.example.swp490_g25_sse.model.Feedback;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author bettafish15
 */
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
