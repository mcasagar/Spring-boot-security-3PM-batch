package org.vspl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vspl.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByUsername(String username);
	
	Optional<User> findByUsernameOrEmail(String username, String email);
	
	Optional<User> existsByUsername(String username);
}
