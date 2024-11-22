package com.jobPortal.jobPortal.Services.Impl;

import com.jobPortal.jobPortal.Model.Admin;
import com.jobPortal.jobPortal.Repository.AdminRepository;
import com.jobPortal.jobPortal.Services.Interface.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository;

    @Override
    public boolean isValid(Admin admin) {
        return adminRepository.findById(admin.getEmail()).orElse(null) != null;
    }

    @Override
    public void addUser(Admin admin) {
        adminRepository.save(admin);
    }
}
