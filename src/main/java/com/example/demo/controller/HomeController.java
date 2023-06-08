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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.demo.entity.Bill;
import com.example.demo.entity.Status;
import com.example.demo.entity.Users;
import com.example.demo.repository.StatusRepository;
import com.example.demo.service.BillService;
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

	@Autowired
	BillService billService;

	@Autowired
	StatusRepository repo;

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
	
	@GetMapping("/purchase")
	public String Purchase(HttpSession session) {
		// Users user = (Users)session.getAttribute("user");
		// usersService.findById(user.getId());
		return "page/Purchase";
	}

	@PostMapping("/purchase")
	public String savePurchase(@RequestParam("user_id") int id,@RequestBody Bill bill) {
		Users user =usersService.findById(id).get();
		Status status = repo.findById(1).get();
		bill.setUser(user);
		bill.setStatus(status);
		billService.save(bill);
		return "redirect:/ORDER_USER";
	}

	@GetMapping("/ORDER_USER")
	public String ORDER_USER(Model model) {
		model.addAttribute("bill",billService.findAll());
		return "page/ORDER_USER";
		
	}
	
	@GetMapping("/USER")
	public String USER() {
		return "page/user";
		
	}
}
