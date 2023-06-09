package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Status;
import com.example.demo.repository.StatusRepository;

@Service
public class StatusService {
    @Autowired
    StatusRepository repo;

    public List<Status> findAll(){
        return repo.findAll();
    }
}
