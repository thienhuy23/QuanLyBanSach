package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
     @RequestMapping("/user_1")
     public String user() {
    	 return "page/user";
     }
} 
 