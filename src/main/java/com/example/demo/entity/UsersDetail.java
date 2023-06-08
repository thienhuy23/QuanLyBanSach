package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name ="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDetail implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	int id; 
	@Column
	@NotEmpty(message ="{NotEmpty.users.name}")
	String name;
	@Column
	@NotEmpty(message ="{NotEmpty.users.password}")
	String password;
	@Column
	@NotEmpty(message ="{NotEmpty.users.email}")
	@Email(message = "Email.users.email")
	String email;
	@Column
	@NotEmpty(message ="{NotEmpty.users.phone}")
	String phone;
	
	@NotNull(message = "Vui chọn vai trò")
	@Column
	Boolean role;

	// @OneToMany(mappedBy = "user",fetch=FetchType.LAZY)
	// @JsonIgnore
	// List<Bill> bills;
}
