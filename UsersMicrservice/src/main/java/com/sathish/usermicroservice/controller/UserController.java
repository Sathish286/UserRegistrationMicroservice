package com.sathish.usermicroservice.controller;

import javax.ws.rs.core.MediaType;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sathish.usermicroservice.dto.UserDto;
import com.sathish.usermicroservice.model.CreateUserRequestModel;
import com.sathish.usermicroservice.model.UserResponse;
import com.sathish.usermicroservice.userservice.UserService;

@RestController()
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	Environment environment;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/status/check")
	public String getStatus() {
		String portNumber = environment.getProperty("local.server.port");
		return "checking on port= "+portNumber;
	}
	
	@PostMapping(consumes= {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML},
			produces= {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequestModel userRequest) {
		
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto userDto=mapper.map(userRequest, UserDto.class);
		UserDto response = userService.createUser(userDto);
		UserResponse created = mapper.map(response, UserResponse.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

}
