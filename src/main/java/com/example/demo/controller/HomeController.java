package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.BookService;

@Controller
public class HomeController {
	
	@Autowired
	BookService bookService;
	
	@GetMapping(value = {"","/","/home"})
	public String home(Model model){
		model.addAttribute("books",bookService.findAll());
		return "page/home";
	}
}
