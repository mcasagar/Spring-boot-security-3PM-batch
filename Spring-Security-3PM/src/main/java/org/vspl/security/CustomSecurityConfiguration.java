package org.vspl.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.vspl.exception.CustomAuthenticationEntryPoint;

@Configuration
@EnableMethodSecurity
public class CustomSecurityConfiguration {
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean 
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	SecurityFilterChain customSecurityFilterChain(HttpSecurity http) {
		http.authorizeHttpRequests((requests) -> requests
		.requestMatchers(HttpMethod.GET, "/api/admin", "/api/user").authenticated()
		.requestMatchers(HttpMethod.GET, "/api/welcome").permitAll()
		.requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
		.anyRequest().authenticated()
		
	);
		
		http.formLogin(form -> form.disable());
		http.httpBasic(httpBasic -> httpBasic.authenticationEntryPoint(new CustomAuthenticationEntryPoint()));
		http.csrf(csrf -> csrf.disable());
		return http.build();
	}
	
	
//	@Bean
//	UserDetailsService userDetailsService() {
//		
//		UserDetails admin = User.builder()
//				.username("admin123")
//				.password(passwordEncoder().encode("321"))
//				.roles("ADMIN")
//				.build();
//		UserDetails user = User.builder()
//				.username("sam123")
//				.password(passwordEncoder().encode("321"))
//				.roles("USER")
//				.build();
//		
//		return new InMemoryUserDetailsManager(admin, user);
//		
//	}

}
