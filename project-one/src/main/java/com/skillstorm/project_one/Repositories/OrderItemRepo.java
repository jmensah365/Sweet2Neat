package com.skillstorm.project_one.Repositories;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skillstorm.project_one.Models.OrderItem;

public interface OrderItemRepo extends JpaRepository<OrderItem, Integer> {
    @Query("SELECT oi FROM OrderItem oi WHERE oi.orders.id = :orderId")
    List<OrderItem> findByOrderId(@Param("orderId") Integer orderId);
}
