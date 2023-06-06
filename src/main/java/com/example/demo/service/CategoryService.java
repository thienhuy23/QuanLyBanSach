package com.example.demo.service;

import java.util.List;
import java.util.Optional;

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
    
	public void createCategory(Category cate) {
		try {
			cateRepo.save(cate);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	public void findByIdCategory(int id) {
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
	public Optional<Category> findById(int id) {
		return cateRepo.findById(id);
	}
	
	public void deleteCategoryId(Integer id) {
		cateRepo.deleteById(id);
	}
}
