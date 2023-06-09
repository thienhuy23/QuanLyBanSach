package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.SupplierRepository;
import com.example.demo.repository.UsersRepository;
import com.example.demo.service.AuthorService;
import com.example.demo.service.BookService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ImageService;
import com.example.demo.service.SupplierService;
import com.example.demo.service.UsersService;

public class addProductController {

	@Autowired
	CategoryService categoryService;
	@Autowired
	CategoryRepository categoryrepo;
	@Autowired
	SupplierService supplierServ;
	@Autowired
	SupplierRepository SupplierRep;
	@Autowired
	AuthorService authorService;
	@Autowired
	BookService bookService;
	@Autowired
	BookRepository bookRep;
	@Autowired
	AuthorRepository AuthorRep;
	@Autowired
	BookService bookservice;

	/*
	 * @RequestMapping("/product") public String product(Model model) {
	 * model.addAttribute("Author", authorService.findAll());
	 * model.addAttribute("Supplier", supplierServ.findAll());
	 * model.addAttribute("Cateory", categoryService.findAll());
	 * model.addAttribute("book", bookService.findAll()); return "page/cart"; }
	 */
	
	
	/*
	 * @RequestMapping("/ProductCreate") public String
	 * ProductCreate(@RequestParam("category") Optional<Integer> category,
	 * 
	 * @RequestParam("supplier") Optional<Integer> supplier,
	 * 
	 * @RequestParam("author") Optional<Integer> author,
	 * 
	 * @RequestParam("name") Optional<String> name,
	 * 
	 * @RequestParam("price") Optional<Double> price,
	 * 
	 * @RequestParam("discount") Optional<Float> discount,
	 * 
	 * @RequestParam("published_year") Optional<Integer> published_year,
	 * 
	 * @RequestParam("number_page") Optional<Integer> number_page,
	 * 
	 * @RequestParam("describe") Optional<String> describe) {
	 * bookRep.saveBook(category, supplier, author, name, price, discount,
	 * published_year, number_page, describe);
	 * 
	 * return "redirect:/product"; }
	 */
////	model.addAttribute("bookk", new Book());
//	System.out.println(name+"name");
//	System.out.println(price+"gia");
//	System.out.println(discount+"discount");
//	System.out.println(published_year+"published_year");
//	System.out.println(number_page+"number_page");
//	System.out.println(describe+"describe");
//	System.out.println(author+"a");
//	System.out.println(supplier+"b");
//	System.out.println(category+"c");
	

}
