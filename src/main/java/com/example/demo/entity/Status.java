package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Status {
    @Id
    @Column
    int id;
    @Column
    String status;

    @OneToMany(mappedBy = "status",fetch=FetchType.LAZY)
	@JsonIgnore
	List<Bill> bills;


}
