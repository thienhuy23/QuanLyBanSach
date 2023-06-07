package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer>{
	
	@Query("SELECT s FROM Supplier s WHERE s.name LIKE %?1% ORDER BY s.name ASC")
	Page<Supplier> findAllByNameLikePage(Optional<String> name,PageRequest pageable);
	
	@Query("SELECT s FROM Supplier s WHERE s.name LIKE %?1%")
	List<Supplier> findAllByNameLike(Optional<String> name,Sort sort);
	
	@Query("SELECT s FROM Supplier s WHERE s.id = ?1")
	List<Supplier> findAllByID(Optional<Integer> id);
}
