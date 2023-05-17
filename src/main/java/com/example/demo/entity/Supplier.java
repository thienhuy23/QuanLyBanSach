package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
	@Id
	@Column
	int id;
	@Column
	public String name;
	
	@OneToMany(mappedBy = "supplier",fetch = FetchType.LAZY)
	List<Book> books;
}
