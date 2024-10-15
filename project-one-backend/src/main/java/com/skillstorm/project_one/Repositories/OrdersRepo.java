package com.skillstorm.project_one.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillstorm.project_one.Models.Orders;
import java.util.List;


public interface OrdersRepo extends JpaRepository<Orders, Integer> {
    List<Orders> findByStatus(String status);

    List<Orders> findAllByOrderByIdAsc();
}
