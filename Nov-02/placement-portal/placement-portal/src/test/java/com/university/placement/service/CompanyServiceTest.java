package com.university.placement.service;

import com.university.placement.entity.CompanyEntity;
import com.university.placement.entity.PlacementStatus;
import com.university.placement.entity.StudentEntity;
import com.university.placement.exception.DuplicateResourceException;
import com.university.placement.exception.ResourceNotFoundException;
import com.university.placement.exception.ValidationException;
import com.university.placement.repository.CompanyRepository;
import com.university.placement.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CompanyServiceTest {

    private CompanyRepository companyRepository;
    private StudentRepository studentRepository;
    private CompanyService service;

    @BeforeEach
    void setUp() {
        companyRepository = mock(CompanyRepository.class);
        studentRepository = mock(StudentRepository.class);
        service = new CompanyService(companyRepository, new StudentService(studentRepository));
    }

    @Test
    void registersCompany() {
        when(companyRepository.save(any(CompanyEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        CompanyEntity result = service.registerCompany(" Acme ", new BigDecimal("7.00"),
                1, new BigDecimal("10.00"));

        assertEquals("Acme", result.getName());
        assertEquals(new BigDecimal("7.00"), result.getMinCgpa());
        assertEquals(new BigDecimal("10.00"), result.getPackageLpa());
        verify(companyRepository).save(any(CompanyEntity.class));
    }

    @Test
    void rejectsInvalidCriteria() {
        assertThrows(ValidationException.class,
                () -> service.registerCompany("", new BigDecimal("7.0"), 0, new BigDecimal("5.0")));
        assertThrows(ValidationException.class,
                () -> service.registerCompany("Acme", new BigDecimal("11"), 0, new BigDecimal("5.0")));
        assertThrows(ValidationException.class,
                () -> service.registerCompany("Acme", new BigDecimal("7.0"), -1, new BigDecimal("5.0")));
        assertThrows(ValidationException.class,
                () -> service.registerCompany("Acme", new BigDecimal("7.0"), 0, BigDecimal.ZERO));
        verify(companyRepository, never()).save(any(CompanyEntity.class));
    }

    @Test
    void rejectsDuplicateCompanyName() {
        when(companyRepository.existsByNameIgnoreCase("Acme")).thenReturn(true);
        assertThrows(DuplicateResourceException.class,
                () -> service.registerCompany("Acme", new BigDecimal("7.0"), 0, new BigDecimal("5.0")));
    }

    @Test
    void eligibleDrivesFailForUnknownStudent() {
        when(studentRepository.findById("NOPE")).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.findEligibleDrives("nope"));
        verify(companyRepository, never()).findEligibleForStudent("NOPE");
    }

    @Test
    void eligibleDriveQueryUsesNormalizedStudentRoll() {
        when(studentRepository.findById("CS1")).thenReturn(Optional.of(
                new StudentEntity("CS1", "Asha", new BigDecimal("8.0"), 0, PlacementStatus.UNPLACED)));

        service.findEligibleDrives("cs1");

        verify(companyRepository).findEligibleForStudent("CS1");
    }
}
