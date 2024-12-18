package com.villa_agency.user;

import java.time.LocalDate;

public class UserDto {

	private String username;
	private String email;
	private LocalDate createdDate;

	public UserDto() {
		super();
	}

	public UserDto(String username, String email, LocalDate createdDate) {
		super();
		this.username = username;
		this.email = email;
		this.createdDate = createdDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

}
