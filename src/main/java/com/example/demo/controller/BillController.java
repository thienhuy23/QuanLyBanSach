package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Bill;
import com.example.demo.entity.Status;
import com.example.demo.entity.Users;
import com.example.demo.repository.StatusRepository;
import com.example.demo.service.BillService;
import com.example.demo.service.BookService;
import com.example.demo.service.MailerService;
import com.example.demo.service.UsersService;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;

@Controller
public class BillController {
	@Autowired
	UsersService usersService;

	@Autowired
	BillService billService;

	@Autowired
	StatusRepository repo;
	@Autowired
	BookService bookService;
	@Autowired
	MailerService mailerService;

	@GetMapping("/bill")
	public String Purchase(HttpSession session) {
		// Users user = (Users)session.getAttribute("user");
		// usersService.findById(user.getId());
		return "page/bill";
	}

	@ResponseBody
	@PostMapping("/bill")
	public List<?> savePurchase(@RequestParam("user_id") int id, @RequestBody Bill bill) throws MessagingException {
		Users user = usersService.findById(id).get();
		Status status = repo.findById(1).get();
		bill.setUser(user);
		bill.setStatus(status);
		bill.getBdt().stream().forEach(s -> {
			s.setBook(bookService.findById(s.getBook_id()).get());
			s.setBill(bill);
			// System.out.println(s.toString());
		});

		billService.save(bill);
		mailerService.send(user.getEmail(), "Mua hàng", "Bạn vừa mua hàng");
		return billService.findAll();
	}
//
//	@GetMapping("/status_bill")
//	public String ORDER_USER(Model model) {
//		List<Bill> bills = billService.findAll();
//		long count = bills.stream().map(s -> s.getBdt()).filter(s -> s.size() > 0).count();
//		System.out.println("size:" + count + ",sum:" + bills.size());
//		model.addAttribute("bills", bills);
//		return "page/status_bill";
//	}

}
