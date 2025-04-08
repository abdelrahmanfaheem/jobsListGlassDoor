
package com.example.jobsList.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/allJobs")
    public List<JobDTO> getAllJobs() {
        return service.getAllJobs()
                .stream()
                .map(JobMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable String id) {
        return service.getJobById(new String(id))
                .map(JobMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/addJob")
    public ResponseEntity<JobDTO> createJob(@RequestBody JobDTO jobDTO) {
        JopList job = JobMapper.toEntity(jobDTO);
        job.setPostedAt(LocalDateTime.now());
        return ResponseEntity.ok(JobMapper.toDTO(service.createJob(job)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobDTO> updateJob(@PathVariable String id, @RequestBody JobDTO jobDTO) {
        JopList updated = JobMapper.toEntity(jobDTO);
        JopList job = service.updateJob(new String(id), updated);
        return ResponseEntity.ok(JobMapper.toDTO(job));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable String id) {
        service.deleteJob(new String(id));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/Status/{status}")
    public List<JobDTO> getJobStatust(@PathVariable("status") String status) {
        List<JopList> jops = service.getJopByStatus(status);
        return jops.stream().map(JobMapper::toDTO).collect(Collectors.toList());

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
