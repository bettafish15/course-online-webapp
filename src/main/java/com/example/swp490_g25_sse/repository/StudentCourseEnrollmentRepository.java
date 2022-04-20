package com.example.swp490_g25_sse.repository;

import com.example.swp490_g25_sse.model.Course;
import com.example.swp490_g25_sse.model.Student;
import com.example.swp490_g25_sse.model.StudentCourseEnrollment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCourseEnrollmentRepository extends JpaRepository<StudentCourseEnrollment, Long> {
   StudentCourseEnrollment findFirstByStudentAndCourse(Student student, Course course);

   java.util.List<StudentCourseEnrollment> findByStudent(Student student);

   Page<StudentCourseEnrollment> findByStudentAndIsFinished(Student student, Boolean isFinished, Pageable pageable);

   int countByIsFinishedAndStudentId(Boolean isFinished, Student student);

   long countByStudentAndIsFinished(Student student, Boolean isFinished);

   long countByStudent(Student student);
}
