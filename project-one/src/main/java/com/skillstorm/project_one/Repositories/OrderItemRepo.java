package com.skillstorm.project_one.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillstorm.project_one.Models.OrderItem;

public interface OrderItemRepo extends JpaRepository<OrderItem, Integer> {
    
}
