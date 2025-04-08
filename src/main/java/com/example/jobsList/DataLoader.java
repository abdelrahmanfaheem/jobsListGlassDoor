package com.example.jobsList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.jobsList.Model.Entity.JopList;
import com.example.jobsList.Reposatory.JobsRepository;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    private final JobsRepository jobRepository;

    public DataLoader(JobsRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Check if there are already jobs in the DB to avoid duplication
        if (jobRepository.count() == 0) {
            // Create sample jobs with String IDs
            JopList job1 = new JopList();
            job1.setCompanyId("660f1e7dc1a0fd001cddf8e5");
            job1.setHrId("660f1eabc1a0fd001cddf8e7");
            job1.setTitle("Backend Developer (Spring Boot)");
            job1.setDescription("We are looking for a backend developer experienced in Spring Boot and MongoDB.");
            job1.setLocation("Bangalore, India");
            job1.setEmploymentType("Full-time");
            job1.setSalaryMin(70000);
            job1.setSalaryMax(120000);
            job1.setSalaryCurrency("INR");
            job1.setRequirements(Arrays.asList("Java", "Spring Boot", "MongoDB", "REST APIs"));
            job1.setPostedAt(LocalDateTime.now());
            job1.setStatus("Active");

            JopList job2 = new JopList();
            job2.setCompanyId("660f1e7dc1a0fd001cddf8e6");
            job2.setHrId("660f1eabc1a0fd001cddf8e8");
            job2.setTitle("Frontend Developer (React)");
            job2.setDescription("We are looking for a frontend developer experienced in React and Redux.");
            job2.setLocation("Mumbai, India");
            job2.setEmploymentType("Part-time");
            job2.setSalaryMin(40000);
            job2.setSalaryMax(70000);
            job2.setSalaryCurrency("INR");
            job2.setRequirements(Arrays.asList("React", "Redux", "JavaScript"));
            job2.setPostedAt(LocalDateTime.now());
            job2.setStatus("Active");

            // Save jobs to MongoDB
            jobRepository.save(job1);
            jobRepository.save(job2);

            System.out.println("Test data loaded into the database.");
        } else {
            System.out.println("Database already contains data.");
        }
    }
}
