/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.swp490_g25_sse.service;

import com.example.swp490_g25_sse.dto.CourseDto;
import com.example.swp490_g25_sse.dto.LectureDto;
import com.example.swp490_g25_sse.dto.TestDto;
import com.example.swp490_g25_sse.exception.BaseRestException;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
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
	private TeacherRepository teacherRepository;

	@Override
	public Optional<Course> getCourseById(long id) {
		return courseRepository.findById(id);
	}

	@Override
	@Transactional(rollbackFor = { Exception.class, Throwable.class })
	public Course createCourse(CourseDto dto) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetailsService currentUser = (CustomUserDetailsService) auth.getPrincipal();

		Teacher teacher = teacherRepository.findFirstByUserId(currentUser.getUser().getId());

		Course course = new Course(teacher,
				dto.getCourseImgUrl(),
				dto.getCourseTitle(),
				dto.getContent());

		course = courseRepository.save(course);
		List<Lecture> lectures = DtoToDaoConversion.convertLectureDtosToListOfLectureModel(dto.getLectureDtos(),
				course);
		System.out.println(lectures);
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
		courseRepository.delete(course.get());

		return course;
	}

	@Override
	@Transactional(rollbackFor = { Exception.class, Throwable.class })
	public Course updateCourse(CourseDto courseDto, long id) {
		Optional<Course> courseOptionalWrapper = courseRepository.findById(id);
		Course course = courseOptionalWrapper.get();

		if (courseOptionalWrapper.isPresent()) {
			course.setTitle(courseDto.getCourseTitle());
			course.setContent(courseDto.getContent());
			course.setImageUrl(courseDto.getCourseImgUrl());
			List<LectureDto> lectureDtos = courseDto.getLectureDtos();
			List<TestDto> testDtos = courseDto.getTestDtos();

			for (int i = 0; i < lectureDtos.size(); i++) {
				LectureDto lectureDto = lectureDtos.get(i);
				Lecture lecture;

				if (lectureDto.id != null) {
					lecture = lectureRepository.getById(lectureDto.id);
					if (lecture.getCourse().getId() != course.getId()) {
						System.out.println("=======================================================================");
						throw new BaseRestException(HttpStatus.FORBIDDEN,
								"there is a lecture that is not belong to this course");
					}
					lecture.setName(lectureDto.getName());
					lecture.setContent(lectureDto.getContent());
					lecture.setIndexOrder(lectureDto.getIndex());
					lecture.setResourceUrl(lectureDto.getResourceUrl());
					lecture.setWeek(lectureDto.getWeek());

				} else {
					lecture = new Lecture(course, lectureDto.getWeek(), lectureDto.getName(), lectureDto.getContent(),
							lectureDto.getResourceUrl(), lectureDto.getIndex());
				}

				lectureRepository.save(lecture);
			}

			for (int i = 0; i < testDtos.size(); i++) {
				TestDto testDto = testDtos.get(i);
				Test test;

				if (testDto.id != null) {
					test = testRepository.getById(testDto.id);
					if (test.getCourse().getId() != course.getId()) {
						System.out.println("=======================================================================");
						throw new BaseRestException(HttpStatus.FORBIDDEN,
								"there is a test that is not belong to this course");
					}
					test.setName(testDto.getName());
					test.setContent(testDto.getContent());
					test.setIndexOrder(testDto.getIndex());
					test.setWeek(testDto.getWeek());

				} else {
					test = new Test(course, testDto.getWeek(), testDto.getName(), testDto.getContent(),
							testDto.getIndex());
				}

				testRepository.save(test);
			}

			courseRepository.save(course);
		}

		courseOptionalWrapper = courseRepository.findById(id);
		course = courseOptionalWrapper.get();

		return course;
	}

	@Override
	public Page<Course> getMostEnrolledCourses() {
		// Page<Course> courses = courseRepository.findAll(Pageable.ofSize(10));
		Page<Course> courses = courseRepository.findAll(PageRequest.of(0, 4));
		return courses;
	}

}
