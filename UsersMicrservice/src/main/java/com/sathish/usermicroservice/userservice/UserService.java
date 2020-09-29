package com.sathish.usermicroservice.userservice;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.sathish.usermicroservice.dto.UserDto;

public interface UserService extends UserDetailsService {
	
	
	UserDto createUser(UserDto userDto);

}
