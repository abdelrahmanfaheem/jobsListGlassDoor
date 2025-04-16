
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

    

    // Get jobs by status
    @GetMapping("/status/{status}")
    public List<JobDTO> getJobsByStatus(@PathVariable("status") String status) {
        List<JopList> jobs = service.getJopByStatus(status);
        return jobs.stream()
                .map(JobMapper::toDTO)
                .collect(Collectors.toList());
    }
   ;
     // List<JopList> findBySalaryCurrency(String salaryCurrency);
    
    @GetMapping("/titles/{titles}")
    public List<JopList>finbyTitles (@PathVariable("titles") String titles){
         return service.findByTitle(titles);

    }

    @GetMapping("/locations/{location}")
    public List<JopList>findByLocation ( @PathVariable("location") String location){

        return service.findByLocation(location);

    }

    @GetMapping("/types/{types}")
    public List<JopList> findByEmploymentType(@PathVariable ("types") String types){
         return service.findByEmploymentType(types);
    }

    @GetMapping("/currency/{currency}")
    public List<JopList> findBySalaryCurrency(@PathVariable("currency") String currency){
   return service.findBySalaryCurrency(currency);
    }

    @GetMapping("/allLocations")
    public List<String> findDistinctLocations(){
        return service.findDistinctLocations();
    }

    @GetMapping("/allTitles")
    public List<String> findDistinctTitles(){
        return service.findDistinctTitles();
    }

    @GetMapping("/allEmplymentTypes")
    public List<String>findDistinctEmploymentTypes(){
        return service.findDistinctEmploymentTypes();
    }

    @GetMapping("/allCurrencies")
    public List<String>findDistinctSalaryCurrencies(){
        return service.findDistinctSalaryCurrencies();
    }

    @GetMapping("/allStatus")
    public List<String> findDistinctStatuses(){
        return service.findDistinctStatuses();
    }
    
    @GetMapping("/filter")
    public List<JopList> filterJobs(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String currency,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String title
    ) {
        return service.findbyfilters(location, currency, status, type, title);
    }






}
