package com.sherwin.foodbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sherwin.foodbox.jwt.JwtUtil;
import com.sherwin.foodbox.model.Jwt;
import com.sherwin.foodbox.model.JwtResponse;
import com.sherwin.foodbox.service.CustomUserDetailsService;

@RestController
public class JwtController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	
	//validating the username and password from the client and generating token
	@RequestMapping(value = "/token" , method=RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody Jwt jwtRequest) throws Exception {
		System.out.println(jwtRequest);
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
		}
		catch(UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Bad Credientials");
		}
		catch(BadCredentialsException e) {
			e.printStackTrace();
			throw new Exception("Bad credientials");
		}
		
		UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
		
		String token = this.jwtUtil.generateToken(userDetails);
		System.out.println("JWT token : " + token);
	
		return ResponseEntity.ok(new JwtResponse(token));
		
	}
}
