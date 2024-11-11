package com.jobsphere.profile.service;

import java.util.UUID;

import com.jobsphere.profile.model.Profile;

public interface ProfileService {
	Profile create(String first_name, String last_name, String user_name, String account_type, String phone, String email);
	Profile read(UUID id);
	Profile update(UUID id, String first_name, String last_name, String user_name, String account_type, String phone, String email);
	Boolean delete(UUID id);
}