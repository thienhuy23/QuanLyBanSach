package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.print.attribute.standard.PageRanges;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;

import jakarta.transaction.Transactional;

@Service
public class BookService {
	@Autowired
	BookRepository bookRepo;
	
	public Optional<Book> findById(int bookId) {
		return bookRepo.findById(bookId); // 1 object Book
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
	@Transactional
	public Page<Book> getPagination(int offset, int limit){
		return bookRepo.findAll(PageRequest.of(offset, limit));
	}
	public long getCount(){
		return bookRepo.count();
	}

	public List<Book> findByListId(List<Integer> list){
		return bookRepo.findByListId(list);
	}
	
	public  Book save(Book book ){
		return bookRepo.save(book);
	}
}
