package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Users;
import com.example.demo.repository.RegisterRepository;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	RegisterRepository RegisterRepo;
	
	@GetMapping(value={"/",""})
	public String getRegister(Users user) {
		return "page/register";
	}
	@PostMapping("/insert")
	public String postRegister(@Valid Users user, BindingResult result,  Model model) {
		 if (result.hasErrors()) {
			 return "page/register";
		}
		 RegisterRepo.save(user);
			model.addAttribute("user", user);
		return "page/register";
		
	}
}
