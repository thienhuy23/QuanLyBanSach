package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
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
	@NotNull(message = "Vui lòng nhập tên")
	@Column
	String name;
	@NotNull(message = "Vui lòng nhập mật khẩu")
	@Column
	String password;
	@NotNull(message = "Vui lòng nhập email")
	@Email(message = "Email không hợp lệ")
	@Column
	String email;
	@NotNull(message = "Vui lòng nhập sđt ")
	@Column
	String phone;
	@NotNull(message = "Vui chọn vai trò")
	@Column
	Boolean role;
}
