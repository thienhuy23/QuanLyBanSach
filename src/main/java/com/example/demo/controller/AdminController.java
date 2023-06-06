package com.example.demo.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Author;
import com.example.demo.entity.Category;
import com.example.demo.entity.Supplier;
import com.example.demo.entity.Users;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.SupplierRepository;
import com.example.demo.repository.UsersRepository;
import com.example.demo.service.AuthorService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.SupplierService;
import com.example.demo.service.UsersService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class AdminController {
	@Autowired
	UsersService usersService;
	@Autowired
	UsersRepository userrepo;
	@Autowired
	CategoryService categoryService;
	@Autowired 
	CategoryRepository categoryrepo;
	@Autowired
	SupplierService supplierServ;
	@Autowired
	AuthorService authorService;
	@Autowired 
	UsersRepository repository;
	@Autowired
	SupplierRepository SupplierRep;
	@Autowired
	AuthorRepository AuthorRep;

	@RequestMapping("/admin")
	public String home(HttpSession session, Principal principal) {
		if (principal != null) {
			session.setAttribute("user", usersService.findByEmail(principal.getName()));
		}
		return "page/statistical_admin";
	}

	// Supplier
	@RequestMapping("/supplier")
	public String supplier(Model model,@RequestParam("field") Optional<String> field) {
		model.addAttribute("supplierr", new Supplier());
		Sort sort = Sort.by(field.orElse("name"));
		model.addAttribute("supplier", SupplierRep.findAll(sort));
		return "page/supplier_admin";
	}

	@RequestMapping("/admin/CreateSupplier")
	public String CreateSupplier(@Valid @ModelAttribute("supplierr") Supplier supplier, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "page/supplier_admin";
		} else {
			supplierServ.save(supplier);
			return "redirect:/supplier";
		}
	}

	@RequestMapping("/admin/DeleteSupplier/{id}")
	public String DeleteSupplier(Model model, @PathVariable("id") Integer id) {
		supplierServ.deleteById(id);
		return "redirect:/supplier";

	}

	@RequestMapping("/admin/EditSupplier/{id}")
	public String EditSupplier(Model model, @PathVariable("id") Integer id) {
		System.out.println(id);
		Optional<Supplier> supplier = supplierServ.findById(id);
//		model.addAttribute("supplier", supplierServ.findById(id));
		System.out.println(supplier.toString());
		return "redirect:/supplier";

	}

	@RequestMapping("/admin/searchSupplier")
	public String search(Model model, @RequestParam("name") Optional<String> name,
			@RequestParam("field") Optional<String> field) {
		model.addAttribute("supplierr", new Supplier());
		Sort sort = Sort.by(field.orElse("name"));
		List<Supplier> supplier = SupplierRep.findAllByNameLike(name, sort);
		model.addAttribute("supplier", supplier);
		return "page/supplier_admin";
	}

	// Author
	@RequestMapping("/author")
	public String author(Model model,@RequestParam("field") Optional<String> field) {
		model.addAttribute("authorr", new Author());
		Sort sort = Sort.by(field.orElse("name"));
		model.addAttribute("author" ,AuthorRep.findAll(sort));
		return "page/author_admin";
	}

	@RequestMapping("/admin/CreateAuthor")
	public String CreateAuthor(@Valid @ModelAttribute("authorr") Author author, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("author", authorService.findAll());
			return "page/author_admin";
		} else {
			authorService.save(author);
			return "redirect:/author";
		}
	}

	@RequestMapping("/admin/DeleteAuthor/{id}")
	public String DeleteAuthor(Model model, @PathVariable("id") Integer id) {
		authorService.deleteById(id);
		return "redirect:/author";
	}

	@RequestMapping("/admin/EditAuthor/{id}")
	public String EditAuthor(Model model, @PathVariable("id") Integer id) {
		Optional<Author> author = authorService.findById(id);
		model.addAttribute("author", author.get());
		return "page/author_admin";
	}
	
	@RequestMapping("/admin/searchAuthor")
	public String searchAuthor(Model model,@RequestParam("name") Optional<String> name,
											@RequestParam("field") Optional<String> field) {
		model.addAttribute("authorr", new Author());
		Sort sort = Sort.by(field.orElse("name"));
		List<Author> author = AuthorRep.findAllByNameLike(name, sort);
		model.addAttribute("author", author);
		return "page/author_admin";
	}

	@RequestMapping("/category")
	public String category(Model model) {
		List<Category> category = categoryService.findAll();
		model.addAttribute("categorys", category);
		return "page/category_admin";
	}

	@RequestMapping("/image")
	public String image() {
		return "page/image_admin";
	}

	@RequestMapping("/product")
	public String product() {
		return "page/book_admin";
	}

	@RequestMapping("/account")
	public String account(Model model) {
		List<Users> user = usersService.findAll();
		model.addAttribute("users", user);
		return "page/account_admin";

	}

	@RequestMapping("/order")
	public String order() {
		return "page/order_admin";

	}

	@RequestMapping("/admin_js")
	public String home(Model model) {
		List<Users> user = usersService.findAll();
		model.addAttribute("users", user);
		List<Category> category = categoryService.findAll();
		model.addAttribute("categorys", category);
		return "page/admin";

	}

	@PostMapping("/user/create")
	public String createUser(Model model, @ModelAttribute("users") Users user) {
		System.out.println("user update:" + user);
		usersService.save(user);
		model.addAttribute("users", user);
		model.addAttribute("message", "Thêm mới thành công!");
		return "redirect:/account";
	}

	@PostMapping("/user/update")
	public String updateUser(Model model, @ModelAttribute("users") Users user) {
		System.out.println("user update:" + user);
		usersService.update(user);
		model.addAttribute("users", user);
		model.addAttribute("message", "Cập nhật thành công!");
		return "redirect:/account";
	}

	@RequestMapping("/user/delete/{id}")
	public String DeletetUsersId(@PathVariable("id") int id, Model model) {
		usersService.deleteUserId(id);
		List<Users> user = usersService.findAll();
		model.addAttribute("users", user);
		return "redirect:/account";
	}
	@RequestMapping("/user/search")
	public String searchUsers(Model model, @RequestParam("search") Optional<String> search) {
		List<Users> user = userrepo.findAllByNameLike(search);
		model.addAttribute("users", user);
		System.out.println(search);
		System.out.println(user);
		return "page/account_admin";

	}
	@RequestMapping("/category/search")
	public String searchCategorys(Model model, @RequestParam("search") Optional<String> search) {
		List<Category> cate = categoryrepo.findAllByNameLike(search);
		model.addAttribute("categorys", cate);
		System.out.println(search);
		System.out.println(cate);
		return "page/category_admin";

	}

	@PostMapping("/category/create")
	public String createCategory(Model model, @ModelAttribute("category") Category category) {
		System.out.println("user update:" + category);
		categoryService.createCategory(category);
		model.addAttribute("categorys", category);
		model.addAttribute("message", "Thêm mới thành công!");
		return "redirect:/category";
	}

	@PostMapping("/category/update")
	public String updateCategory(Model model, @ModelAttribute("category") Category category) {
		System.out.println("user update:" + category);
		categoryService.update(category);
		model.addAttribute("categorys", category);
		model.addAttribute("message", "Cập nhật thành công!");
		return "redirect:/category";
	}

	@RequestMapping("/category/delete/{id}")
	public String DeleteCategoryId(Model model, @PathVariable("id") int id) {
		categoryService.deleteCategoryId(id);
		return "redirect:/category";
	}


}