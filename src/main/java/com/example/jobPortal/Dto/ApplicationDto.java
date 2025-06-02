package com.example.jobPortal.Dto;

import com.example.jobPortal.Entity.Job;
import com.example.jobPortal.Entity.User;
import jakarta.persistence.*;

import java.util.Date;

public class ApplicationDto {
    
    private Long id;
    private Long jobId;
    private Long userId;
    private String resumeUrl;
    private Date appliedAt;

    public ApplicationDto(Long id, Long jobId, Long userId, String resumeUrl, Date appliedAt) {
        this.id = id;
        this.jobId = jobId;
        this.userId = userId;
        this.resumeUrl = resumeUrl;
        this.appliedAt = appliedAt;
    }

    public ApplicationDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getResumeUrl() {
        return resumeUrl;
    }

    public void setResumeUrl(String resumeUrl) {
        this.resumeUrl = resumeUrl;
    }

    public Date getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(Date appliedAt) {
        this.appliedAt = appliedAt;
    }
    
}
