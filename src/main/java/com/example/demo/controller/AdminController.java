package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Category;
import com.example.demo.entity.Users;
import com.example.demo.repository.UsersRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.UsersService;

import jakarta.validation.Valid;

@Controller
public class AdminController {
	@Autowired
	UsersService usersService;
	@Autowired
	CategoryService categoryService;
	@RequestMapping("/admin")
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
	@RequestMapping("/order")
	public String order() {
		return "page/order_admin";

	}
	
	@RequestMapping("/admin_js")
	public String home(Model model) {
		List<Users> user = usersService.findAll();
		model.addAttribute("users", user);
		List<Category> category = categoryService.findAll();
		model.addAttribute("categorys", category);
		return "page/admin";
	}
	
	@PostMapping("/user/create")
	public String createUser(Model model, @ModelAttribute("users") Users user) {	
			System.out.println("user update:" + user);
			usersService.save(user);
			model.addAttribute("users", user);
			model.addAttribute("message", "Thêm mới thành công!");
		return "redirect:/admin";
	}
	
	@PostMapping("/user/update")
	public String updateUser(Model model, @ModelAttribute("users") Users user) {	
			System.out.println("user update:" + user);
			usersService.update(user);
			model.addAttribute("users", user);
			model.addAttribute("message", "Cập nhật thành công!");
		return "redirect:/admin";
	}
	
	@RequestMapping("/user/delete/{id}")
	public String DeletetUsersId( @PathVariable("id") int id) {		
		usersService.deleteUserId(id);
		return "/page/admin";
	}
	
	@PostMapping("/category/create")
	public String createCategory(Model model, @ModelAttribute("category") Category category) {	
			System.out.println("user update:" + category);
			categoryService.createCategory(category);
			model.addAttribute("users", category);
			model.addAttribute("message", "Thêm mới thành công!");
		return "page/admin";
	}
	@PostMapping("/category/update")
	public String updateCategory(Model model, @ModelAttribute("category") Category category) {	
			System.out.println("user update:" + category);
			categoryService.update(category);
			model.addAttribute("users", category);
			model.addAttribute("message", "Cập nhật thành công!");
		return "redirect:/admin";
	}
	
	@RequestMapping("/category/delete/{id}")
	public String DeleteCategoryId(Model model, @PathVariable("id") int id) {		
		categoryService.deleteCategoryId(id);	
		return "/page/admin";
	}
	
}