package com.example.demo.dto;

import com.example.demo.entity.Bill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailInfo {
	String from;
	String to;
	String subject;
	Bill body;

	public MailInfo(String to, String subject, Bill body) {
		this.from = "FPT Polytechnic <poly@fpt.edu.vn>";
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
}
