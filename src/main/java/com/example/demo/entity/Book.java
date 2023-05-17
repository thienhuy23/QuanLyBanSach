package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	int id;
	@Column
	String name;
	@Column
	double price;
	@Column
	float discount;
	@Column
	int published_year;
	@Column
	int number_page;
	@Column
	String describe;
	
	@OneToMany(mappedBy = "book",fetch=FetchType.LAZY)
	List<Image> images;
	
	@ManyToOne
	@JoinColumn(name = "author_id")
	Author author;
	
	@ManyToOne
	@JoinColumn(name = "supplier_id")
	Supplier supplier;
}
