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

import java.io.Serializable;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	int id;
	@Column
	@NotEmpty(message = "{NotEmpty.users.name}")
	String name;
	@Column
	@NotEmpty(message = "{NotEmpty.users.password}")
	String password;
	@Column
	@NotEmpty(message = "{NotEmpty.users.email}")
	@Email(message = "Email.users.email")
	String email;
	@Column
	@NotEmpty(message = "{NotEmpty.users.phone}")
	String phone;

<<<<<<< HEAD
	
=======
>>>>>>> origin/master
	@Column
	Boolean role;

	/*
	 * @OneToMany(mappedBy = "user",fetch=FetchType.LAZY)
	 * 
	 * @JsonIgnore List<Bill> bills;
	 */
//	@OneToMany(mappedBy = "user",fetch=FetchType.LAZY)
//	@JsonIgnore
//	List<Bill> bills;

}
