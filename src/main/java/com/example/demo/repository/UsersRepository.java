package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Users;
@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    
    @Query("select * from Users where email =:email and password = :password")
    public Users getOneUser(@Param("email") String email, @Param("password") String password);	
}
