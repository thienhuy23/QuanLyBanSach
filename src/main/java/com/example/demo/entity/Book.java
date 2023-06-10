package com.example.demo.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable{
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
	
	@Transient
	int quantity;

	@OneToMany(mappedBy = "book",fetch=FetchType.LAZY)
	// @JsonIgnore
	List<Image> images;
	
	@ManyToOne
	@JoinColumn(name = "author_id")
	@JsonIgnore
	Author author;
	
	@ManyToOne
	@JoinColumn(name = "supplier_id")
	@JsonIgnore
	Supplier supplier;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	@JsonIgnore
	Category category;

	@OneToMany(mappedBy = "book",fetch=FetchType.LAZY)
	@JsonIgnore
	List<Bill_detail> bill_details;

}
