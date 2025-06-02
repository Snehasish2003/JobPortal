package com.example.jobPortal.Controller;

import com.example.jobPortal.Dto.JobDto;
import com.example.jobPortal.Dto.Response;
import com.example.jobPortal.Service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/job")
public class JobController {
    @Autowired
    JobService jobService;
    @GetMapping("/view")
    public ResponseEntity<Object> viewAlljobs(@RequestParam(defaultValue = "0") int pageNo,
                                              @RequestParam(defaultValue = "10") int pageSize,
                                              @RequestParam(defaultValue = "salary") String sortBy,
                                              @RequestParam(defaultValue = "true") Boolean ascending,
                                              @RequestParam(defaultValue = "") String jobTitle,
                                              @RequestParam(defaultValue = "") String companyName,
                                              @RequestParam(defaultValue = "") String[] location,
                                              @RequestParam(defaultValue = "0.0") Double minSalary,
                                              @RequestParam(defaultValue = "1000000.0") Double maxSalary
    ){
            Response<?> response = jobService.viewAllJobs(pageNo,pageSize,sortBy,ascending,jobTitle,companyName,location,minSalary,maxSalary);
            return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addJob(@RequestBody JobDto jobDto){
        Response<?> response = jobService.addJob(jobDto);
        return new ResponseEntity<>(response,HttpStatusCode.valueOf(response.getStatusCode()));

    }
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteJob(@RequestParam Long id){
        Response<?> response = jobService.deleteJob(id);
        return new ResponseEntity<>(response,HttpStatusCode.valueOf(response.getStatusCode()));

    }
}
