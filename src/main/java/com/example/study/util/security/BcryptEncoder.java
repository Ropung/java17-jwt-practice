package com.example.study.util.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BcryptEncoder extends BCryptPasswordEncoder {
	
	BcryptEncoder() {
		super(12);
	}
	
	@Override
	public String encode(CharSequence rawPassword) {
		return "{bcrypt}" + super.encode(rawPassword);
	}
	
	// {bcrypt}$...
}
