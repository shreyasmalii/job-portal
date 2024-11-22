package com.jobPortal.jobPortal.Repository;

import com.jobPortal.jobPortal.Model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {
}
