package com.sagarbhoi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sagarbhoi.dto.JwtAuthResponseDto;
import com.sagarbhoi.dto.LoginDto;
import com.sagarbhoi.dto.RegisterDto;
import com.sagarbhoi.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	
	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
		  String message = authService.register(registerDto);
		 return new ResponseEntity<>(message, HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponseDto> login(@RequestBody LoginDto loginDto){
		String token = authService.login(loginDto);
		JwtAuthResponseDto jwtAuthResponseDto = new JwtAuthResponseDto(token);
		//return new ResponseEntity<>(message, HttpStatus.OK);
		return ResponseEntity.ok(jwtAuthResponseDto);
	}
	
	@PostMapping("/logout") 
	public ResponseEntity<String> logout( @RequestHeader("Authorization") String authorizationHeader) 
	{ 
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) { 
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); 
		} 
		String token = authorizationHeader.substring(7); 
		String message = authService.logout(token); 
		//return ResponseEntity.noContent().build();
		return new ResponseEntity<>(message, HttpStatus.NO_CONTENT);
		
	}
}
