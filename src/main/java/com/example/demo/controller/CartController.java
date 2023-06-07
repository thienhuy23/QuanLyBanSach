package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.dto.BookDto;
import com.example.demo.entity.Book;
import com.example.demo.service.BookService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	BookService bookService;


	@GetMapping(value={"/",""})
	public String getRegister() {
		return "page/cart";
	}
	@ResponseBody
	@GetMapping(value="/get")
	public ResponseEntity<List<Book>> getValue(@RequestParam("arr") String arr) throws SQLException {
		List<Integer> list = Arrays.asList(arr.split(","))
									.stream()
									.map(Integer::parseInt)
									.toList();
		// bookService.findByListId(list).stream().map(s->s.getName()).forEach(System.out::println);
		// List<Integer> list= Arrays.asList(arr.split(",")).stream().map(Integer::parseInt).toList();
		// System.out.println(list.toString());
		return ResponseEntity.ok(bookService.findByListId(list));
		// return ResponseEntity.ok(null);
	}
	
	
}
