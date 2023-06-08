package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Users;
import com.example.demo.entity.UsersDetail;
@Repository
public interface UsersDetailRepository extends JpaRepository<UsersDetail, Integer> {

	UsersDetail findByEmail(String username);
	@Query("SELECT u FROM Users u WHERE u.name LIKE %?1% ")
	List<UsersDetail> findAllByNameLike(Optional<String> key);
}
