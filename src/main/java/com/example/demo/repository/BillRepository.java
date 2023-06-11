package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Bill;
@Repository
public interface BillRepository extends JpaRepository<Bill,Integer> {
    @Query("select b from Bill b where b.user.id = :id")
    List<Bill> findAllByUserId(@Param("id") int id);
}
