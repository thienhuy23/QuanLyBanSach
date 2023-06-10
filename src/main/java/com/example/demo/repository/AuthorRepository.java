package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Author;
import com.example.demo.entity.Supplier;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>{
	
	@Query("SELECT a FROM Author a WHERE a.name LIKE %?1%")
	List<Author> findAllByNameLike(Optional<String> name);
	
	@Query("SELECT s FROM Supplier s WHERE s.name LIKE %?1% ORDER BY s.name ASC")
	Page<Author> findAllByNameLikePage(Optional<String> name,PageRequest pageable);

}
