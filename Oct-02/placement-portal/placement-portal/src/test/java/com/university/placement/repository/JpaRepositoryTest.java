package com.university.placement.repository;

import com.university.placement.entity.ApplicationEntity;
import com.university.placement.entity.CompanyEntity;
import com.university.placement.entity.StudentEntity;
import com.university.placement.entity.InterviewStatus;
import com.university.placement.entity.PlacementStatus;
import com.university.placement.config.PlacementDataInitializer;
import org.springframework.boot.DefaultApplicationArguments;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
class JpaRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Test
    void initializesSampleRowsThroughJpaOnlyOnce() {
        PlacementDataInitializer dataInitializer = new PlacementDataInitializer(
                studentRepository, companyRepository, applicationRepository);
        dataInitializer.run(new DefaultApplicationArguments(new String[0]));

        assertEquals(14, studentRepository.count());
        assertEquals(9, companyRepository.count());
        assertEquals(24, applicationRepository.count());

        dataInitializer.run(new DefaultApplicationArguments(new String[0]));

        assertEquals(14, studentRepository.count());
        assertEquals(9, companyRepository.count());
        assertEquals(24, applicationRepository.count());
    }

    @Test
    void persistsEntitiesAndRunsEligibilityAndDriveSummaryQueries() {
        StudentEntity student = studentRepository.save(new StudentEntity(
                "CS1", "Asha Rao", new BigDecimal("8.50"), 0, PlacementStatus.UNPLACED));
        CompanyEntity eligibleCompany = companyRepository.save(new CompanyEntity(
                "Acme", new BigDecimal("8.00"), 0, new BigDecimal("12.00")));
        companyRepository.save(new CompanyEntity(
                "Other", new BigDecimal("9.00"), 0, new BigDecimal("20.00")));
        applicationRepository.save(new ApplicationEntity(student, eligibleCompany, InterviewStatus.OFFERED));

        assertEquals(1, companyRepository.findEligibleForStudent("CS1").size());
        assertEquals("Acme", companyRepository.findEligibleForStudent("CS1").getFirst().getName());

        var summaries = companyRepository.findDriveSummaries();
        assertEquals(2, summaries.size());
        assertEquals("Other", summaries.getFirst().getCompanyName());
        assertEquals(0, summaries.getFirst().getApplicants());
        assertEquals("Acme", summaries.get(1).getCompanyName());
        assertEquals(1, summaries.get(1).getApplicants());
        assertEquals(1, summaries.get(1).getOffers());

        var offers = applicationRepository.loadOfferAggregates(InterviewStatus.OFFERED);
        assertEquals(1, offers.getTotalOffers());
        assertEquals(new BigDecimal("12.00"), offers.getAveragePackageLpa().setScale(2));
        assertEquals(new BigDecimal("12.00"), offers.getHighestPackageLpa().setScale(2));

    }

    @Test
    void rejectsOnlyPendingApplicationsForLowerPackageDrives() {
        StudentEntity student = studentRepository.save(new StudentEntity(
                "CS2", "Ben Lee", new BigDecimal("8.50"), 0, PlacementStatus.UNPLACED));
        CompanyEntity lowPackage = companyRepository.save(new CompanyEntity(
                "Low", new BigDecimal("6.00"), 0, new BigDecimal("10.00")));
        CompanyEntity highPackage = companyRepository.save(new CompanyEntity(
                "High", new BigDecimal("6.00"), 0, new BigDecimal("20.00")));
        applicationRepository.save(new ApplicationEntity(student, lowPackage, InterviewStatus.SHORTLISTED));
        applicationRepository.save(new ApplicationEntity(student, highPackage, InterviewStatus.APPLIED));

        int rejected = applicationRepository.rejectPendingApplicationsForLowerPackages(
                "CS2", new BigDecimal("15.00"));

        assertEquals(1, rejected);
        assertEquals(InterviewStatus.REJECTED, applicationRepository
                .findByCompany_CompanyIdOrderByAppIdAsc(lowPackage.getCompanyId()).getFirst().getInterviewStatus());
        assertEquals(InterviewStatus.APPLIED, applicationRepository
                .findByCompany_CompanyIdOrderByAppIdAsc(highPackage.getCompanyId()).getFirst().getInterviewStatus());
    }
}
