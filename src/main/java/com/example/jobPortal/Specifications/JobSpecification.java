package com.example.jobPortal.Specifications;

import com.example.jobPortal.Entity.Job;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class JobSpecification {
    public Specification<Job> hasTitle(String title){
        return (root,query,criteriaBuilder) ->
                criteriaBuilder.equal(criteriaBuilder.lower(root.get("title")),title.toLowerCase());
    }
    public Specification<Job> hasLocation(String location){
        return (root,query,criteriaBuilder) ->
                criteriaBuilder.equal(criteriaBuilder.lower(root.get("location")),location.toLowerCase());
    }
    public Specification<Job> containsComapnyName(String company){
        return (root,query,criteriaBuilder) ->
                criteriaBuilder.equal(criteriaBuilder.lower(root.get("company")),company.toLowerCase());
    }

    public Specification<Job> hasMinSalary(Double minSalary) {
        return (root,query,criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("salary"),minSalary);
    }

    public Specification<Job> hasMaxSalary(Double maxSalary) {
        return (root,query,criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("salary"),maxSalary);
    }
//    public static Specification<Job> isWithinRange(int min, int max) {
//        return (root, query, builder) ->
//                builder.and(
//                        builder.greaterThan(root.get("salary"), min),
//                        builder.lessThan(root.get("salary"), max)
//                );
//    }

}
