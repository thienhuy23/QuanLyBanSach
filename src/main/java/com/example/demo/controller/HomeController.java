package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.BookService;
import com.example.demo.service.CategoryService;

@Controller
public class HomeController {
	
	@Autowired
	BookService bookService;
	@Autowired
	CategoryService categoryService;
	
	@GetMapping(value = {"","/","/home"})
	public String home(@RequestParam("index") Optional<Integer> index,Model model){
		int i = index.isPresent() ? index.get() : 0;
		model.addAttribute("index",i);
		model.addAttribute("books",bookService.getPagination(i, 5));
		model.addAttribute("categories",categoryService.findAll());
		model.addAttribute("count", Math.round(bookService.getCount()/5));
		System.out.println(Math.ceil(bookService.getCount()/5));
		return "page/home";
	}
}
