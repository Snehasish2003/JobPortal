package com.example.jobPortal.Entity;

import com.example.jobPortal.Dto.JobDto;
import jakarta.persistence.*;

import java.util.Date;
//Job {
//    id: UUID (PK)
//    title: VARCHAR
//    description: TEXT
//    location: VARCHAR
//    company: VARCHAR
//    salary: DECIMAL
//    created_by: UUID (FK to User)
//    created_at: TIMESTAMP
//}


@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String location;
    private String company;
    private Double salary;
    private Date createdAt;
    private Date updatedAt;
    @ManyToOne
    @JoinColumn(name = "created_By", nullable = false)
    private User createdBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Job(Long id, String title, String description, String location, String company, Double salary, Date createdAt, Date updatedAt, User createdBy) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.company = company;
        this.salary = salary;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdBy = createdBy;
    }

    public Job() {
    }
    public JobDto convertToDto(){
        return new JobDto(this.id!=null?this.id:null
        ,this.title!=null?this.title:null
        ,this.description!=null?this.description:null
        ,this.location!=null?this.location:null
        ,this.company!=null?this.company:null
        ,this.salary!=null?this.salary:null
        ,this.createdAt!=null?this.createdAt:null
        ,this.updatedAt!=null?this.updatedAt:null
        ,this.createdBy!=null?this.createdBy.getId():null);
    }
}
