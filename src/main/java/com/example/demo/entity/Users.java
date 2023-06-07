package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
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
public class Users {
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
	
}
