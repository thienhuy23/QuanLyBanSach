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
	public String getBook(@RequestParam("bookId") Optional<Integer> bookId,Model model) {
		if(!bookId.isPresent()) {
			return "redirect:/error.html";
		}
		Optional<Book> book = bookService.findById(bookId.get());

		model.addAttribute("book", book.get());
<<<<<<< HEAD
		// bookService.findListExpect(bookId).stream().map(s->s.getImages().size()).forEach(System.out::println);
		model.addAttribute("listBook",bookService.findListExpect(bookId));
=======
		model.addAttribute("listBook",bookService.findListExpect(bookId.get()));
>>>>>>> origin/master
		return "page/book";
	}
	@GetMapping("/error")
	public String error() {
		return "page/error";
	}
}
