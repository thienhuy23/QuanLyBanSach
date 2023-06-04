package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
	@RequestMapping("/statistical")
	public String home() {
		return "page/statistical_admin";
	}
	@RequestMapping("/supplier")
	public String supplier() {
		return "page/supplier_admin";
	}
	@RequestMapping("/author")
	public String author() {
		return "page/author_admin";
	}
	@RequestMapping("/category")
	public String category() {
		return "page/category_admin";
	}
	@RequestMapping("/image")
	public String image() {
		return "page/image_admin";
	}
	@RequestMapping("/product")
	public String product() {
		return "page/book_admin";
	}
	@RequestMapping("/account")
	public String account() {
		return "page/account_admin";
	}
}
