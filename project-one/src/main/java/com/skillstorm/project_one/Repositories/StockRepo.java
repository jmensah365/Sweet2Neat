package com.skillstorm.project_one.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillstorm.project_one.Models.Stock;

public interface StockRepo extends JpaRepository<Stock, Integer>{
    
}
