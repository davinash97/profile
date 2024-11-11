package com.jobsphere.profile.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "profile")
public class Profile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String first_name;
	private String last_name;
	private String user_name;
	private String account_type;
	private String phone;
	private String email;

	public Profile(){}

	public Profile(String first_name, String last_name,
				   String user_name, String account_type) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.user_name = user_name;
		this.account_type = account_type;
	}

	public UUID getId() {
		return id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "id: " + getId() +
				" first_name: "  + getFirst_name() +
				" last_name: " + getLast_name() +
				" user_name: " + getUser_name() +
				" account_type: " + getAccount_type() +
				" phone: " + getPhone() +
				" email: " + getEmail();
	}
}
