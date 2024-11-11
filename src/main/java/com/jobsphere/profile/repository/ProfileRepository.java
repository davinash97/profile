package com.jobsphere.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobsphere.profile.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, java.util.UUID> {
}
