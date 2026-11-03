package com.university.placement.controller;

import com.university.placement.api.CompanyRequest;
import com.university.placement.entity.CompanyEntity;
import com.university.placement.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyEntity registerCompany(@Valid @RequestBody CompanyRequest request) {
        return companyService.registerCompany(request.getName(), request.getMinCgpa(),
                request.getMaxBacklogs(), request.getPackageLpa());
    }

    @GetMapping
    public List<CompanyEntity> listCompanies() {
        return companyService.listCompanies();
    }

    @GetMapping("/eligible")
    public List<CompanyEntity> findEligibleDrives(@RequestParam String rollNo) {
        return companyService.findEligibleDrives(rollNo);
    }

    @GetMapping("/{companyId}")
    public CompanyEntity getCompany(@PathVariable int companyId) {
        return companyService.getCompany(companyId);
    }
}
