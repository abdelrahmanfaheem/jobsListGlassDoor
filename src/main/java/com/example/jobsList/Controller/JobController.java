
package com.example.jobsList.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.jobsList.Model.DTO.JobDTO;
import com.example.jobsList.Model.Entity.JopList;
import com.example.jobsList.Model.Mapper.JobMapper;
import com.example.jobsList.Service.JobService;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService service;

    // Get all jobs
    @GetMapping("/allJobs")
    public List<JobDTO> getAllJobs() {
        return service.getAllJobs()
                .stream()
                .map(JobMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Get a job by ID
    @GetMapping("/{id}")
    public JobDTO getJobById(@PathVariable String id) {
        return service.getJobById(new String(id))
                .map(JobMapper::toDTO)
                .orElse(null); // return null if job not found
    }

    // Create a new job
    @PostMapping("/addJob")
    public JobDTO createJob(@RequestBody JobDTO jobDTO) {
        JopList job = JobMapper.toEntity(jobDTO);
        job.setPostedAt(LocalDateTime.now());
        return JobMapper.toDTO(service.createJob(job));
    }

    // Update an existing job
    @PutMapping("/{id}")
    public JobDTO updateJob(@PathVariable String id, @RequestBody JobDTO jobDTO) {
        JopList updated = JobMapper.toEntity(jobDTO);
        JopList job = service.updateJob(new String(id), updated);
        return JobMapper.toDTO(job);
    }

    // Delete a job by ID
    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable String id) {
        service.deleteJob(new String(id));
    }

    // Get jobs by status
    @GetMapping("/status/{status}")
    public List<JobDTO> getJobsByStatus(@PathVariable("status") String status) {
        List<JopList> jobs = service.getJopByStatus(status);
        return jobs.stream()
                .map(JobMapper::toDTO)
                .collect(Collectors.toList());
    }
}