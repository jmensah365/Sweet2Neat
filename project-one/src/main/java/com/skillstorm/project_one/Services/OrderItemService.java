package com.skillstorm.project_one.Services;

import java.util.Optional;

import com.skillstorm.project_one.Models.Orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skillstorm.project_one.Repositories.OrdersRepo;

import com.skillstorm.project_one.Models.OrderItem;
import com.skillstorm.project_one.Repositories.OrderItemRepo;

@Service
public class OrderItemService {
    @Autowired
    private OrdersRepo orderRepo;

    private OrderItemRepo repo;

    public OrderItemService(OrderItemRepo repo){
        this.repo = repo;
    }

    public Iterable<OrderItem> findAll(){
        return repo.findAll();
    }

    public Optional<OrderItem> getOrderItemById(Integer id){
        return repo.findById(id);
    }

    public Optional<OrderItem> getOrderItemsByOrderId(Integer orderId){
        Optional<Orders> orders = orderRepo.findById(orderId);
        return repo.findByOrders(orders);
    }

    public OrderItem createOrderItem(OrderItem orderItem){
        return repo.save(orderItem);
    }

    public void updateOrderItem(int id, OrderItem orderItem){
        orderItem.setId(id);
        repo.save(orderItem);
    }

    public void deleteOrderItem(int id){
        repo.deleteById(id);
    }
}
