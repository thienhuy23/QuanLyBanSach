package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
	public List<Book> findAll(){
		return bookRepo.findAll();
	}
	public List<Book> findListExpect(int id){
		return bookRepo.findAll()
						.stream()
						.filter(s->s.getId()!=id)
						.collect(Collectors.toList());
	}
}
