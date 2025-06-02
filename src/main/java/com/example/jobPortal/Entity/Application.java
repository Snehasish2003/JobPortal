package com.example.jobPortal.Entity;

import com.example.jobPortal.Dto.ApplicationDto;
import jakarta.persistence.*;

import java.util.Date;

//Application {
//    id: UUID (PK)
//    job_id: UUID (FK to Job)
//    candidate_id: UUID (FK to User)
//    resume_url: VARCHAR
//    applied_at: TIMESTAMP
//}
@Entity
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private String resumeUrl;
    private Date appliedAt;

    public Application(Long id, Job job, User user, String resumeUrl, Date appliedAt) {
        this.id = id;
        this.job = job;
        this.user = user;
        this.resumeUrl = resumeUrl;
        this.appliedAt = appliedAt;
    }

    public Application() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
    public ApplicationDto convertToDto(){
        return new ApplicationDto(
                this.id!=null?this.id:null
                ,this.job!=null?this.job.getId():null
                ,this.user!=null?this.user.getId():null
                ,this.resumeUrl!=null?this.resumeUrl:null
                ,this.appliedAt!=null?this.appliedAt:null
        );
    }
}
