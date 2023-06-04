package com.example.demo.controller;

import java.security.Principal;
import java.util.Optional;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.demo.entity.Users;
import com.example.demo.service.BookService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.UsersService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	BookService bookService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	UsersService usersService;

	@GetMapping(value = { "", "/", "/home" })
	public String home(@RequestParam("index") Optional<Integer> index, 
						Model model,
						HttpSession session,
						Principal principal) {
		int i = index.isPresent() ? index.get() : 0;
		model.addAttribute("index", i);
		model.addAttribute("books", bookService.getPagination(i, 8));
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("count", Math.round(bookService.getCount() / 8));
		System.out.println(Math.ceil(bookService.getCount() / 8));
		if(principal != null){
			session.setAttribute("user",usersService.findByEmail(principal.getName()));
		}
		return "page/home";
	}

	@GetMapping("/error")
	public String error() {
		return "page/error";
	}

	// @SessionAttribute("user")
	// public Users getUser(Principal principal) {
	// 	return usersService.findByEmail(principal.getName());
	// }
}
