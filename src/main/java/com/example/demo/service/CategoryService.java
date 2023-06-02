package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Category;
import com.example.demo.entity.Users;
import com.example.demo.repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository cateRepo;
    
    public List<Category> findAll(){
        return cateRepo.findAll();
    }
    
	public void save(Category cate) {
		try {
			cateRepo.save(cate);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	public void findById(int id) {
		try {
			cateRepo.findById((id));
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	public void update(Category cate) {
		try {
			cateRepo.save(cate);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
