package ua.krasnovnikita.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import ua.krasnovnikita.entity.Blog;
import ua.krasnovnikita.entity.Item;
import ua.krasnovnikita.entity.User;
import ua.krasnovnikita.exception.RssException;
import ua.krasnovnikita.repository.BlogRepository;
import ua.krasnovnikita.repository.ItemRepository;
import ua.krasnovnikita.repository.UserRepository;

@Service
public class BlogService {
	@Autowired
	BlogRepository blogRepository;
	@Autowired
	UserRepository userRepository;

	@Autowired
	RssService rssService;

	@Autowired
	ItemRepository itemRepository;

	public void saveItems(Blog blog) {
		try {
			List<Item> items = rssService.getItems(blog.getUrl());
			for (Item item : items) {
				Item savedItem = itemRepository.findByBlogAndLink(blog, item.getLink());
				if (savedItem == null) {
					item.setBlog(blog);
					itemRepository.save(item);
				}

			}
		} catch (RssException e) {
			e.printStackTrace();
		}
	}

	public void save(Blog blog, String name) {
		User user = userRepository.findByName(name);
		blog.setUser(user);
		blogRepository.save(blog);
		saveItems(blog);
	}

	@PreAuthorize("#blog.user.name == authentication.name or hasRole('ROLE_ADMIN')")
	public void delete(@P("blog") Blog blog) {
		blogRepository.delete(blog);
	}

	public Blog findOne(int id) {
		return blogRepository.findOne(id);
	}

	// 1 hour = 60 seconds* 60 minutes * 1000
	@Scheduled(fixedDelay = 3600000)
	public void reloadBlogs() {
		List<Blog> blogs = blogRepository.findAll();
		for (Blog blog : blogs) {
			saveItems(blog);
		}
	}

}
