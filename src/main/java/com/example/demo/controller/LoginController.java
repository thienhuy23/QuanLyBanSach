package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.repository.UsersRepository;


@Controller
public class LoginController {
	@Autowired
	UsersRepository usersRepo;

	@GetMapping("/login")
	public String login() {
		return "page/login";
	}
//	@PostMapping("CheckLogin")
//	public String checkLogin(@RequestParam("id")int profileid, @RequestParam("password")String password 
//			,Model model) {
//		Optional<Users> Users = usersRepo.findById(profileid);
//		if(!Users.get().getPassword().equals(password)) {
//			return "redirect:/error";
//		}
//		return "page/cart";
//	}
	@GetMapping("logout")
	public String logout() {
		return "page/login";
	}
	
}

