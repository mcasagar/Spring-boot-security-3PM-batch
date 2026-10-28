package org.vspl.service;

import org.vspl.dto.LoginDto;
import org.vspl.dto.RegisterDto;

public interface AuthService {

	String registerUser(RegisterDto registerDto);
	
	String login(LoginDto loginDto);
}
