package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.entity.Supplier;
import com.example.demo.repository.SupplierRepository;



@Service
public class SupplierService {
	@Autowired
	SupplierRepository supplierRepo;
	
	public List<Supplier> findAll() {
		return supplierRepo.findAll();
	}
	public Optional<Supplier> findById(Integer id) {
		return supplierRepo.findById(id);
	}

	public Supplier save(Supplier entity) {
		return supplierRepo.save(entity);
	}
	
	public void deleteById(Integer id) {
		supplierRepo.deleteById(id);
    } 
	
	
}