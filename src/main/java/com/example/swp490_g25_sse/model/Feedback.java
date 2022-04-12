package com.example.swp490_g25_sse.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;

@Entity()
@Table(name = "feedbacks")
public class Feedback {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "course_id", insertable = true, updatable = true)
  private Course course;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "student_id", insertable = true, updatable = true)
  private Student student;

  private Integer rating;

  private String contentKey;

  private String content;

  public Feedback() {
  }

  public Feedback(Course course, Student student, Integer rating, String contentKey, String content) {
    this.course = course;
    this.student = student;
    this.rating = rating;
    this.contentKey = contentKey;
    this.content = content;
  }

  public Feedback(Long id, Course course, Student student, Integer rating, String contentKey, String content) {
    this.id = id;
    this.course = course;
    this.student = student;
    this.rating = rating;
    this.contentKey = contentKey;
    this.content = content;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Course getCourse() {
    return this.course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public Student getStudent() {
    return this.student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public Integer getRating() {
    return this.rating;
  }

  public void setRating(Integer rating) {
    this.rating = rating;
  }

  public String getKey() {
    return this.contentKey;
  }

  public void setKey(String contentKey) {
    this.contentKey = contentKey;
  }

  public String getContent() {
    return this.content;
  }

  public void setContent(String content) {
    this.content = content;
  }

}
