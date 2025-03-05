package com.jobPortal.jobPortal.Services.Impl;

import com.jobPortal.jobPortal.Model.Admin;
import com.jobPortal.jobPortal.Model.Job;
import com.jobPortal.jobPortal.Model.User;
import com.jobPortal.jobPortal.Model.UserProfile;
import com.jobPortal.jobPortal.Repository.JobRepository;
import com.jobPortal.jobPortal.Repository.UserProfileRepository;
import com.jobPortal.jobPortal.Repository.UserRepository;
import com.jobPortal.jobPortal.Services.Interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    JobRepository jobRepository;
    @Autowired
    UserProfileRepository userProfileRepository;

    @Override
    public UserProfile getUserProfile(String email) {
        User u = userRepository.findById(email).orElse(null);
        return u.getUserProfile();
    }

    @Override
    public boolean isValid(User user) {
        return userRepository.findById(user.getEmail()).orElse(null) != null;
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }
    @Override
    public void apply(String email, long id) {
        Boolean exists = false;
        User user = userRepository.findById(email).orElse(null);
        Job job = jobRepository.findById(id).orElse(null);
        if(user != null && job != null){
            for (Job j: user.getApplications()) {
                if(j.getId() == id){
                    exists = true;
                    break;
                }
            }
        }
        if(!exists){
            user.getApplications().add(job);
        }
        if(user != null)
            userRepository.save(user);
    }

    @Override
    public void addProfile(String email, UserProfile userProfile) {
        userProfileRepository.save(userProfile);
        User user = userRepository.getById(email);
        if(user.getUserProfile() == null){
            user.setUserProfile(userProfile);
            userRepository.save(user);
        }
    }
}
