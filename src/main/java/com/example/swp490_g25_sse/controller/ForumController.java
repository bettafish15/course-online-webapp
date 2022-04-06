package com.example.swp490_g25_sse.controller;

import java.io.Console;
import java.util.List;
import java.util.Optional;

import com.example.swp490_g25_sse.dto.AnswerDto;
import com.example.swp490_g25_sse.dto.QuestionDto;
import com.example.swp490_g25_sse.model.Answer;
import com.example.swp490_g25_sse.model.Course;
import com.example.swp490_g25_sse.model.Question;
import com.example.swp490_g25_sse.model.Student;
import com.example.swp490_g25_sse.model.User;
import com.example.swp490_g25_sse.repository.StudentRepository;
import com.example.swp490_g25_sse.service.AnswerService;
import com.example.swp490_g25_sse.service.CourseService;
import com.example.swp490_g25_sse.service.CustomUserDetailsService;
import com.example.swp490_g25_sse.service.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app/student/forum")
public class ForumController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AnswerService answerService;

    @GetMapping("/{id}")
    private String index(Model model, @PathVariable String id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetailsService userDetails = (CustomUserDetailsService) auth.getPrincipal();

        List<Question> questions = questionService.getQuestionsByCourseId(Long.parseLong(id));

        Course course = courseService.getCourseById(Long.parseLong(id)).get();
        model.addAttribute("questions", questions);
        model.addAttribute("course", course);
        model.addAttribute("userName", userDetails.getUser().getFirstName());
        return "student/forum-questions";
    }

    @GetMapping("/question/{id}")
    private String getStudentQuestion(Model model, @PathVariable String id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetailsService user = (CustomUserDetailsService) auth.getPrincipal();
        Student student = studentRepository.findFirstByUserId(user.getUser().getId());
        Long studentId = student.getId();
        Course course = courseService.getCourseById(Long.parseLong(id)).get();
        List<Question> questions = questionService.getQuestionsByCourseIdAndUserId(Long.parseLong(id), studentId);
        System.out.println(questions);
        model.addAttribute("questions", questions);
        model.addAttribute("course", course);

        return "student/student-questions";
    }

    @GetMapping("/creat/{id}")
    private String createQuestion(Model model, @PathVariable String id) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetailsService user = (CustomUserDetailsService) auth.getPrincipal();
        Student student = studentRepository.findFirstByUserId(user.getUser().getId());
        Long studentId = student.getId();
        Question question = new Question();
        Course course = courseService.getCourseById(Long.parseLong(id)).get();
        model.addAttribute("question", question);
        model.addAttribute("course", course);
        model.addAttribute("studentId", studentId);
        return "student/course-question";
    }

    @PostMapping("/creat/{id}")
    private String creatQuestion(@ModelAttribute("question") QuestionDto questionDto) {
        questionService.createQuestion(questionDto);
        return "redirect:/app/student/forum/{id}";
    }

    @GetMapping("/answer/{courseId}/{id}")
    private String getStudentAnswer(Model model, @PathVariable String id, @PathVariable String courseId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetailsService user = (CustomUserDetailsService) auth.getPrincipal();
        Student student = studentRepository.findFirstByUserId(user.getUser().getId());
        Long studentId = student.getId();

        Question question = questionService.getQuestionById(Long.parseLong(id)).get();

        Course course = courseService.getCourseById(Long.parseLong(courseId)).get();
        Answer answer = new Answer();

        List<Answer> answers = answerService.getAnswersByQuestionId(Long.parseLong(id));

        model.addAttribute("question", question);
        model.addAttribute("course", course);
        model.addAttribute("studentId", studentId);
        model.addAttribute("answer", answer);
        model.addAttribute("answers", answers);
        model.addAttribute("userName", user.getUser().getFirstName());
        return "student/forum-answers";
    }

    @PostMapping("/answer/creat/{courseId}/{questionId}")
    private String creatAnswer(@ModelAttribute("answer") AnswerDto answerDto, @PathVariable String questionId) {
        answerService.createAnswer(answerDto, Long.parseLong(questionId));
        return "redirect:/app/student/forum/answer/{courseId}/{questionId}";
    }
}
