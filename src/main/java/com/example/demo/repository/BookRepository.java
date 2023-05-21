package com.example.demo.repository;

import java.util.List;

import javax.print.attribute.standard.PageRanges;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Book;
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
