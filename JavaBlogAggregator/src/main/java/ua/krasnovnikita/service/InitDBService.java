package ua.krasnovnikita.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ua.krasnovnikita.entity.Blog;
import ua.krasnovnikita.entity.Item;
import ua.krasnovnikita.entity.Role;
import ua.krasnovnikita.entity.User;
import ua.krasnovnikita.repository.BlogRepository;
import ua.krasnovnikita.repository.ItemRepository;
import ua.krasnovnikita.repository.RoleRepository;
import ua.krasnovnikita.repository.UserRepository;

@Transactional
@Service
public class InitDBService {
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BlogRepository blogRepository;

	@Autowired
	private ItemRepository itemRepository;

	@PostConstruct
	public void init() {
		Role roleUser = new Role();
		roleUser.setName("ROLE_USER");
		roleRepository.save(roleUser);

		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);

		User userAdmin = new User();
		userAdmin.setName("admin");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userAdmin.setPassword(encoder.encode("admin"));
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleAdmin);
		roles.add(roleUser);
		userAdmin.setRoles(roles);
		userRepository.save(userAdmin);

		Blog firstBlog = new Blog();
		firstBlog.setName("FirstBlog");
		firstBlog.setUrl("http://feeds.feedburner.com/javavids");
		firstBlog.setUser(userAdmin);
		blogRepository.save(firstBlog);

		Item item1 = new Item();
		item1.setBlog(firstBlog);
		item1.setTitle("first");
		item1.setLink("http://football.ua");
		item1.setPublishedDate(new Date());
		itemRepository.save(item1);

		Item item2 = new Item();
		item2.setBlog(firstBlog);
		item2.setTitle("second");
		item2.setLink("http://meta.ua");
		item2.setPublishedDate(new Date());
		itemRepository.save(item2);

	}

}
