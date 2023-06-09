package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill_detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    int id;
    @Column
    int quantity;

    @ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="book_id")
	// @JsonIgnore
	Book book;
    @Transient
    int book_id;

    // @Column(insertable=true, updatable=false)
    // int bill_id;

    @ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="bill_id",insertable = true)
	@JsonIgnore
	Bill bill;
}
