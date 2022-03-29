package com.example.swp490_g25_sse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity()
@Table(name = "test_results")
public class TestResult {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "enrollment_id", insertable = true, updatable = true)
  @JsonIgnore
  private StudentCourseEnrollment enrollment;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "test_id", insertable = true, updatable = true)
  @JsonIgnore
  private Test test;

  private Boolean isFinished;

  private Integer mark;

  public TestResult() {
  }

  public TestResult(StudentCourseEnrollment enrollment, Test test, Boolean isFinished, Integer mark) {
    this.enrollment = enrollment;
    this.test = test;
    this.isFinished = isFinished;
    this.mark = mark;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public StudentCourseEnrollment getEnrollment() {
    return this.enrollment;
  }

  public void setEnrollment(StudentCourseEnrollment enrollment) {
    this.enrollment = enrollment;
  }

  public Test getTest() {
    return this.test;
  }

  public void setTest(Test test) {
    this.test = test;
  }

  public Boolean isIsFinished() {
    return this.isFinished;
  }

  public Boolean getIsFinished() {
    return this.isFinished;
  }

  public void setIsFinished(Boolean isFinished) {
    this.isFinished = isFinished;
  }

  public Integer getMark() {
    return this.mark;
  }

  public void setMark(Integer mark) {
    this.mark = mark;
  }

}
