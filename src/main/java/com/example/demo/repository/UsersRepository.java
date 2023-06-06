package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Users;
@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

	Users findByEmail(String username);
	@Query("SELECT u FROM Users u WHERE u.name LIKE %?1% ")
	List<Users> findAllByNameLike(Optional<String> key);
}
