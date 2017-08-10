package ua.krasnovnikita.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.krasnovnikita.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(String name);

}
