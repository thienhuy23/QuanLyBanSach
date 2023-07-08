package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Bill;
import com.example.demo.repository.BillRepository;

@Service
public class BillService {
    @Autowired
    BillRepository repo;

    public Bill save(Bill bill){
        return repo.save(bill);
    }
    public List<Bill> findAll(){
        return repo.findAll();
    }
    public List<Bill> findAllByUserId(int id){
        return repo.findAllByUserId(id);
    }
}
