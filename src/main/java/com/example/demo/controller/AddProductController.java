package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import com.example.demo.repository.BookRepository;
import com.example.demo.service.AuthorService;
import com.example.demo.service.BookService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.SupplierService;



@Controller
public class AddProductController {
	@Autowired
	BookService bookSV;
	@Autowired
	BookRepository bookrepo;
	@Autowired
	SupplierService supplierServ;
	@Autowired
	AuthorService authorService;
	@Autowired
	CategoryService categoryService;
	@RequestMapping("/product")
	public String product(Model model) {
		model.addAttribute("Author", authorService.findAll());
		model.addAttribute("Supplier", supplierServ.findAll());
		model.addAttribute("Cateory", categoryService.findAll());
		model.addAttribute("book",bookSV.findAll());
		return "page/book_admin";
	}
}
