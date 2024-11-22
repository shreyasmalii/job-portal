package com.jobPortal.jobPortal.Services.Interface;

import com.jobPortal.jobPortal.Model.Admin;

public interface AdminService {
    public boolean isValid(Admin admin);
    public void addUser(Admin admin);
}
