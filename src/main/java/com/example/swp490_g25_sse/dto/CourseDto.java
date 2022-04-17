/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.swp490_g25_sse.dto;

import java.util.List;

/**
 *
 * @author bettafish15
 */
public class CourseDto {

    public Long id;
    public String courseTitle;
    public String courseImgUrl;
    public String content;
    public List<LectureDto> lectureDtos;
    public List<TestDto> testDtos;
    public Integer feedbackRating;
    public Integer totalEnrolls;

    public CourseDto() {
    }

    public CourseDto(String courseTitle, String courseImgUrl, String content, List<LectureDto> lectureDtos,
            List<TestDto> testDtos) {
        this.courseTitle = courseTitle;
        this.courseImgUrl = courseImgUrl;
        this.content = content;
        this.lectureDtos = lectureDtos;
        this.testDtos = testDtos;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseImgUrl() {
        return courseImgUrl;
    }

    public void setCourseImgUrl(String courseImgUrl) {
        this.courseImgUrl = courseImgUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<LectureDto> getLectureDtos() {
        return lectureDtos;
    }

    public void setLectureDtos(List<LectureDto> lectureDtos) {
        this.lectureDtos = lectureDtos;
    }

    public List<TestDto> getTestDtos() {
        return testDtos;
    }

    public void setTestDtos(List<TestDto> testDtos) {
        this.testDtos = testDtos;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFeedbackRating() {
        return this.feedbackRating;
    }

    public void setFeedbackRating(Integer feedbackRating) {
        this.feedbackRating = feedbackRating;
    }

    public Integer getTotalEnrolls() {
        return this.totalEnrolls;
    }

    public void setTotalEnrolls(Integer totalEnrolls) {
        this.totalEnrolls = totalEnrolls;
    }

}
