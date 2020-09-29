package com.sathish.usermicroservice.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sathish.usermicroservice.model.LoginModel;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req,HttpServletResponse res) throws AuthenticationException
	{
		try {
			
			LoginModel cred = new ObjectMapper().readValue(req.getInputStream(), LoginModel.class);
			return getAuthenticationManager().authenticate(
					new UsernamePasswordAuthenticationToken(cred.getEmail(),cred.getPassword(),new ArrayList<>()));
			}catch(IOException e) 
			{
			throw new RuntimeException(e);
			}
	}
	@Override
	protected void successfulAuthentication(HttpServletRequest req,HttpServletResponse res,
			FilterChain chain,Authentication auth)throws IOException,ServletException{
		
	}
}
