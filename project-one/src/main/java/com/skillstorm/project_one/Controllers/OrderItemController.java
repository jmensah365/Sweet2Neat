package com.skillstorm.project_one.Controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.project_one.Models.OrderItem;
import com.skillstorm.project_one.Services.OrderItemService;
import org.springframework.web.bind.annotation.PutMapping;


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
    public Iterable<OrderItem> findAll(){
        return service.findAll();
    }

    // Endpoint to retrieve an order item by its ID
    @GetMapping("/{id}")
    public Optional<OrderItem> getOrderItemById(@PathVariable int id){
        return service.getOrderItemById(id);
    }

    //Endpoint to retrieve an order item by its orderId
    @GetMapping("/order/{orderId}")
    public Optional<OrderItem> getOrderItemsByOrderId(@PathVariable int orderId){
        return service.getOrderItemsByOrderId(orderId);
    }

    // Endpoint to add a new order item
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public OrderItem createOrderItem(@RequestBody OrderItem orderItem){
        return service.createOrderItem(orderItem);
    }

    // Endpoint to update an existing order item
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOrderItem (@PathVariable int id, @RequestBody OrderItem orderItem) {
        service.updateOrderItem(id, orderItem);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Endpoint to delete an order item by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem (@PathVariable int id) {
        service.deleteOrderItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
