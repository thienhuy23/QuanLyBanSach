package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.BookDto;

@Controller
@RequestMapping("/cart")
public class CartController {
	@GetMapping(value={"/",""})
	public String getRegister() {
		return "page/cart";
	}
	
	@ResponseBody
	@PostMapping(value={"/",""})
	public String getValue(@RequestBody BookDto book) {
		System.out.println(book.toString());
		return "ok";
	}
}
