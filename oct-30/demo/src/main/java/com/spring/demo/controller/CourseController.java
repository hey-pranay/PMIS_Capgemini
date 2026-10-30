package com.spring.demo.controller;

import com.spring.demo.entity.Course;
import com.spring.demo.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(
            @RequestBody Course course
    ) {
        Course savedCourse = courseService.createCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedCourse);
    }

    @GetMapping
    public ResponseEntity<?> getAllCourse() {
        List<Course> courses = courseService.getAllCourse();

        if (courses.isEmpty()) {
            return ResponseEntity.ok(
                    "No courses found. Please create a new course."
            );
        }
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(
            @PathVariable Long id
    ) {
        Course course = courseService.getCourseById(id);
        return ResponseEntity.ok(course);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourseById(
            @PathVariable Long id,
            @RequestBody Course course
    ) {
        Course updatedCourse = courseService.updateCourse(id, course);

        return ResponseEntity.ok(updatedCourse);
    }


    @DeleteMapping
    public ResponseEntity<String> deleteCourseById(
            @PathVariable Long id
    ) {
        courseService.deleteCourseById(id);
        return ResponseEntity.ok("Course deleted successfully");
    }


}