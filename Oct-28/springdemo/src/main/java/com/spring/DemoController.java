package com.spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class DemoController {
    @GetMapping("/me")
    public String demoApi() {
        return "Hi Pranay, You are testing the test api.";
    }

    @GetMapping("/even-or-odd")
    public String testNumber(@RequestParam int num) {
        if (num % 2 == 0) {
            return num + " even";
        }
        return num + " odd";
    }

    @GetMapping("/can-vote")
    public String canVote(@RequestParam int age) {
        if (age < 18) {
            return "you are minor.";
        } else {
            return "you are eligible to vote.";
        }
    }

    @GetMapping("/check-grade")
    public String checkGrade(
            @RequestParam String name,
            @RequestParam int marks
    ) {
        if (marks >= 90) {
            return name + " got Grade A";
        } else if (marks >= 75) {
            return name + " got Grade B";
        } else if (marks >= 60) {
            return name + " got Grade C";
        } else if (marks >= 35) {
            return name + " got Grade D";
        } else {
            return name + " Fail";
        }
    }
}