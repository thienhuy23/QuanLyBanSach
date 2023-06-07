package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Bill_detail;

@Repository
public interface BillDetRepository extends JpaRepository<Bill_detail, Integer>{
	
}