package ua.krasnovnikita.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.krasnovnikita.entity.Blog;
import ua.krasnovnikita.entity.User;
import ua.krasnovnikita.repository.BlogRepository;
import ua.krasnovnikita.repository.UserRepository;

@Service
public class BlogService {
	@Autowired
	BlogRepository blogRepository;
	@Autowired
	UserRepository userRepository;

	public void save(Blog blog, String name) {
		User user = userRepository.findByName(name);
		blog.setUser(user);
		blogRepository.save(blog);
	}

}
