package com.jobPortal.jobPortal.Services.Impl;

import com.jobPortal.jobPortal.Model.Admin;
import com.jobPortal.jobPortal.Model.Job;
import com.jobPortal.jobPortal.Repository.AdminRepository;
import com.jobPortal.jobPortal.Repository.JobRepository;
import com.jobPortal.jobPortal.Services.Interface.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    JobRepository jobRepository;
    @Autowired
    AdminRepository adminRepository;

    @Override
    public List<Job> getJobs(String email) {
        return adminRepository.findById(email).get().getJobs();
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public void addJob(Job job, String email) {
        boolean found = false;
        Admin admin = adminRepository.getById(email);
        for(Job j : admin.getJobs()) {
            if (j.getId() == job.getId()) {
                found = true;
                break;
            }
        }
        Job savedJob = jobRepository.save(job);
        if(!found){
            admin.getJobs().add(savedJob);
            adminRepository.save(admin);
        }
    }

    @Override
    public Job getJobById( long id) {
        return jobRepository.findById(id).orElse(null);
    }

}
