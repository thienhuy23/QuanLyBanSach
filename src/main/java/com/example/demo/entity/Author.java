package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
	
	@Id
	@Column
	@NotNull(message ="{NotNull.author.id}")
	int id;
	
	@Column
	@NotEmpty(message ="{NotEmpty.author.name}")
	String name;
	
	@OneToMany(mappedBy = "author",fetch = FetchType.LAZY)
	List<Book> books;
}
