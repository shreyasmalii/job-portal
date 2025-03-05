package com.jobPortal.jobPortal.Services.Interface;

import com.jobPortal.jobPortal.Model.Job;

import java.util.List;

public interface JobService {
    public List<Job> getJobs(String email);
    public List<Job> getAllJobs();
    public void addJob(Job job, String email);
    public Job getJobById(long id);
}
