/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.swp490_g25_sse.controller;
import javax.validation.Valid;

import com.example.swp490_g25_sse.exception.ResourceNotFoundException;
import com.example.swp490_g25_sse.model.CourseLecture;
import com.example.swp490_g25_sse.model.LectureAttachement;
import com.example.swp490_g25_sse.repository.LectureRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ADMIN
 */
@RestController
@RequestMapping("/lectures")
public class LectureController {
    
    @Autowired
    private LectureRepository lectureRepository;

    @GetMapping("")
    public List<CourseLecture> getAllCourseLectures() {
        return lectureRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CourseLecture> getCourseLectureById(@PathVariable(value = "id") int CourseLectureId)
            throws ResourceNotFoundException, Exception {
        CourseLecture CourseLecture = lectureRepository.findById(CourseLectureId)
                .orElseThrow(() -> new Exception("CourseLecture not found for this id :: " + CourseLectureId));
        return ResponseEntity.ok().body(CourseLecture);
    }
    
        @PostMapping("/add")
    public CourseLecture createCourseLecture(@Valid @RequestBody CourseLecture CourseLecture) {
        LectureAttachement attachement = new LectureAttachement();
        attachement.setAttachUrl("okeokeoke");
        CourseLecture.addAttachement(attachement);
        return lectureRepository.save(CourseLecture);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<CourseLecture> updateCourseLecture(@PathVariable(value = "id") int CourseLectureId,
            @Valid @RequestBody CourseLecture CourseLectureDetails) throws ResourceNotFoundException {
        CourseLecture CourseLecture = lectureRepository.findById(CourseLectureId)
                .orElseThrow(() -> new ResourceNotFoundException("CourseLecture not found for this id :: " + CourseLectureId));

        CourseLecture.setLectureName(CourseLectureDetails.getLectureName());
        CourseLecture.setLectureWeek(CourseLectureDetails.getLectureWeek());
        CourseLecture.setCourseId(CourseLectureDetails.getCourseId());
        CourseLecture.setMarkAsRead(CourseLectureDetails.isMarkAsRead());
        for (LectureAttachement attachement : CourseLecture.getLectureAttachements()) {
            if (attachement.getAttachId() == 12) {
                attachement.setAttachUrl("Đã cập nhật thành công");                
            }
        }

        final CourseLecture updatedCourseLecture = lectureRepository.save(CourseLecture);
        return ResponseEntity.ok(updatedCourseLecture);
    }
    
      @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteCourseLecture(@PathVariable(value = "id") int CourseLectureId)
            throws ResourceNotFoundException {
        CourseLecture CourseLecture = lectureRepository.findById(CourseLectureId)
                .orElseThrow(() -> new ResourceNotFoundException("CourseLecture not found for this id :: " + CourseLectureId));

        lectureRepository.delete(CourseLecture);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    
}
