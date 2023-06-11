package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Users; 
import com.example.demo.service.RegisterService;

import jakarta.validation.Valid;
 
@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	RegisterService registerRService;
 
	@GetMapping(value={"/",""})
	public String getRegister(Model model ) {
		model.addAttribute("user", new Users());
		return "page/register";
	}
	@PostMapping(value={"/",""})
	public String postRegister(@Valid @ModelAttribute("user") Users user ,BindingResult result ,Model model ) {
		user.setRole(false);
		System.out.println(user.toString());
		if (result.hasErrors()) {
			return "page/register";
		}else {
<<<<<<< HEAD
			user.setRole(false);
			registerRService.save(user);
=======
			System.out.println(registerRService.save(user).toString());
>>>>>>> origin/master
			model.addAttribute("message","Đăng ký thành công, Vui lòng đăng nhập");
			return "redirect:/login";
		}
		
	}
	@PostMapping("/tb")
	public String thongbao() {
		return "redirect:/login";
	}
}
