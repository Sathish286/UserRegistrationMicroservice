package com.sathish.usermicroservice.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateUserRequestModel {
		
	@NotNull(message="First Name cannot be null")
	@Size(min=3,message="firstName cannot be less than 3 characters")
	private String firstName;
	@NotNull(message="Last Name cannot be null")
	@Size(min=3,message="LastName cannot be less than 3 characters")
	private String lastName;
	@Email
	@NotNull(message="email cannot be null")
	private String email;
	@NotNull
	@Size(min=3 ,max=16,message="Password cannot be more than 16 characters or less than 3 characters")
	private String password;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
