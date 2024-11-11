package com.jobsphere.profile.service.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobsphere.profile.model.Profile;
import com.jobsphere.profile.repository.ProfileRepository;
import com.jobsphere.profile.service.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService {

	private static final Logger log = LoggerFactory.getLogger(ProfileServiceImpl.class);

	@Autowired
	private ProfileRepository repository;

	@Override
	public Profile create(String first_name, String last_name, String user_name, String account_type, String email, String phone){
		try {

			Profile profile = new Profile(first_name, last_name, user_name, account_type);

			if(email != null) profile.setEmail(email);
			if(phone != null) profile.setPhone(phone);

			System.out.print(profile.toString());

			repository.save(profile);

			if(repository.existsById(profile.getId())) {
				log.debug(profile.toString());
				return read(profile.getId());
			}
			return null;
		} catch (Exception e) {
			Error.server(e.getMessage());
			return null;
		}
	}

	@Override
	public Profile read(UUID id) {
		try {
			Profile profile = repository.findById(id).orElse(null);
			if(profile!=null) log.debug(profile.toString());
			return profile;
		} catch(Exception e) {
			Error.server(e.getMessage());
			return null;
		}
	}

	@Override
	public Profile update(UUID id, String first_name, String last_name, String user_name, String account_type, String phone, String email) {

		try {
			Profile profile = read(id);

			if (profile != null) {
				if (!first_name.isBlank())
					profile.setFirst_name(first_name);

				if (!last_name.isBlank())
					profile.setLast_name(last_name);

				if (!user_name.isBlank())
					profile.setUser_name(user_name);

				if (!account_type.isBlank())
					profile.setAccount_type(account_type);

				if (email != null)
					profile.setEmail(email);

				if(phone != null)
					profile.setPhone(phone);

				repository.save(profile);
				log.debug(profile.toString());
				return read(profile.getId());
			}
			return null;
		} catch (Exception e) {
			Error.server(e.getMessage());
			return null;
		}
	}

	@Override
	public Boolean delete(UUID id) {
		try {
			repository.deleteById(id);
			log.debug("Deleted: {}", id);
			return !repository.existsById(id);
		} catch (Exception e) {
			Error.server(e.getMessage());
			return false;
		}
	}

	// @Override
	// public Boolean queryExists(String query) {
	// 	if(query == null || query.isBlank()) {
	// 		throw new IllegalArgumentException("Query must not be null or empty");
	// 	}
	// 	return repository.findByUsernameOrPhoneOrEmail(query);
	// }

	static class Error {
		static void server(String message) {
			log.error(message);
		}
	}
}

