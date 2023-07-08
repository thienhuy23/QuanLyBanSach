package com.example.demo.controller;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.BookDto;
import com.example.demo.entity.Bill;
import com.example.demo.entity.Bill_detail;
import com.example.demo.entity.Book;
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

	@PostMapping("/bill")
	public String purchase(@RequestParam("user_id") int id, @RequestParam("cart") List<String> list, Model model,HttpSession session) {
		List<Book> books = bookService
				.findByListId(list.stream().map(s -> s.split("_")[0]).map(Integer::parseInt).toList());
		Bill bill = new Bill();
		IntStream.range(0, books.size())
				.forEach(s->{
					books.get(s).setQuantity(Integer.parseInt(list.get(s).split("_")[1]));
					bill.setSum(bill.getSum()+(books.get(s).getQuantity()*books.get(s).getPrice()));
				});
		List<Bill_detail> bdt = books.stream().map(s->new Bill_detail(0, s.getQuantity(), s, s.getId(), null)).toList();
		bill.setBdt(bdt);
		bill.setUser(usersService.findById(id).get());
		model.addAttribute("bill", bill);
		session.setAttribute("bill", bill);
		return "page/bill";
	}

	@ResponseBody

	@PostMapping("/bill/tt")
	public String savePurchase(HttpSession session, @RequestParam("place") String place) throws MessagingException {
		Bill bill = (Bill)session.getAttribute("bill");
		bill.setReceive_place(place);
		Status status = repo.findById(1).get();
		bill.setStatus(status);
		bill.getBdt().stream().forEach(s -> {
			s.setBook(bookService.findById(s.getBook_id()).get());
			s.setBill(bill);
		});
		billService.save(bill);
		mailerService.send(bill.getUser().getEmail(), "Mua hàng", bill);
		return "redirect:/profile?profileId="+bill.getUser().getId();
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
