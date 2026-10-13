package org.vspl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Welcome {

	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to app";
	}
}
