package com.skillstorm.project_one.Services;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillstorm.project_one.Models.Orders;
import com.skillstorm.project_one.Repositories.OrdersRepo;

@Service
public class OrdersService {
    
    private OrdersRepo repo;

    public OrdersService(OrdersRepo repo) {
        this.repo = repo;
    }

    public Iterable<Orders> findAll(){
        return repo.findAll();
    }

    public Optional<Orders> getOrdersById(int id){
        return repo.findById(id);
    }

    public Iterable<Orders> getOrdersByStatus(Boolean status){
        return repo.findByStatus(status);
    }

    public Orders createOrders(Orders orders){
        return repo.save(orders);
    }

    public void updateOrders(int id, Orders order){
        if(!repo.existsById(id)) throw new NoSuchElementException("Order with id " + id + " does not exist");
        order.setId(id);
        // order.setCustomerName(name);
        // order.setOrderDate(orderDate);
        // order.setStatus(status);
        repo.save(order);
    }

    public void deleteOrder(int id){
        repo.deleteById(id);
    }
}
