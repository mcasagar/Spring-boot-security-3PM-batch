package org.vspl.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.vspl.dto.LoginDto;
import org.vspl.dto.RegisterDto;
import org.vspl.entity.Role;
import org.vspl.entity.User;
import org.vspl.repository.RoleRepository;
import org.vspl.repository.UserRepository;
import org.vspl.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
	
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	private AuthenticationManager authenticationManager;
	
	public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository, 
						PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
	}

	@Override
	public String registerUser(RegisterDto registerDto) {
		
		User user = new User();
		user.setEmail(registerDto.getEmail());
		user.setName(registerDto.getName());
		user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
		user.setUsername(registerDto.getUsername());
		
		Set<Role> roles = new HashSet<>();
		
		for(String role : registerDto.getRoles()) {
			roles.add(roleRepository.findByName(role));
		}
		
		user.setRoles(roles);
		
		userRepository.save(user);
		
		return "User register successfully";
	}
	
	@Override
	public String login(LoginDto loginDto) {
		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		return "User login successfully...";
	}

}
