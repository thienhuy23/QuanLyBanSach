package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    int id;

    @Column
    String receivePlace;

    @Column
    Date date=new Date();
    
    @Column
    double sum;

    @ManyToOne
	@JoinColumn(name="user_id")
	@JsonIgnore
	Users user;

    @OneToMany(mappedBy = "bill",fetch=FetchType.LAZY)
	// @JsonIgnore
	List<Bill_detail> bill_details;


    @ManyToOne
	@JoinColumn(name="status_id")
	@JsonIgnore
	Status status;
}
