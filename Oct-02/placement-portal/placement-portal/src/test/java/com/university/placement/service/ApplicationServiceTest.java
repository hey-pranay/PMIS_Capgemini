package com.university.placement.service;

import com.university.placement.api.StatusUpdateResponse;
import com.university.placement.entity.ApplicationEntity;
import com.university.placement.entity.CompanyEntity;
import com.university.placement.entity.InterviewStatus;
import com.university.placement.entity.PlacementStatus;
import com.university.placement.entity.StudentEntity;
import com.university.placement.exception.DuplicateResourceException;
import com.university.placement.exception.EligibilityException;
import com.university.placement.exception.InvalidStatusTransitionException;
import com.university.placement.exception.ResourceNotFoundException;
import com.university.placement.repository.ApplicationRepository;
import com.university.placement.repository.CompanyRepository;
import com.university.placement.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ApplicationServiceTest {

    private ApplicationRepository applicationRepository;
    private StudentRepository studentRepository;
    private CompanyRepository companyRepository;
    private ApplicationService service;

    @BeforeEach
    void setUp() {
        applicationRepository = mock(ApplicationRepository.class);
        studentRepository = mock(StudentRepository.class);
        companyRepository = mock(CompanyRepository.class);
        service = new ApplicationService(applicationRepository, studentRepository, companyRepository);
    }

    private StudentEntity student(String roll, String cgpa, int backlogs, PlacementStatus status) {
        return new StudentEntity(roll, "Test " + roll, new BigDecimal(cgpa), backlogs, status);
    }

    private CompanyEntity company(String name, String minCgpa, int maxBacklogs, String packageLpa) {
        CompanyEntity company = new CompanyEntity(name, new BigDecimal(minCgpa),
                maxBacklogs, new BigDecimal(packageLpa));
        ReflectionTestUtils.setField(company, "companyId", 1);
        return company;
    }

    private ApplicationEntity application(int appId, StudentEntity student,
                                          CompanyEntity company, InterviewStatus status) {
        return new ApplicationEntity(student, company, status);
    }

    @Test
    void eligibleStudentCanApply() {
        StudentEntity student = student("CS1", "8.50", 0, PlacementStatus.UNPLACED);
        CompanyEntity company = company("Acme", "8.00", 0, "28.00");
        when(studentRepository.findById("CS1")).thenReturn(Optional.of(student));
        when(companyRepository.findById(1)).thenReturn(Optional.of(company));
        when(applicationRepository.save(any(ApplicationEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var result = service.applyForDrive("cs1", 1);

        assertEquals("CS1", result.getRollNo());
        assertEquals(1, result.getCompanyId());
        assertEquals(InterviewStatus.APPLIED, result.getInterviewStatus());
        verify(applicationRepository).save(any(ApplicationEntity.class));
    }

    @Test
    void rejectsUnknownStudentOrCompany() {
        when(studentRepository.findById("GHOST")).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.applyForDrive("ghost", 1));

        when(studentRepository.findById("CS1")).thenReturn(Optional.of(
                student("CS1", "9.0", 0, PlacementStatus.UNPLACED)));
        when(companyRepository.findById(404)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.applyForDrive("CS1", 404));
    }

    @Test
    void rejectsIneligibleStudentsAndDuplicates() {
        when(studentRepository.findById("CS1")).thenReturn(Optional.of(
                student("CS1", "7.99", 1, PlacementStatus.UNPLACED)));
        when(studentRepository.findById("CS2")).thenReturn(Optional.of(
                student("CS2", "9.0", 0, PlacementStatus.PLACED)));
        when(companyRepository.findById(1)).thenReturn(Optional.of(company("Acme", "8.0", 0, "12.0")));

        assertThrows(EligibilityException.class, () -> service.applyForDrive("CS1", 1));
        assertThrows(EligibilityException.class, () -> service.applyForDrive("CS2", 1));
        verify(applicationRepository, never()).save(any(ApplicationEntity.class));

        when(studentRepository.findById("CS3")).thenReturn(Optional.of(
                student("CS3", "9.0", 0, PlacementStatus.UNPLACED)));
        when(applicationRepository.existsByStudent_RollNoAndCompany_CompanyId("CS3", 1)).thenReturn(true);
        assertThrows(DuplicateResourceException.class, () -> service.applyForDrive("CS3", 1));
    }

    @Test
    void offerMarksStudentPlacedAndCancelsLowerPackageApplications() {
        StudentEntity student = student("CS1", "8.10", 0, PlacementStatus.UNPLACED);
        CompanyEntity company = company("Amazon", "8.00", 0, "28.00");
        ApplicationEntity application = application(5, student, company, InterviewStatus.INTERVIEWING);
        when(applicationRepository.findById(5)).thenReturn(Optional.of(application));
        when(applicationRepository.rejectPendingApplicationsForLowerPackages("CS1", company.getPackageLpa()))
                .thenReturn(2);

        StatusUpdateResponse result = service.updateInterviewStatus(5, InterviewStatus.OFFERED);

        assertEquals(InterviewStatus.OFFERED, result.getApplication().getInterviewStatus());
        assertTrue(result.isStudentMarkedPlaced());
        assertEquals(2, result.getCancelledApplications());
        assertEquals(PlacementStatus.PLACED, student.getPlacementStatus());
        verify(applicationRepository).rejectPendingApplicationsForLowerPackages("CS1", company.getPackageLpa());
    }

    @Test
    void nonOfferTransitionDoesNotChangePlacementOrOtherApplications() {
        ApplicationEntity application = application(7,
                student("CS1", "8.10", 0, PlacementStatus.UNPLACED),
                company("Acme", "6.0", 5, "12.0"), InterviewStatus.APPLIED);
        when(applicationRepository.findById(7)).thenReturn(Optional.of(application));

        StatusUpdateResponse result = service.updateInterviewStatus(7, InterviewStatus.SHORTLISTED);

        assertEquals(InterviewStatus.SHORTLISTED, result.getApplication().getInterviewStatus());
        assertFalse(result.isStudentMarkedPlaced());
        assertEquals(0, result.getCancelledApplications());
        verify(applicationRepository, never()).rejectPendingApplicationsForLowerPackages(any(), any());
    }

    @Test
    void illegalTransitionsAreBlocked() {
        ApplicationEntity application = application(7,
                student("CS1", "8.10", 0, PlacementStatus.UNPLACED),
                company("Acme", "6.0", 5, "12.0"), InterviewStatus.APPLIED);
        when(applicationRepository.findById(7)).thenReturn(Optional.of(application));

        assertThrows(InvalidStatusTransitionException.class,
                () -> service.updateInterviewStatus(7, InterviewStatus.OFFERED));
    }

    @Test
    void optedOutStudentCannotReceiveOffer() {
        ApplicationEntity application = application(7,
                student("CS1", "8.10", 0, PlacementStatus.OPTED_OUT),
                company("Acme", "6.0", 5, "12.0"), InterviewStatus.INTERVIEWING);
        when(applicationRepository.findById(7)).thenReturn(Optional.of(application));

        assertThrows(EligibilityException.class,
                () -> service.updateInterviewStatus(7, InterviewStatus.OFFERED));
        verify(applicationRepository, never()).rejectPendingApplicationsForLowerPackages(any(), any());
    }

    @Test
    void placedStudentCanOnlyUpgradePackage() {
        StudentEntity student = student("CS1", "8.10", 0, PlacementStatus.PLACED);
        CompanyEntity company = company("NewCo", "6.0", 5, "25.0");
        ApplicationEntity application = application(7, student, company, InterviewStatus.INTERVIEWING);
        when(applicationRepository.findById(7)).thenReturn(Optional.of(application));
        when(applicationRepository.findHighestOfferedPackage("CS1", InterviewStatus.OFFERED))
                .thenReturn(new BigDecimal("28.0"));

        assertThrows(EligibilityException.class,
                () -> service.updateInterviewStatus(7, InterviewStatus.OFFERED));
        verify(applicationRepository, never()).rejectPendingApplicationsForLowerPackages(any(), any());
    }
}
