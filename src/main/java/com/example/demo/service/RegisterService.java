package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.repository.RegisterRepository;

@Service
public class RegisterService {
	@Autowired
	RegisterRepository RegisterRepo;

	public Users save(Users entity) {
		return RegisterRepo.save(entity);
	}

}
