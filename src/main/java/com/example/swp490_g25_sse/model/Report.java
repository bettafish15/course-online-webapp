/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.swp490_g25_sse.model;

import org.springframework.stereotype.Component;

/**
 *
 * @author ADMIN
 */
@Component
public class Report {
    private int reportId;
    private String reportText;
    private int userId;
    private int courseId;

    public Report() {
    }

    public Report(int reportId, String reportText, int userId, int courseId) {
        this.reportId = reportId;
        this.reportText = reportText;
        this.userId = userId;
        this.courseId = courseId;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public String getReportText() {
        return reportText;
    }

    public void setReportText(String reportText) {
        this.reportText = reportText;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    
    
}
