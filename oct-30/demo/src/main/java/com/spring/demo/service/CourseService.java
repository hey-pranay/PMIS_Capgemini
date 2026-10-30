package com.spring.demo.service;

import com.spring.demo.entity.Course;
import com.spring.demo.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    // constructor injection
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourse() {
        List<Course> courses = courseRepository.findAll();

        if (courses.isEmpty()) {
            throw new RuntimeException("No course found. Please create a new course.");
        }

        return courses;
    }

    public Course createCourse(Course course) {

        if (course == null) {
            throw new IllegalArgumentException("Course cannot be empty.");
        }

        if (course.getTitle() == null || course.getTitle().isBlank()) {
            throw new IllegalArgumentException("Course title is required");
        }

        return courseRepository.save(course);
    }

    public Course getCourseById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid course ID ");
        }
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id : " + id));

    }

    public Course updateCourse(Long id, Course updateCourse) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid course ID ");
        }

        if (updateCourse == null) {
            throw new IllegalArgumentException("Updated course cannot be empty");
        }

        if (updateCourse.getTitle() == null || updateCourse.getTitle().isBlank()) {
            throw new IllegalArgumentException("Course title is required.");
        }

        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Course not found with ID : " + id
                ));

        existingCourse.setTitle(updateCourse.getTitle());
        existingCourse.setDescription(updateCourse.getDescription());

        return courseRepository.save(existingCourse);
    }

    public void deleteCourseById(Long id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid course ID");
        }

        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Course not found with ID: " + id)
                );

        courseRepository.delete(course);
    }

}