package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Users;
import com.example.demo.service.UsersService;

@Controller
@RequestMapping("/profile")
public class ProfileController {
	@Autowired
	UsersService usersService;

	@GetMapping({ "/", "" })
	public String getProfile(@RequestParam("profileId") int profileid, Model model) {
		Optional<Users> users = usersService.findById(profileid);

		if (!users.isPresent()) {
			return "redirect:/error";
		}
		if (users.get().getRole() == true) {
			return "redirect:/error";
		}
		System.out.println(users.get());
		model.addAttribute("users", users.get());
//		model.addAttribute("listUsers",usersService.findListExpect(profileid));
		return "page/profile";
	}

	@PostMapping("update")
	public String updateProfile(@ModelAttribute("users") Users user, @RequestParam(name = "role") String role,
			Model model) {
		if (role != null) {
			if (role.equals("1")) {
				user.setRole(true);
			} else {
				user.setRole(false);
			}
		}

		else {
			user.setRole(false);
		}
		System.out.println("user update:" + user);
		usersService.update(user);
		model.addAttribute("users", new Users());

		return "page/profile";
	}



}
