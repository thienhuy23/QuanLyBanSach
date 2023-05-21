package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.BookService;
import com.example.demo.service.CategoryService;

@Controller
public class HomeController {
	
	@Autowired
	BookService bookService;
	@Autowired
	CategoryService categoryService;
	
	@GetMapping(value = {"","/","/home"})
	public String home(Model model){
		model.addAttribute("books",bookService.getPagination(5, 0));
		model.addAttribute("categories",categoryService.findAll());
		return "page/home";
	}
}
