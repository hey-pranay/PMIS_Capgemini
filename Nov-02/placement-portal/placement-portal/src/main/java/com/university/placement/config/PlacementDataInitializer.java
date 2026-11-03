package com.university.placement.config;

import com.university.placement.entity.ApplicationEntity;
import com.university.placement.entity.CompanyEntity;
import com.university.placement.entity.InterviewStatus;
import com.university.placement.entity.PlacementStatus;
import com.university.placement.entity.StudentEntity;
import com.university.placement.repository.ApplicationRepository;
import com.university.placement.repository.CompanyRepository;
import com.university.placement.repository.StudentRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PlacementDataInitializer implements ApplicationRunner {

    private static final List<StudentSeed> STUDENTS = List.of(
            new StudentSeed("CS21001", "Aarav Sharma", "9.25", 0, PlacementStatus.PLACED),
            new StudentSeed("CS21002", "Priya Nair", "8.80", 0, PlacementStatus.PLACED),
            new StudentSeed("CS21003", "Rohan Mehta", "8.10", 0, PlacementStatus.UNPLACED),
            new StudentSeed("CS21004", "Sneha Iyer", "7.60", 0, PlacementStatus.UNPLACED),
            new StudentSeed("CS21005", "Karan Patel", "7.20", 1, PlacementStatus.UNPLACED),
            new StudentSeed("CS21006", "Ananya Reddy", "6.90", 0, PlacementStatus.PLACED),
            new StudentSeed("CS21007", "Vikram Singh", "6.40", 2, PlacementStatus.UNPLACED),
            new StudentSeed("CS21008", "Meera Joshi", "5.80", 0, PlacementStatus.UNPLACED),
            new StudentSeed("CS21009", "Arjun Kumar", "8.40", 1, PlacementStatus.UNPLACED),
            new StudentSeed("CS21010", "Divya Menon", "7.90", 0, PlacementStatus.OPTED_OUT),
            new StudentSeed("CS21011", "Rahul Verma", "5.20", 4, PlacementStatus.UNPLACED),
            new StudentSeed("CS21012", "Neha Gupta", "9.00", 0, PlacementStatus.UNPLACED),
            new StudentSeed("CS21013", "Siddharth Rao", "7.00", 0, PlacementStatus.UNPLACED),
            new StudentSeed("CS21014", "Pooja Desai", "6.20", 1, PlacementStatus.UNPLACED)
    );

    private static final List<CompanySeed> COMPANIES = List.of(
            new CompanySeed("Infosys", "6.00", 1, "6.50"),
            new CompanySeed("TCS Digital", "6.50", 0, "7.00"),
            new CompanySeed("Wipro Elite", "6.00", 2, "3.50"),
            new CompanySeed("Accenture", "6.50", 1, "4.50"),
            new CompanySeed("Deloitte", "7.50", 0, "12.00"),
            new CompanySeed("Amazon", "8.00", 0, "28.00"),
            new CompanySeed("Microsoft", "8.50", 0, "42.00"),
            new CompanySeed("Zoho", "7.00", 0, "9.00"),
            new CompanySeed("Capgemini", "5.50", 3, "3.80")
    );

    private static final List<ApplicationSeed> APPLICATIONS = List.of(
            new ApplicationSeed("CS21001", "Microsoft", InterviewStatus.OFFERED),
            new ApplicationSeed("CS21001", "Amazon", InterviewStatus.REJECTED),
            new ApplicationSeed("CS21002", "Amazon", InterviewStatus.OFFERED),
            new ApplicationSeed("CS21002", "Infosys", InterviewStatus.REJECTED),
            new ApplicationSeed("CS21003", "Amazon", InterviewStatus.INTERVIEWING),
            new ApplicationSeed("CS21003", "Deloitte", InterviewStatus.SHORTLISTED),
            new ApplicationSeed("CS21003", "Infosys", InterviewStatus.APPLIED),
            new ApplicationSeed("CS21004", "Deloitte", InterviewStatus.INTERVIEWING),
            new ApplicationSeed("CS21004", "Zoho", InterviewStatus.SHORTLISTED),
            new ApplicationSeed("CS21004", "TCS Digital", InterviewStatus.APPLIED),
            new ApplicationSeed("CS21005", "Infosys", InterviewStatus.SHORTLISTED),
            new ApplicationSeed("CS21005", "Accenture", InterviewStatus.APPLIED),
            new ApplicationSeed("CS21006", "TCS Digital", InterviewStatus.OFFERED),
            new ApplicationSeed("CS21006", "Wipro Elite", InterviewStatus.REJECTED),
            new ApplicationSeed("CS21007", "Wipro Elite", InterviewStatus.APPLIED),
            new ApplicationSeed("CS21007", "Capgemini", InterviewStatus.REJECTED),
            new ApplicationSeed("CS21008", "Capgemini", InterviewStatus.APPLIED),
            new ApplicationSeed("CS21012", "Amazon", InterviewStatus.SHORTLISTED),
            new ApplicationSeed("CS21012", "Microsoft", InterviewStatus.APPLIED),
            new ApplicationSeed("CS21012", "Zoho", InterviewStatus.REJECTED),
            new ApplicationSeed("CS21013", "Accenture", InterviewStatus.APPLIED),
            new ApplicationSeed("CS21013", "TCS Digital", InterviewStatus.INTERVIEWING),
            new ApplicationSeed("CS21014", "Wipro Elite", InterviewStatus.SHORTLISTED),
            new ApplicationSeed("CS21014", "Infosys", InterviewStatus.APPLIED)
    );

    private final StudentRepository studentRepository;
    private final CompanyRepository companyRepository;
    private final ApplicationRepository applicationRepository;

    public PlacementDataInitializer(StudentRepository studentRepository,
                                    CompanyRepository companyRepository,
                                    ApplicationRepository applicationRepository) {
        this.studentRepository = studentRepository;
        this.companyRepository = companyRepository;
        this.applicationRepository = applicationRepository;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        if (studentRepository.count() != 0) {
            return;
        }

        List<StudentEntity> students = studentRepository.saveAll(STUDENTS.stream()
                .map(seed -> new StudentEntity(seed.rollNo, seed.name, decimal(seed.cgpa),
                        seed.activeBacklogs, seed.status))
                .toList());
        List<CompanyEntity> companies = companyRepository.saveAll(COMPANIES.stream()
                .map(seed -> new CompanyEntity(seed.name, decimal(seed.minCgpa),
                        seed.maxBacklogs, decimal(seed.packageLpa)))
                .toList());

        Map<String, StudentEntity> studentsByRollNo = new HashMap<>();
        students.forEach(student -> studentsByRollNo.put(student.getRollNo(), student));
        Map<String, CompanyEntity> companiesByName = new HashMap<>();
        companies.forEach(company -> companiesByName.put(company.getName(), company));

        applicationRepository.saveAll(APPLICATIONS.stream()
                .map(seed -> new ApplicationEntity(
                        studentsByRollNo.get(seed.rollNo),
                        companiesByName.get(seed.companyName),
                        seed.status))
                .toList());
    }

    private static BigDecimal decimal(String value) {
        return new BigDecimal(value);
    }

    private static final class StudentSeed {
        private final String rollNo;
        private final String name;
        private final String cgpa;
        private final int activeBacklogs;
        private final PlacementStatus status;

        private StudentSeed(String rollNo, String name, String cgpa,
                            int activeBacklogs, PlacementStatus status) {
            this.rollNo = rollNo;
            this.name = name;
            this.cgpa = cgpa;
            this.activeBacklogs = activeBacklogs;
            this.status = status;
        }
    }

    private static final class CompanySeed {
        private final String name;
        private final String minCgpa;
        private final int maxBacklogs;
        private final String packageLpa;

        private CompanySeed(String name, String minCgpa, int maxBacklogs, String packageLpa) {
            this.name = name;
            this.minCgpa = minCgpa;
            this.maxBacklogs = maxBacklogs;
            this.packageLpa = packageLpa;
        }
    }

    private static final class ApplicationSeed {
        private final String rollNo;
        private final String companyName;
        private final InterviewStatus status;

        private ApplicationSeed(String rollNo, String companyName, InterviewStatus status) {
            this.rollNo = rollNo;
            this.companyName = companyName;
            this.status = status;
        }
    }
}
