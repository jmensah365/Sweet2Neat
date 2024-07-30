package com.skillstorm.project_one.Services;

import java.util.NoSuchElementException;
import java.util.Optional;

import com.skillstorm.project_one.Models.Orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skillstorm.project_one.Repositories.OrdersRepo;
import com.skillstorm.project_one.Models.Candy;
import com.skillstorm.project_one.Models.OrderItem;
import com.skillstorm.project_one.Repositories.CandyRepo;
import com.skillstorm.project_one.Repositories.OrderItemRepo;

@Service
public class OrderItemService {
    @Autowired
    private OrdersRepo orderRepo;

    @Autowired
    private CandyRepo candyRepo;
    
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

    public OrderItem createOrderItem(OrderItem orderItemRequest){
        Candy candy = candyRepo.findById(orderItemRequest.getCandyId()).orElseThrow(() -> new NoSuchElementException("Candy does not exist"));
        Orders order = orderRepo.findById(orderItemRequest.getOrderId()).orElseThrow(() -> new NoSuchElementException("Order does not exist"));
        
        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItem.getId());
        orderItem.setCandy(candy);
        orderItem.setOrder(order);
        orderItem.setPrice(orderItem.getPrice());
        orderItem.setQuantity(orderItem.getQuantity());
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
