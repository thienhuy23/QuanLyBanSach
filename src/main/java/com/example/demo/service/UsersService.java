package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.repository.UsersRepository;

@Service
public class UsersService {
	@Autowired
	UsersRepository usersRepo;

	public Optional<Users> findById(int profileid) {
		return usersRepo.findById(profileid); // 1 object Book
	}

	public List<Users> findAll() {
		return usersRepo.findAll();
	}

	public List<Users> findListExpect(int id) {
		return usersRepo.findAll().stream().filter(s -> s.getId() != id).collect(Collectors.toList());
	}

	public void update(Users user) {
		try {
			usersRepo.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}


}
