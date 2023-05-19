package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Users;
import com.example.demo.repository.UsersRepository;
import com.example.demo.service.UsersService;

import jakarta.servlet.http.HttpSession;

@Controller

public class LoginController {
	@Autowired
	UsersService service;

	@Autowired
	HttpSession session;
	@RequestMapping("/login")
	public String getLogin() {
		return "page/login";
	}
	@PostMapping("CheckLogin")
	public String checkLogin(@RequestParam("email") String email, @RequestParam("password")String password 
			,Model model) {
		Users Users = service.getOneUser(email,password);
		return "page/cart";
	}
	@GetMapping("logout")
	public String logout() {
		return "page/login";
	}
}

