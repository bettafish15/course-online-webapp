/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.swp490_g25_sse.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.stereotype.Component;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "Lecture_attachement")
public class LectureAttachement {

    private int attachId;
    private String attachUrl;
    private CourseLecture courseLecture;

    public LectureAttachement() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getAttachId() {
        return attachId;
    }

    public void setAttachId(int attachId) {
        this.attachId = attachId;
    }

    @Column(name = "attachUrl", nullable = false)
    public String getAttachUrl() {
        return attachUrl;
    }

    public void setAttachUrl(String attachUrl) {
        this.attachUrl = attachUrl;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lectureId")
    public CourseLecture getCourseLecture() {
        return courseLecture;
    }

    public void setCourseLecture(CourseLecture courseLecture) {
        this.courseLecture = courseLecture;
    }

}
