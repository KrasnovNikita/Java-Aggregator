package ua.krasnovnikita.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.krasnovnikita.entity.Blog;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

}
