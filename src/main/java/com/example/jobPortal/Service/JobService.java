package com.example.jobPortal.Service;

import com.example.jobPortal.Dto.JobDto;
import com.example.jobPortal.Dto.Response;

public interface JobService {
    Response<?> viewAllJobs(int pageNo, int pageSize, String sortBy, Boolean ascending, String jobTitle, String companyName, String[] location, Double minSalary, Double maxSalary);

    Response<?> addJob(JobDto jobDto);

    Response<?> deleteJob(Long id);
}
