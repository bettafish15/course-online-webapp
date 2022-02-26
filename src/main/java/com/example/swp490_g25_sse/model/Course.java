/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.swp490_g25_sse.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "Course")
public class Course {

    private long courseId;
    private String courseName;
    private Date createDate;
    private Date startAt;
    private Date endAt;
    private Date updateDate;
    private long userId;
    private List<CourseDescription> courseDes = new ArrayList<>();
    private List<CourseImage> courseImg = new ArrayList<>();

    public Course() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    @Column(name = "courseName", nullable = false)
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Column(name = "createDate", nullable = false)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "startAt", nullable = false)
    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    @Column(name = "endAt", nullable = false)
    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    @Column(name = "updateDate", nullable = true)
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Column(name = "userId", nullable = false)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<CourseDescription> getCourseDes() {
        return courseDes;
    }

    public void setCourseDes(List<CourseDescription> courseDes) {
        this.courseDes = courseDes;
    }

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<CourseImage> getCourseImg() {
        return courseImg;
    }

    public void setCourseImg(List<CourseImage> courseImg) {
        this.courseImg = courseImg;
    }

    public void addImg(CourseImage img) {
        img.setCourse(this);
        img.getCourse().setCourseId(this.courseId);
        this.courseImg.add(img);
    }

    public void addDes(CourseDescription des) {
        des.setCourse(this);
        des.getCourse().setCourseId(this.courseId);
        this.courseDes.add(des);
    }

}
