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
import jakarta.validation.constraints.Size;
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
	@NotEmpty(message = "Vui lòng nhập tên")
	String name; 
	@Column
	@NotEmpty(message = "Vui lòng nhập mật khẩu")
	@Size( min = 6 , message ="Mật khẩu phải tối thiểu 6 ký tự")
	String password; 
	@Column
	@NotEmpty(message = "Vui lòng nhập email")
	@Email(message = "Email không hợp lệ")
	String email; 
	@Column
	@NotEmpty(message = "Vui lòng nhập sđt")
	@Size( max = 11 , message ="Số điện thoại không hợp lệ")
	String phone;
	@Column
	@NotNull(message = "Vui chọn vai trò")
	Boolean role;
}
