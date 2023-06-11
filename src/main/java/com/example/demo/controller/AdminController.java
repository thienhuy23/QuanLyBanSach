package com.example.demo.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
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
<<<<<<< HEAD

	@RequestMapping("/product")
	public String product(Model model) {
		model.addAttribute("bookk", new Book());
		model.addAttribute("Author", authorService.findAll());
		model.addAttribute("Supplier", supplierServ.findAll());
		model.addAttribute("Cateory", categoryService.findAll());
		model.addAttribute("book", bookService.findAll());
		return "page/book_admin";
	}

	@RequestMapping("/Product/Create")
	public String ProductCreate(Model model, @ModelAttribute("bookk") Book book) {
		model.addAttribute("book", bookService.save(book));
		return "redirect:/admin/product";
	}

	@RequestMapping("/Product/Delete/{id}")
	public String ProductDelete(@PathVariable("id") Integer id) {
		bookRep.deleteById(id);
		return "redirect:/admin/product";
	}

	@RequestMapping(value = "/Product/edit", method = RequestMethod.GET)
	public String editProduct(Model model, @RequestParam("id") int Id) {
		model.addAttribute("bookk", bookService.findById(Id).orElse(new Book()));
		System.out.println(bookService.findById(Id).orElse(new Book()));
		return "redirect:/admin/product";

	}

	@RequestMapping("")
=======
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping("/admin")
>>>>>>> origin/master
	public String home(Model model, HttpSession session, Principal principal) {
		model.addAttribute("count", userrepo.ListReportNbMembers());
		if (principal != null) {
			session.setAttribute("user", usersService.findByEmail(principal.getName()));
		}
		return "page/statistical_admin";
	}

	// Supplier
	@RequestMapping(value = { "/supplier/{id}", "/supplier" })
	public String supplier(Model model, @RequestParam("name") Optional<String> name,
			@PathVariable("id") Optional<String> id) {
		model.addAttribute("supplierr",
				id.isPresent() ? SupplierRep.findById(Integer.parseInt(id.get())).get() : new Supplier());
		if (!name.isPresent()) {
			model.addAttribute("supplier", SupplierRep.findAll());
			return "page/supplier_admin";
		} else {
			model.addAttribute("supplier", SupplierRep.findAllByNameLike(name));
			return "page/supplier_admin";
		}

	}

	// Author
	@RequestMapping(value = { "/author/{id}", "/author" })
	public String author(Model model, @RequestParam("name") Optional<String> name,
			@PathVariable("id") Optional<Integer> id) {
		model.addAttribute("authorr", id.isPresent() ? AuthorRep.findById(id.get()).get() : new Author());
		if (!name.isPresent()) {
			model.addAttribute("author", AuthorRep.findAll());
			return "page/author_admin";
		}
		List<Author> author = AuthorRep.findAllByNameLike(name);
		model.addAttribute("author", author);
		return "page/author_admin";
	}

	// @RequestMapping("/searchSupplier")
	// public String search(Model model, @RequestParam("name") Optional<String>
	// name) {
	// model.addAttribute("supplierr", new Supplier());
	// List<Supplier> supplier = SupplierRep.findAllByNameLike(name);
	// model.addAttribute("supplier", supplier);
	// return "page/supplier_admin";
	// }

	// @RequestMapping("/EditSupplier/{id}")
	// public String EditSupplier(Model model, @PathVariable("id") Optional<Integer>
	// id) {
	// Optional<Supplier> supplier = SupplierRep.findById(id.get());
	// model.addAttribute("supplier", supplier);
	// return "page/supplier";
	// }

	@RequestMapping("/CreateSupplier")
	public String CreateSupplier(@Valid @ModelAttribute("supplierr") Supplier supplier, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("supplier", supplierServ.findAll());
			return "page/supplier_admin";
		} else {
			supplierServ.save(supplier);
			return "redirect:/admin/supplier";
		}
	}
	
	@RequestMapping("/DeleteSupplier/{id}")
	public String DeleteSupplier(Model model, @PathVariable("id") Integer id) {
		supplierServ.deleteById(id);
		return "redirect:/admin/supplier";

	}

	// @RequestMapping("/supplier/edit")
	// public String editSupplier(Model model, @RequestParam("id") int id) {
	// Optional<Supplier> supplier = supplierServ.findById(id);
	// model.addAttribute("supplier", supplierServ.findAll());
	// model.addAttribute("supplierr", supplier.orElse(new Supplier()));
	// return "page/supplier_admin";
	// }

	// @RequestMapping("/EditAuthor/{id}")
	// public String EditAuthor(Model model, @PathVariable("id") Integer id) {
	// Optional<Author> author = authorService.findById(id);
	// model.addAttribute("author", author.get());
	// return "page/author_admin";
	// }

	// @RequestMapping("/author/edit")
	// public String editAuthur(Model model, @RequestParam("id") int id) {
	// Optional<Author> authur = authorService.findById(id);
	// model.addAttribute("author", authorService.findAll());
	// model.addAttribute("authorr", authur.orElse(new Author()));
	// return "page/author_admin";
	// }

	// @RequestMapping("/searchAuthor")
	// public String searchAuthor(Model model, @RequestParam("name")
	// Optional<String> name,
	// @RequestParam("field") Optional<String> field) {
	// model.addAttribute("authorr", new Author());
	// Sort sort = Sort.by(field.orElse("name"));
	// List<Author> author = AuthorRep.findAllByNameLike(name, sort);
	// model.addAttribute("author", author);
	// return "page/author_admin";
	// }

	@RequestMapping("/DeleteAuthor/{id}")
	public String DeleteAuthor(Model model, @PathVariable("id") Integer id) {
		authorService.deleteById(id);
		return "redirect:/admin/author";
	}

	@RequestMapping("/CreateAuthor")
	public String CreateAuthor(@Valid @ModelAttribute("authorr") Author author, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("author", authorService.findAll());
			return "page/author_admin";
		} else {
			authorService.save(author);
			return "redirect:/admin/author";
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
		model.addAttribute("book", bookService.findAll());
		model.addAttribute("img", img);
		return "page/image_admin";
	}

	@RequestMapping("/image/create")
	public String addImage(@RequestParam("url") String url, @RequestParam("bookId") int bookId) {
		Image img = new Image();
		Optional<Book> book = bookService.findById(bookId);
		img.setBook(book.get());
		img.setUrl(url);
		imgservice.save(img);
		return "redirect:/admin/image";
	}

	@RequestMapping("/image/update")
	public String editImage(@RequestParam("id") String idString, @RequestParam("url") String url,
			@RequestParam("bookId") int bookId) {
		int id = 0; // Default value for id
		if (idString != null && !idString.isEmpty()) {
			id = Integer.parseInt(idString);
		}
		Optional<Image> img = imgservice.findById(id);
		Optional<Book> book = bookService.findById(bookId);
		img.get().setBook(book.get());
		img.get().setUrl(url);
		imgservice.save(img.get());
		return "redirect:/admin/image";
	}

	@RequestMapping("/image/delete/{id}")
	public String DeleteImageid(Model model, @PathVariable("id") int id) {
		imgservice.deleteImageid(id);
		return "redirect:/admin/image";
	}

	@RequestMapping("/image/new")
	public String newImage(Model model) {

		return "redirect:/image";
	}

	@RequestMapping("/image/edit")
	public String editImage(Model model, @RequestParam("id") int id) {
		Optional<Image> img = imgservice.findById(id);
		model.addAttribute("book", bookService.findAll());
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
		List<Users> user = userrepo.findAllByUsers();
		model.addAttribute("users", user);
		return "page/account_admin";

	}

	@PostMapping("/user/create")
	public String createUser(Model model, @RequestParam("name") String name, @RequestParam("password") String password,
			@RequestParam("email") String email, @RequestParam("phone") String phone,
			@RequestParam("role") String role) {
		Users user = new Users();
		user.setName(name);
		user.setPassword(password);
		user.setEmail(email);
		user.setPhone(phone);
		user.setRole((role.equals("1") ? true : false));
		usersService.save(user);
		return "redirect:/admin/account";
	}

	@RequestMapping("/user/update")
	public String updateUser(Model model, @ModelAttribute("users") Users user) {
		usersService.update(user);
		return "redirect:/admin/account";
	}

	@RequestMapping("/user/edit")
	public String editUser(Model model, @RequestParam("id") int id) {
		List<String> list = new ArrayList<>();
		Optional<Users> user = usersService.findById(id);
		model.addAttribute("user", user.orElse(new Users()));
		List<Users> users = userrepo.findAllByUsers();
		model.addAttribute("users", users);

		return "page/account_admin";
	}

	@RequestMapping("/user/delete/{id}")
	public String DeletetUsersId(@PathVariable("id") int id, Model model) {
		usersService.deleteUserId(id);
		List<Users> user = usersService.findAll();
		model.addAttribute("users", user);
		return "redirect:/admin/account";
	}

	@RequestMapping("/user/new")
	public String newUser(Model model) {
		return "redirect:/admin/account";
	}

	@RequestMapping("/user/search")
	public String searchUsers(Model model, @RequestParam("search") String search) {
		if (search.isEmpty()) {
			model.addAttribute("SearchUsers", "Vui lòng nhập tên users cần tìm !");
			List<Users> user = usersService.findAll();
			model.addAttribute("users", user);
			return "page/account_admin";
		} else {
			List<Users> user = userrepo.findAllByNameLike(search);
			model.addAttribute("users", user);
			return "page/account_admin";
		}
	}

	@RequestMapping("/category/search")
	public String searchCategorys(Model model, @RequestParam("search") String search) {
		if (search.isEmpty()) {
			model.addAttribute("SearchCate", "Vui lòng nhập tên thể loại cần tìm !");
			List<Category> category = categoryService.findAll();
			model.addAttribute("categorys", category);
			return "page/category_admin";
		} else {
			List<Category> cate = categoryrepo.findAllByNameLike(search);
			model.addAttribute("categorys", cate);
			return "page/category_admin";

		}
	}

	@RequestMapping("/category/create")
	public String createCategory(Model model, @ModelAttribute("categorys") Category category) {
		categoryService.createCategory(category);
		model.addAttribute("message", "Thêm mới thành công!");
		return "redirect:/admin/category";

	}

	@RequestMapping("/category/update")
	public String updateCategory(Model model, @ModelAttribute("category") Category category) {
		System.out.println("user update:" + category);
		categoryService.update(category);
		model.addAttribute("categorys", category);
		model.addAttribute("message", "Cập nhật thành công!");
		return "redirect:/admin/category";
	}

	@RequestMapping("/category/edit")
	public String editCategory(Model model, @RequestParam("id") int id) {
		Optional<Category> cate = categoryService.findById(id);
		model.addAttribute("categorys", categoryService.findAll());
		model.addAttribute("category", cate.orElse(new Category()));
		return "page/category_admin";
	}

	@RequestMapping("/category/new")
	public String newCategory(Model model) {

		return "redirect:/admin/category";
	}

	@RequestMapping("/category/delete/{id}")
	public String DeleteCategoryId(Model model, @PathVariable("id") int id) {
		categoryService.deleteCategoryId(id);
		return "redirect:/admin/category";
	}

}