package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.repository.AuthorRepository;

import jakarta.transaction.Transactional;

@Service
public class AuthorService{
	@Autowired
	AuthorRepository AuthorRep;
	
	public List<Author> findAll() {
		return AuthorRep.findAll();
	}
	public Optional<Author> findById(Integer id) {
		return AuthorRep.findById(id);
	}

	public Author save(Author entity) {
		return AuthorRep.save(entity);
	}
	
	public void deleteById(Integer id) {
		AuthorRep.deleteById(id);
    } 
	@Transactional
	public Page<Author> getPagination(int offset, int limit){
		return AuthorRep.findAll(PageRequest.of(offset, limit));
	}
	public long getCount(){
		return AuthorRep.count();
	}
}
