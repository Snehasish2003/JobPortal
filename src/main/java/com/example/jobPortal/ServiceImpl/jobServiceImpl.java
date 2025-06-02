package com.example.jobPortal.ServiceImpl;

import com.example.jobPortal.Dto.JobDto;
import com.example.jobPortal.Dto.Response;
import com.example.jobPortal.Entity.Job;
import com.example.jobPortal.Entity.User;
import com.example.jobPortal.Enum.Role;
import com.example.jobPortal.Exceptions.JobNotFound;
import com.example.jobPortal.Exceptions.UserNotFound;
import com.example.jobPortal.Repository.JobRepository;
import com.example.jobPortal.Repository.UserRepository;
import com.example.jobPortal.Service.JobService;
import com.example.jobPortal.Specifications.JobSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@Service
public class jobServiceImpl implements JobService {
    @Autowired
    JobSpecification jobSpecification;
    @Autowired
    JobRepository jobRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public Response<?> viewAllJobs(

            int pageNo, int pageSize, String sortBy, Boolean ascending,
            String jobTitle, String companyName, String[] location,
            Double minSalary, Double maxSalary) {

        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Specification<Job> spec = Specification.where(null);

        if (!jobTitle.isEmpty()) {
            spec = spec.and(jobSpecification.hasTitle(jobTitle));
        }

        if (!companyName.isEmpty()) {
            spec = spec.and(jobSpecification.containsComapnyName(companyName));
        }

        if (location != null && location.length > 0) {
            Specification<Job> locationSpec = jobSpecification.hasLocation(location[0]);

            for (int i = 1; i < location.length; i++) {
                locationSpec = locationSpec.or(jobSpecification.hasLocation(location[i]));
            }

            spec = spec.and(locationSpec);
        }


        if (minSalary > 0.0) {
            spec = spec.and(jobSpecification.hasMinSalary(minSalary));
        }

        if (maxSalary > 0.0 && maxSalary < Double.MAX_VALUE) {
            spec = spec.and(jobSpecification.hasMaxSalary(maxSalary));
        }

        Page<Job> jobPage = jobRepository.findAll(spec, pageable);

        return new Response<>(HttpStatus.OK.value(), "Fetched successfully", jobPage);
    }

    @Override
    public Response<?> addJob(JobDto jobDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream().anyMatch(
                auth->
                auth.getAuthority().equals(Role.COMPANY_ADMIN.toString()));
        Optional<User> optionalUser = Optional.of(userRepository.findByEmail(authentication.getName()).orElseThrow());
        if(isAdmin){
            Job job = new Job();
            job.setCompany(jobDto.getCompany());
            job.setCreatedAt(new Date());
            job.setDescription(jobDto.getDescription());
            job.setLocation(job.getLocation());
            job.setSalary(jobDto.getSalary());
            job.setTitle(job.getTitle());
            job.setCreatedBy(optionalUser.get());
            Job savedJob = jobRepository.save(job);
        return new Response<>(HttpStatus.OK.value(), "Job added successfully", savedJob);
        }
        return new Response<>(HttpStatus.UNAUTHORIZED.value(), "You are not authorized to add jobs", null);



    }

    @Override
    public Response<?> deleteJob(Long id) {
        if(id!=null){
            Optional<Job> optional = jobRepository.findById(id);
            if(optional.isEmpty()){
                throw new JobNotFound("Job Not  found");
            }
            jobRepository.delete(optional.get());
            return new Response<>(HttpStatus.OK.value(), "Deleted SuccessFully " , null);
        }
        throw new NullPointerException("invalid Id");
    }


}
