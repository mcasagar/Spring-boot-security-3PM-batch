package org.vspl.dto;

import java.util.Set;

import org.vspl.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
	private String name;
	private String username;
	private String email;
	private String password;
	private Set<String> roles;
}
