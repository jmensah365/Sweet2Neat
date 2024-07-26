package com.skillstorm.project_one.Repositories;

import java.util.Optional;

import com.skillstorm.project_one.Models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import com.skillstorm.project_one.Models.OrderItem;

public interface OrderItemRepo extends JpaRepository<OrderItem, Integer> {
    Optional<OrderItem> findByOrders(Optional<Orders> orders);
}
