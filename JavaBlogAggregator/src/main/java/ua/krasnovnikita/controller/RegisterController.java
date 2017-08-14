package ua.krasnovnikita.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ua.krasnovnikita.entity.User;
import ua.krasnovnikita.service.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private UserService userService;

	@RequestMapping
	public String showRegister() {
		return "user-register";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doRegister(@Valid @ModelAttribute("user") User user, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "user-register";
		}
		userService.saveUser(user);
		redirectAttributes.addFlashAttribute("success", true);
		return "redirect:/register.html";
	}

	@ModelAttribute("user")
	public User constructUser() {
		return new User();
	}

	@RequestMapping("/avaliable")
	@ResponseBody
	public String avaliable(@RequestParam String userName) {
		Boolean avaliable = userService.findOne(userName) == null;
		return avaliable.toString();

	}

}
