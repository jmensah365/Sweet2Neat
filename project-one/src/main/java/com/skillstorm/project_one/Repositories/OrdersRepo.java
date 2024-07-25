package com.skillstorm.project_one.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillstorm.project_one.Models.Orders;

public interface OrdersRepo extends JpaRepository<Orders, Integer> {
    
}
