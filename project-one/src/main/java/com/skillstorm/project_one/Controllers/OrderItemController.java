package com.skillstorm.project_one.Controllers;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.project_one.DTOs.OrderItemDTO;
import com.skillstorm.project_one.Models.OrderItem;
import com.skillstorm.project_one.Services.OrderItemService;
import org.springframework.web.bind.annotation.PutMapping;


//Linking frontend and backend using CORS
@CrossOrigin(origins = "http://cim-frontend.s3-website-us-east-1.amazonaws.com")
@RestController
@RequestMapping("/orderItem")
public class OrderItemController {

    // Injecting OrderItemService dependency
    private OrderItemService service;
    public OrderItemController(OrderItemService service){
        this.service = service;
    }


    // Endpoint to retrieve all order items
    @GetMapping
    public ResponseEntity<Iterable<OrderItemDTO>>findAll(){
        List<OrderItemDTO> orderItem = service.findAll();
        return new ResponseEntity<>(orderItem, HttpStatus.OK);
    }

    //Endpoint to retrieve an order item by its orderId
    @GetMapping("/order/{orderId}")
    public List<OrderItemDTO> getOrderItemsByOrderId(@PathVariable int orderId){
        return service.findByOrderId(orderId);
    }


    // Endpoint to add a new order item
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItemDTO orderItem){

        OrderItem createdOrderItem = service.createOrderItem(orderItem);
        return ResponseEntity.ok(createdOrderItem);
    }
    

    // Endpoint to update an existing order item
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOrderItem (@PathVariable Integer id, @RequestBody OrderItemDTO orderItemDto) {
        service.updateOrderItem(id, orderItemDto);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Endpoint to delete an order item by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem (@PathVariable int id) {
        service.deleteOrderItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
