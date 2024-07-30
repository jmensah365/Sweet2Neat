package com.skillstorm.project_one.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.skillstorm.project_one.Models.Orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skillstorm.project_one.Repositories.OrdersRepo;
import com.skillstorm.project_one.DTOs.OrderItemDTO;
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
    
    @Autowired
    private OrderItemRepo repo;


    public List<OrderItemDTO> findAll(){
        List<OrderItemDTO> dtos = new ArrayList<>();
        for (OrderItem orderItem : repo.findAll()) {
            dtos.add(convertToDTO(orderItem));
        }
        return dtos;
    }


    public List<OrderItemDTO> findByOrderId(Integer id){
        List<OrderItemDTO> dtos = new ArrayList<>();
        for (OrderItem orderItem : repo.findByOrderId(id)) {
            dtos.add(convertToDTO(orderItem));
        }
        return dtos;
    }

    private OrderItemDTO convertToDTO(OrderItem orderItem) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setId(orderItem.getId());
        dto.setCandyId(orderItem.getCandyId());
        dto.setOrderId(orderItem.getOrderId());
        dto.setPrice(orderItem.getPrice());
        dto.setQuantity(orderItem.getQuantity());
        return dto;
    }


    public OrderItem createOrderItem(OrderItemDTO orderItemRequest){
       // System.out.println("Looking up Candy with ID: " + orderItemRequest.getCandy().getCandyId());
        Candy candy = candyRepo.findById(orderItemRequest.getCandyId()).orElseThrow(() -> new NoSuchElementException("Candy does not exist"));
        //System.out.println("Looking up Order with ID: " + orderItemRequest.getOrders().getId());
        Orders order = orderRepo.findById(orderItemRequest.getOrderId()).orElseThrow(() -> new NoSuchElementException("Order does not exist"));
        
        OrderItem orderItem = new OrderItem();
        orderItem.setOrders(order);
        orderItem.setCandy(candy);
        orderItem.setPrice(orderItemRequest.getPrice());
        orderItem.setQuantity(orderItemRequest.getQuantity());
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
