package com.example.jobsList.Model.Mapper;

import com.example.jobsList.Model.DTO.JobDTO;
import com.example.jobsList.Model.Entity.JopList;

public class JobMapper {

    public static JobDTO toDTO(JopList job) {
        JobDTO dto = new JobDTO();
        dto.setId(job.getId()); // JopList id and JobDTO id are both Strings
        dto.setCompanyId(job.getCompanyId());
        dto.setHrId(job.getHrId());
        dto.setTitle(job.getTitle());
        dto.setDescription(job.getDescription());
        dto.setLocation(job.getLocation());
        dto.setEmploymentType(job.getEmploymentType());
        dto.setSalaryMin(job.getSalaryMin());
        dto.setSalaryMax(job.getSalaryMax());
        dto.setSalaryCurrency(job.getSalaryCurrency());
        dto.setRequirements(job.getRequirements());
        dto.setPostedAt(job.getPostedAt());
        dto.setStatus(job.getStatus());
        return dto;
    }

    public static JopList toEntity(JobDTO dto) {
        JopList job = new JopList();
        // Ensure id is set properly (only set if the dto has a valid id)
        if (dto.getId() != null) {
            job.setId(dto.getId());
        }
        job.setCompanyId(dto.getCompanyId());
        job.setHrId(dto.getHrId());
        job.setTitle(dto.getTitle());
        job.setDescription(dto.getDescription());
        job.setLocation(dto.getLocation());
        job.setEmploymentType(dto.getEmploymentType());
        job.setSalaryMin(dto.getSalaryMin());
        job.setSalaryMax(dto.getSalaryMax());
        job.setSalaryCurrency(dto.getSalaryCurrency());
        job.setRequirements(dto.getRequirements());
        job.setPostedAt(dto.getPostedAt());
        job.setStatus(dto.getStatus());
        return job;
    }
}
