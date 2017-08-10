package ua.krasnovnikita.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.krasnovnikita.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByName(String name);

}
