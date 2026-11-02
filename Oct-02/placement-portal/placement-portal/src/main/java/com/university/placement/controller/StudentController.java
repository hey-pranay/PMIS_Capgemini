package com.university.placement.controller;

import com.university.placement.api.StudentRequest;
import com.university.placement.entity.StudentEntity;
import com.university.placement.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentEntity registerStudent(@Valid @RequestBody StudentRequest request) {
        return studentService.registerStudent(request.getRollNo(), request.getName(),
                request.getCgpa(), request.getActiveBacklogs());
    }

    @GetMapping
    public List<StudentEntity> listStudents() {
        return studentService.listStudents();
    }

    @GetMapping("/{rollNo}")
    public StudentEntity getStudent(@PathVariable String rollNo) {
        return studentService.getStudent(rollNo);
    }

    @PostMapping("/{rollNo}/opt-out")
    public StudentEntity optOut(@PathVariable String rollNo) {
        return studentService.optOut(rollNo);
    }
}
