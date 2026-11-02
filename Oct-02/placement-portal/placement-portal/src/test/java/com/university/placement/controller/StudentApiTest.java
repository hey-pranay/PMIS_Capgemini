package com.university.placement.controller;

import com.university.placement.entity.PlacementStatus;
import com.university.placement.entity.StudentEntity;
import com.university.placement.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
class StudentApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    void registersStudentAsJson() throws Exception {
        when(studentService.registerStudent("CS24001", "Asha Rao", new BigDecimal("8.75"), 0))
                .thenReturn(new StudentEntity("CS24001", "Asha Rao", new BigDecimal("8.75"),
                        0, PlacementStatus.UNPLACED));

        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"rollNo":"CS24001","name":"Asha Rao","cgpa":8.75,"activeBacklogs":0}
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.rollNo").value("CS24001"))
                .andExpect(jsonPath("$.placementStatus").value("UNPLACED"));

        verify(studentService).registerStudent("CS24001", "Asha Rao", new BigDecimal("8.75"), 0);
    }

    @Test
    void rejectsInvalidStudentPayloadAsJson() throws Exception {
        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"rollNo":"","name":"","cgpa":12.5,"activeBacklogs":-1}
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").isNotEmpty());
    }
}
