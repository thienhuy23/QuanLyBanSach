package com.example.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
	@Id
	@Column
	@NotNull(message ="{NotNull.supplier.id}")
	int id;
	@Column
	@NotEmpty(message ="{NotEmpty.supplier.name}")
	public String name;
	
	@OneToMany(mappedBy = "supplier",fetch = FetchType.LAZY)
	@JsonIgnore
	List<Book> books;
}
