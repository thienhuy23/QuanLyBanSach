package com.example.demo.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.entity.Category;
import com.example.demo.entity.Image;
import com.example.demo.entity.Supplier;
import com.example.demo.entity.Users;
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

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RequestMapping("/admin")
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
	BookService bookService;
	@Autowired
	BookRepository bookRep;
	@Autowired
	SupplierRepository SupplierRep;
	@Autowired
	AuthorRepository AuthorRep;

	@Autowired
	ImageService imgservice;
	@Autowired
	BookService bookservice;

	@RequestMapping("/product")
	public String product(Model model) {
		model.addAttribute("book", bookService.findAll());
		return "page/book_admin";
	}

	@RequestMapping("/ProductCreate")
	public String ProductCreate(@RequestParam("category") Optional<Integer> category,
			@RequestParam("supplier") Optional<Integer> supplier, @RequestParam("author") Optional<Integer> author,
			@RequestParam("name") Optional<String> name, @RequestParam("price") Optional<Double> price,
			@RequestParam("discount") Optional<Float> discount,
			@RequestParam("published_year") Optional<Integer> published_year,
			@RequestParam("number_page") Optional<Integer> number_page,
			@RequestParam("describe") Optional<String> describe) {
		bookRep.saveBook(category, supplier, author, name, price, discount, published_year, number_page, describe);
////		model.addAttribute("bookk", new Book());
//		System.out.println(name+"name");
//		System.out.println(price+"gia");
//		System.out.println(discount+"discount");
//		System.out.println(published_year+"published_year");
//		System.out.println(number_page+"number_page");
//		System.out.println(describe+"describe");
//		System.out.println(author+"a");
//		System.out.println(supplier+"b");
//		System.out.println(category+"c");
		return "redirect:/product";
	}

	@RequestMapping("")
	public String home(Model model, HttpSession session, Principal principal) {
		model.addAttribute("count", userrepo.ListReportNbMembers());
		if (principal != null) {
			session.setAttribute("user", usersService.findByEmail(principal.getName()));
		}
		return "page/statistical_admin";
	}

	// Supplier
	@RequestMapping("/supplier")
	public String supplier(Model model, @RequestParam("field") Optional<String> field) {
		model.addAttribute("supplierr", new Supplier());

		Sort sort = Sort.by(field.orElse("name"));
		model.addAttribute("supplier", SupplierRep.findAll(sort));
		return "page/supplier_admin";
	}

	@RequestMapping("/searchSupplier")
	public String search(Model model, @RequestParam("name") Optional<String> name,
			@RequestParam("field") Optional<String> field) {
		model.addAttribute("supplierr", new Supplier());
		Sort sort = Sort.by(field.orElse("name"));
		List<Supplier> supplier = SupplierRep.findAllByNameLike(name, sort);
		model.addAttribute("supplier", supplier);
		return "page/supplier_admin";
	}

	@RequestMapping("/EditSupplier/{id}")
	public String EditSupplier(Model model, @PathVariable("id") Optional<Integer> id) {
		List<Supplier> supplier = SupplierRep.findAllByID(id);
		// model.addAttribute("supplier", supplier);
		System.out.println(supplier.toString());
		return "redirect:/supplier";
	}

	@RequestMapping("/CreateSupplier")
	public String CreateSupplier(@Valid @ModelAttribute("supplierr") Supplier supplier, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "page/supplier_admin";
		} else {
			supplierServ.save(supplier);
			return "redirect:/supplier";
		}
	}

	@RequestMapping("/DeleteSupplier/{id}")
	public String DeleteSupplier(Model model, @PathVariable("id") Integer id) {
		supplierServ.deleteById(id);
		return "redirect:/supplier";

	}

	// Author
	@RequestMapping("/author")
	public String author(Model model, @RequestParam("field") Optional<String> field) {
		List<String> list = new ArrayList<>();
		// if (list.isPresent)
		/*
		 * if (list.isPresent()) {
		 * 
		 * }
		 */
		model.addAttribute("authorr", new Author());
		Sort sort = Sort.by(field.orElse("name"));
		model.addAttribute("author", AuthorRep.findAll(sort));
		return "page/author_admin";
	}

	@RequestMapping("/EditAuthor/{id}")
	public String EditAuthor(Model model, @PathVariable("id") Integer id) {
		Optional<Author> author = authorService.findById(id);
		model.addAttribute("author", author.get());
		return "page/author_admin";
	}

	@RequestMapping("/searchAuthor")
	public String searchAuthor(Model model, @RequestParam("name") Optional<String> name,
			@RequestParam("field") Optional<String> field) {
		model.addAttribute("authorr", new Author());
		Sort sort = Sort.by(field.orElse("name"));
		List<Author> author = AuthorRep.findAllByNameLike(name, sort);
		model.addAttribute("author", author);
		return "page/author_admin";
	}

	@RequestMapping("/DeleteAuthor/{id}")
	public String DeleteAuthor(Model model, @PathVariable("id") Integer id) {
		authorService.deleteById(id);
		return "redirect:/author";
	}

	@RequestMapping("/CreateAuthor")
	public String CreateAuthor(@Valid @ModelAttribute("authorr") Author author, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("author", authorService.findAll());
			return "page/author_admin";
		} else {
			authorService.save(author);
			return "redirect:/author";
		}
	}

	@RequestMapping("/category")
	public String category(Model model) {
		List<Category> category = categoryService.findAll();
		model.addAttribute("categorys", category);
		return "page/category_admin";
	}

	@RequestMapping("/image")
	public String image(Model model) {
		List<Image> img = imgservice.findAll();
		model.addAttribute("book", bookservice.findAll());
		model.addAttribute("img", img);
		return "page/image_admin";
	}

	@PostMapping("/image")
	public String handleImage(Model model, @RequestParam("url") String url,
			@RequestParam(value = "id", required = false) String idString, @RequestParam("bookId") int bookId,
			@RequestParam(value = "action", required = false) String action) {
		int id = 0; // Default value for id
		if (idString != null && !idString.isEmpty()) {
			id = Integer.parseInt(idString);
		}
		if (action != null && action.equals("add")) {

			Image img = new Image();
			Optional<Book> book = bookservice.findById(bookId);
			img.setBook(book.get());
			img.setUrl(url);
			imgservice.save(img);
		} else if (action != null && action.equals("edit")) {
			Optional<Image> image = imgservice.findById(id);
			if (image.isPresent()) {
				image.get().setUrl(url);
				image.get().setBook(bookservice.findById(bookId).get());
				imgservice.save(image.get());
			}
		} else if (action.equals("delete")) {

		}
		return "redirect:/image";
	}

	@GetMapping("/image/edit")
	public String editImage(Model model, @RequestParam("id") int id) {
		Optional<Image> img = imgservice.findById(id);
		model.addAttribute("book", bookservice.findAll());
		model.addAttribute("image", img.get());
		model.addAttribute("img", imgservice.findAll());
		return "page/image_admin";
	}

	

	@RequestMapping("/order")
	public String order() {
		return "page/order_admin";
	}
	@RequestMapping("/account")
	public String account(Model model) {
		List<Users> user = usersService.findAll();
		model.addAttribute("users", user);
		return "page/account_admin";

	}
	@PostMapping("/user/create")
	public String createUser(Model model, @ModelAttribute("users") Users user) {
		System.out.println("user update:" + user);
		usersService.save(user);
		//model.addAttribute("users", user);
		//model.addAttribute("message", "Thêm mới thành công!");
		return "redirect:/account";
	}

	@PostMapping("/user/update")
	public String updateUser(Model model, @ModelAttribute("users") Users user) {
		System.out.println("user update:" + user);
		usersService.update(user);
		// model.addAttribute("users", user);
		// model.addAttribute("message", "Cập nhật thành công!");
		return "redirect:/account";
	}

	@RequestMapping(value = "/user/edit", method = RequestMethod.GET)
	public String editUser(Model model, @RequestParam("id") int id) {
		List<String> list = new ArrayList<>();
		Optional<Users> user = usersService.findById(id);
		if (user.isPresent()) {

		}

		model.addAttribute("users", usersService.findAll());
		model.addAttribute("user", user.orElse(new Users()));
		return "page/account_admin";
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

	@RequestMapping("/category/create")
	public String createCategory(Model model, @ModelAttribute("categorys") Category category) {
		categoryService.createCategory(category);
		model.addAttribute("message", "Thêm mới thành công!");
		return "redirect:/category";

	}

//	@RequestMapping("/category/create")
//	public String createCategory(@Valid @ModelAttribute("categorys") Category category, BindingResult result,
//			Model model) {
//
//		if (result.hasErrors()) {
//			model.addAttribute("categorys", categoryService.findAll());
//			return "page/category_admin";
//		} else {
//			categoryService.createCategory(category);
//			model.addAttribute("message", "Thêm mới thành công!");
//			return "redirect:/category";
//		}
//
//	}
	@RequestMapping("/category/update")
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

	@RequestMapping("/category/edit/{id}")
	public String editCategorys(Model model, @PathVariable("id") int id) {
		Optional<Category> cate = categoryService.findById(id);
		model.addAttribute("categorys", cate.get());
		System.out.println(cate);
		return "redirect:/category";
	}

}