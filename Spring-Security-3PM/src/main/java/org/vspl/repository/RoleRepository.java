package org.vspl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vspl.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
