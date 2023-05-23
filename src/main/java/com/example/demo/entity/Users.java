package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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
	@NotEmpty(message ="{NotEmpty.student.name}")
	String name;
	@Column
	@NotEmpty(message ="{NotEmpty.student.password}")
	String password;
	@Column
	@NotEmpty(message ="{NotEmpty.student.email}")
	@Email(message = "Email.student.email")
	String email;
	@Column
	String phone;
	@Column
	Boolean role;
}
