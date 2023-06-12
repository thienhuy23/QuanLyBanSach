package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Book;
import com.example.demo.entity.UsersDetail;

import jakarta.transaction.Transactional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
	@Query("select b from Book b where b.id in (:list)")
	List<Book> findByListId(@Param("list") List<Integer> list);
	
	@Query("SELECT u FROM Book u WHERE u.name LIKE %?1% ")
	List<Book> findAllByNameLike(Optional<String> name);

	
	
	@Modifying
	@Transactional
	@Query(value = "insert into book(category_id, supplier_id, author_id, name, price, discount, published_year, number_page, describe) "
			       + "VALUES (:category,:supplier,:author,:name,:price,:discount,:published_year,:number_page,:describe)", nativeQuery = true)
	void saveBook(@Param("category") Optional<Integer> category_id, 
			@Param("supplier") Optional<Integer> supplier_id,
			@Param("author") Optional<Integer> author_id, 
			@Param("name") Optional<String> name,
			@Param("price") Optional<Double> price, 
			@Param("discount") Optional<Float> discount,
			@Param("published_year") Optional<Integer> published_year,
			@Param("number_page") Optional<Integer> number_page, 
			@Param("describe") Optional<String> describe);
}
