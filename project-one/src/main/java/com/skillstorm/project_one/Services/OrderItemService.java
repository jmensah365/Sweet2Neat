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


//service class to manage order information
@Service
public class OrderItemService {
    //Injecting repo dependencies
    @Autowired
    private OrdersRepo orderRepo;

    @Autowired
    private CandyRepo candyRepo;
    
    @Autowired
    private OrderItemRepo repo;


    // Method to fetch all order items
    public List<OrderItemDTO> findAll(){
        List<OrderItemDTO> dtos = new ArrayList<>();
        for (OrderItem orderItem : repo.findAll()) {
            dtos.add(convertToDTO(orderItem));
        }
        return dtos;
    }


    //Method to fetch order items by its order id
    public List<OrderItemDTO> findByOrderId(Integer id){
        List<OrderItemDTO> dtos = new ArrayList<>();
        for (OrderItem orderItem : repo.findByOrderId(id)) {
            dtos.add(convertToDTO(orderItem));
        }
        return dtos;
    }

    //converting order items to a DTO
    private OrderItemDTO convertToDTO(OrderItem orderItem) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setId(orderItem.getId());
        dto.setCandyId(orderItem.getCandyId());
        dto.setOrderId(orderItem.getOrderId());
        dto.setPrice(orderItem.getPrice());
        dto.setQuantity(orderItem.getQuantity());
        return dto;
    }


    //Method to create a new order item
    public OrderItem createOrderItem(OrderItemDTO orderItemRequest){
        Candy candy = candyRepo.findById(orderItemRequest.getCandyId()).orElseThrow(() -> new NoSuchElementException("Candy does not exist"));
        Orders order = orderRepo.findById(orderItemRequest.getOrderId()).orElseThrow(() -> new NoSuchElementException("Order does not exist"));
        
        OrderItem orderItem = new OrderItem();
        orderItem.setOrders(order);
        orderItem.setCandy(candy);
        orderItem.setPrice(orderItemRequest.getPrice());
        orderItem.setQuantity(orderItemRequest.getQuantity());
        return repo.save(orderItem);
    }

    //Method to update an exisitng order item
    public void updateOrderItem(int id, OrderItemDTO orderItemDto){
        if (orderItemDto.getId() == null){
            throw new NoSuchElementException("Sorry that id does not exist");
        }
        Candy candy = candyRepo.findById(orderItemDto.getCandyId()).orElseThrow(() -> new NoSuchElementException("Candy does not exist"));
        Orders order = orderRepo.findById(orderItemDto.getOrderId()).orElseThrow(() -> new NoSuchElementException("Order does not exist"));
        OrderItem orderItem = new OrderItem();
        orderItem.setId(id);
        orderItem.setOrders(order);
        orderItem.setCandy(candy);
        orderItem.setPrice(orderItemDto.getPrice());
        orderItem.setQuantity(orderItemDto.getQuantity());
        repo.save(orderItem);
    }

    //Method to delete an order item using its id
    public void deleteOrderItem(int id){
        repo.deleteById(id);
    }
}
