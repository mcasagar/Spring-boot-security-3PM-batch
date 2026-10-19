package org.vspl.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@GetMapping("/user")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public String getUser() {
		return "Hello! User";
	}

}
