package com.example.jobsList.Service;

import java.util.List;
import java.util.Optional;

 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 import com.example.jobsList.Model.JopList;
import com.example.jobsList.Reposatory.JobsRepository;


@Service
public class JobService {

    @Autowired
    private JobsRepository jobRepository;

    public List<JopList> getAllJobs() {
        return jobRepository.findAll();
    }

    public Optional<JopList> getJobById(String id) {
        return jobRepository.findById(id);
    }

    public JopList createJob(JopList job) {
        return jobRepository.save(job);
    }

    public JopList updateJob(String id, JopList job) {
        job.setId(id);
        return jobRepository.save(job);
    }

    public void deleteJob(String id) {
        jobRepository.deleteById(id);
    }


    public List<JopList> getJopByStatus(String status){
        return jobRepository.findByStatus(status);
    }
}
