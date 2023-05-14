package com.example.demo.controller;

import java.util.Optional;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
	
	@GetMapping(value = {"/{url}","/"})
	public String home(@PathVariable Optional<String> url) {
		return "page/"+(url.isPresent() ? url.get():"home");
	}
}
