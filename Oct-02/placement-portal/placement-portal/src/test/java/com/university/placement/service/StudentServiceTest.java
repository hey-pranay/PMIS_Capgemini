package com.university.placement.service;

import com.university.placement.entity.PlacementStatus;
import com.university.placement.entity.StudentEntity;
import com.university.placement.exception.DuplicateResourceException;
import com.university.placement.exception.EligibilityException;
import com.university.placement.exception.ResourceNotFoundException;
import com.university.placement.exception.ValidationException;
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

class StudentServiceTest {

    private StudentRepository repository;
    private StudentService service;

    @BeforeEach
    void setUp() {
        repository = mock(StudentRepository.class);
        service = new StudentService(repository);
    }

    @Test
    void registersStudentAsUnplacedWithNormalizedRollNo() {
        when(repository.save(any(StudentEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        StudentEntity student = service.registerStudent(" cs22001 ", " Asha Rao ",
                new BigDecimal("8.75"), 0);

        assertEquals("CS22001", student.getRollNo());
        assertEquals("Asha Rao", student.getName());
        assertEquals(PlacementStatus.UNPLACED, student.getPlacementStatus());
        verify(repository).save(student);
    }

    @Test
    void rejectsBlankRollNoAndName() {
        assertThrows(ValidationException.class,
                () -> service.registerStudent(" ", "Asha", new BigDecimal("8.0"), 0));
        assertThrows(ValidationException.class,
                () -> service.registerStudent("CS1", "  ", new BigDecimal("8.0"), 0));
        verify(repository, never()).save(any(StudentEntity.class));
    }

    @Test
    void rejectsCgpaOutOfRangeOrTooPrecise() {
        assertThrows(ValidationException.class,
                () -> service.registerStudent("CS1", "A", new BigDecimal("10.01"), 0));
        assertThrows(ValidationException.class,
                () -> service.registerStudent("CS1", "A", new BigDecimal("-0.5"), 0));
        assertThrows(ValidationException.class,
                () -> service.registerStudent("CS1", "A", new BigDecimal("8.123"), 0));
        verify(repository, never()).save(any(StudentEntity.class));
    }

    @Test
    void rejectsNegativeBacklogsAndDuplicateRollNo() {
        assertThrows(ValidationException.class,
                () -> service.registerStudent("CS1", "A", new BigDecimal("8.0"), -1));
        when(repository.existsByRollNo("CS1")).thenReturn(true);
        assertThrows(DuplicateResourceException.class,
                () -> service.registerStudent("cs1", "A", new BigDecimal("8.0"), 0));
        verify(repository, never()).save(any(StudentEntity.class));
    }

    @Test
    void getStudentThrowsWhenMissing() {
        when(repository.findById("NOPE")).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.getStudent("nope"));
    }

    @Test
    void placedStudentCannotOptOut() {
        when(repository.findById("CS1")).thenReturn(Optional.of(
                new StudentEntity("CS1", "A", new BigDecimal("8.0"), 0, PlacementStatus.PLACED)));
        assertThrows(EligibilityException.class, () -> service.optOut("CS1"));
    }

    @Test
    void unplacedStudentCanOptOut() {
        StudentEntity student = new StudentEntity("CS1", "A", new BigDecimal("8.0"),
                0, PlacementStatus.UNPLACED);
        when(repository.findById("CS1")).thenReturn(Optional.of(student));

        StudentEntity result = service.optOut("CS1");

        assertEquals(PlacementStatus.OPTED_OUT, result.getPlacementStatus());
        verify(repository).findById("CS1");
    }
}
