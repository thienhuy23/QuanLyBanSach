package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	BookRepository bookRepo;
	
	public Optional<Book> findById(int bookId) {
		return bookRepo.findById(bookId);
	}
}
