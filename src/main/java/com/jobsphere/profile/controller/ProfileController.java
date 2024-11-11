package com.jobsphere.profile.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobsphere.profile.model.Profile;
import com.jobsphere.profile.model.Response;
import com.jobsphere.profile.service.ProfileService;

@RestController
@RequestMapping("/profile")
public class ProfileController {

	private static final Logger log = LoggerFactory.getLogger(ProfileController.class);

	@Autowired
	private ProfileService service;

	@PostMapping
	public ResponseEntity<Response<?>> create(@RequestBody (required=false) Profile body,
											  @RequestParam (required=false) String first_name,
											  @RequestParam (required=false) String last_name,
											  @RequestParam (required=false) String user_name,
											  @RequestParam (required=false) String email,
											  @RequestParam (required=false) String phone,
											  @RequestParam (required=false) String account_type) {
		try {
			if (body != null) {
				if (first_name == null) {
					first_name = body.getFirst_name();
				}

				if (last_name == null) {
					last_name = body.getLast_name();
				}

				if (user_name == null) {
					user_name = body.getUser_name();
				}

				if (account_type == null) {
					account_type = body.getAccount_type();
				}

				if (email == null) {
					email = body.getEmail();
				}

				if(phone == null) {
					phone = body.getPhone();
				}

			}

			Profile profile = service.create(first_name, last_name, user_name, account_type, email, phone);

			if (profile == null)
				return ResponseEntity.internalServerError().body(new Response<>("bad", HttpStatus.NOT_FOUND.value(), profile));
			log.debug("Created new job: {}", profile);
			return ResponseEntity.ok(new Response<>("success", HttpStatus.CREATED.value(), service.read(profile.getId())));
		} catch (Exception e) {
			Error.server(e.getMessage());
			return ResponseEntity.internalServerError().body(new Response<>("bad", HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response<?>> read(@PathVariable UUID id) {
		try {
			log.debug("Fetched {}", id);
			return ResponseEntity.ok(new Response<>("success", HttpStatus.OK.value(), service.read(id)));
		} catch (Exception e) {
			Error.server(e.getMessage());
			return ResponseEntity.internalServerError().body(new Response<>("bad", HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Response<?>> update(@RequestBody (required=false) Profile body,
											  @PathVariable (required = false) UUID id,
											  @RequestParam (required = false) String first_name,
											  @RequestParam (required = false) String last_name,
											  @RequestParam (required = false) String user_name,
											  @RequestParam (required = false) String account_type,
											  @RequestParam (required = false) String phone,
											  @RequestParam (required = false) String email) {

		try {
			if (body != null) {
				if (id == null) {
					id = body.getId();
				}

				if (first_name == null) {
					first_name = body.getFirst_name();
				}

				if (last_name == null) {
					last_name = body.getLast_name();
				}

				if (user_name == null) {
					user_name = body.getUser_name();
				}

				if (account_type == null) {
					account_type = body.getAccount_type();
				}

				if (email == null) {
					email = body.getEmail();
				}

				if(phone == null) {
					phone = body.getPhone();
				}
			}

			Profile profile = service.update(id, first_name, last_name, user_name, account_type, phone, email);

			if(profile == null)
				return ResponseEntity.internalServerError().body(new Response<>("bad", HttpStatus.INTERNAL_SERVER_ERROR.value(), profile));

			log.debug("Updated job: {}", profile);
			return ResponseEntity.ok().body(new Response<>("success", HttpStatus.OK.value(), service.read(profile.getId())));
		} catch (Exception e) {
			Error.server(e.getMessage());
			return ResponseEntity.internalServerError().body(new Response<>("bad", HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response<?>> delete(@PathVariable UUID id) {
		try {
			log.debug("Deleted Job: {}", id);
			return ResponseEntity.ok(new com.jobsphere.profile.model.Response<>("success", HttpStatus.OK.value(), service.delete(id)));
		} catch (Exception e) {
			Error.server(e.getMessage());
			return ResponseEntity.internalServerError().body(new com.jobsphere.profile.model.Response<>("bad", HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
		}
	}

	static class Error {
		public static void server(String message) {
			log.error("Error occurred at {}", message);
		}
	}
}
