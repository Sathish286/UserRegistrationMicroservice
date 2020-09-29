package com.sathish.usermicroservice.userservice;

import java.util.ArrayList;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.sathish.usermicroservice.Entity.UserEntity;
import com.sathish.usermicroservice.Entity.UserRepository;
import com.sathish.usermicroservice.dto.UserDto;

@Service
//@Component
public class UserServiceImpl implements UserService {
	
	
	@Autowired
	UserRepository repository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		userDto.setUserId(UUID.randomUUID().toString());
		String password = userDto.getPassword();
	
		userDto.setEncryptedPassword(bCryptPasswordEncoder.encode(password));
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity entity = mapper.map(userDto, UserEntity.class);
		UserEntity  userEntity = repository.save(entity);
		UserDto  Dto=mapper.map(userEntity, UserDto.class);
		return Dto;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = repository.findByEmail(username);
		if(userEntity ==null) throw new UsernameNotFoundException("not found");
		return new User(userEntity.getEmail(),userEntity.getEncryptedPassword(),true,true,true,true,
				new ArrayList<>());
	}

}
