package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Image;
import com.example.demo.repository.ImageRepository;
@Service
public class ImageService {
	@Autowired
	ImageRepository imgrepo;
	public List<Image> findAll() {
		return imgrepo.findAll();
	}
	public Optional<Image> findById(int id) {
		return imgrepo.findById(id);
	}
	public Image save(Image img) {
		return imgrepo.save(img);
	}
	public void deleteImageid(Integer id) {
		imgrepo.deleteById(id);
	}
}
