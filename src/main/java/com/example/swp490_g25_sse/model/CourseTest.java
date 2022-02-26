/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.swp490_g25_sse.model;

import java.util.Date;
import org.springframework.stereotype.Component;

/**
 *
 * @author ADMIN
 */
@Component
public class CourseTest {

    private int testId;
    private String testName;
    private Date testStart;
    private Date testEnd;
    private Date testDuration;
    private int score;
    private boolean hasDo;
    private int courseId;

    public CourseTest() {
    }

    public CourseTest(int testId, String testName, Date testStart, Date testEnd, Date testDuration, int score, boolean hasDo, int courseId) {
        this.testId = testId;
        this.testName = testName;
        this.testStart = testStart;
        this.testEnd = testEnd;
        this.testDuration = testDuration;
        this.score = score;
        this.hasDo = hasDo;
        this.courseId = courseId;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Date getTestStart() {
        return testStart;
    }

    public void setTestStart(Date testStart) {
        this.testStart = testStart;
    }

    public Date getTestEnd() {
        return testEnd;
    }

    public void setTestEnd(Date testEnd) {
        this.testEnd = testEnd;
    }

    public Date getTestDuration() {
        return testDuration;
    }

    public void setTestDuration(Date testDuration) {
        this.testDuration = testDuration;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isHasDo() {
        return hasDo;
    }

    public void setHasDo(boolean hasDo) {
        this.hasDo = hasDo;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
     

}
