/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.swp490_g25_sse.service;

import com.example.swp490_g25_sse.dto.CourseDto;
import com.example.swp490_g25_sse.model.Course;
import com.example.swp490_g25_sse.model.Lecture;
import com.example.swp490_g25_sse.model.Teacher;
import com.example.swp490_g25_sse.model.Test;
import com.example.swp490_g25_sse.model.User;
import com.example.swp490_g25_sse.repository.CourseRepository;
import com.example.swp490_g25_sse.repository.LectureRepository;
import com.example.swp490_g25_sse.repository.TeacherRepository;
import com.example.swp490_g25_sse.repository.TestRepository;
import com.example.swp490_g25_sse.repository.UserRepository;
import com.example.swp490_g25_sse.util.DtoToDaoConversion;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author bettafish15
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public Optional<Course> getCourseById(long id) {
        return courseRepository.findById(id);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public Course createCourse(CourseDto dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetailsService currentUser = (CustomUserDetailsService) auth.getPrincipal();

        User currentAccount = userRepository.findFirstByEmail((currentUser.getUser().getEmail()));
        Teacher teacher = teacherRepository.findFirstByUserId(currentAccount.getId());

        Course course = new Course(teacher,
                dto.getCourseImgUrl(),
                dto.getCourseTitle(),
                dto.getContent());

        course = courseRepository.save(course);
        List<Lecture> lectures = DtoToDaoConversion.convertLectureDtosToListOfLectureModel(dto.getLectureDtos(), course);
        List<Test> tests = DtoToDaoConversion.convertTestDtosToListOfTestModel(dto.getTestDtos(), course);
        lectureRepository.saveAll(lectures);
        testRepository.saveAll(tests);

        return courseRepository.save(course);
    }

    @Override
    public List<Course> getCourses() {
        return courseRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    public Optional<Course> deleteCourse(long id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            courseRepository.delete(course.get());
        }
        
        return course;
    }

}
