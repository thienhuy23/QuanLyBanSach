package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Bill;
import com.example.demo.entity.Status;
import com.example.demo.entity.Users;
import com.example.demo.repository.StatusRepository;
import com.example.demo.service.BillService;
import com.example.demo.service.UsersService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BillController {
	@Autowired
	UsersService usersService;

	@Autowired
	BillService billService;

	@Autowired
	StatusRepository repo;
	
	@GetMapping("/bill")
	public String Purchase(HttpSession session) {
		// Users user = (Users)session.getAttribute("user");
		// usersService.findById(user.getId());
		return "page/bill";
	}

	@PostMapping("/bill")
	public String savePurchase(@RequestParam("user_id") int id,@RequestBody Bill bill) {
		Users user =usersService.findById(id).get();
		Status status = repo.findById(1).get();
		bill.setUser(user);
		bill.setStatus(status);
		billService.save(bill);
		return "redirect:/status_bill";
	}
	
	@GetMapping("/status_bill")
	public String ORDER_USER(Model model) {
		model.addAttribute("bill",billService.findAll());
		return "page/status_bill";
		
	}
}
