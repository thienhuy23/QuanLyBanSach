package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;

@Controller
@RequestMapping("/book")
public class BookController {
	@Autowired
	BookService bookService;
	
	@GetMapping({"/",""})
	public String getBook(@RequestParam("bookId") int bookId,Model model) {
		Optional<Book> book = bookService.findById(bookId);
		if(!book.isPresent()) {
			return "redirect:/error";
		}
		model.addAttribute("book", book.get());
		model.addAttribute("listBook",bookService.findListExpect(bookId));
		return "page/book";
	}
	@GetMapping("/error")
	public String error() {
		return "page/error";
	}
}
