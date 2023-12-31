package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Bill;
import com.example.demo.entity.Users;
import com.example.demo.service.BillService;
import com.example.demo.service.StatusService;
import com.example.demo.service.UsersService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/profile")
public class ProfileController {
	@Autowired
	UsersService usersService;
	@Autowired
	BillService billService;
	@Autowired
	StatusService statusService;

	@GetMapping({ "/", "" })
	public String getProfile(@RequestParam("profileId") int profileid,@RequestParam("status") Optional<Integer> status, Model model) {
		Optional<Users> users = usersService.findByIdProfile(profileid);
		List<Bill> bills= billService.findAllByUserId(profileid);
		model.addAttribute("status", statusService.findAll());
		int statudId = status.orElse(1);
		model.addAttribute("statusId", statudId);
		bills = bills.stream().filter(s->s.getStatus().getId()==statudId).toList();
		model.addAttribute("bills",bills);
		if (!users.isPresent()) {
			return "redirect:/error";
		}
		// if (users.get().getRole() == true) {
		// 	return "redirect:/error";
		// }
		System.out.println(users.get());
		model.addAttribute("users", users.get());
//		model.addAttribute("listUsers",usersService.findListExpect(profileid));
		return "page/profile";
	}

	@PostMapping("update")
	public String updateProfile(Model model, @Valid @ModelAttribute("users") Users user, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("message", "Một số trường không hợp lệ. Hãy sửa chữa chúng!");
		} else {
			System.out.println("user update:" + user);
			usersService.update(user);
			model.addAttribute("users", user);
			model.addAttribute("message", "Cập nhật thành công!");
		}
		return "page/profile";
	}

}
