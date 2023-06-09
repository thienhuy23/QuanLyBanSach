package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	@Query("SELECT u FROM Category u WHERE u.name LIKE %?1% ")
	List<Category> findAllByNameLike(Optional<String> key);
	

}
