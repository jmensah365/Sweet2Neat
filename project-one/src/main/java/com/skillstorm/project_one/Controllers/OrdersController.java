package com.skillstorm.project_one.Controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.project_one.Models.Candy;
import com.skillstorm.project_one.Models.Orders;
import com.skillstorm.project_one.Services.OrdersService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/orders")
public class OrdersController {
    
    private OrdersService service;

    public OrdersController(OrdersService service){
        this.service = service;
    }

    @GetMapping
    public Iterable<Orders> findAllOrders(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Orders> findOrdersById(@PathVariable int id){
        return service.getOrdersById(id);
    }

    @GetMapping("/getByStatus")
    public Iterable<Orders> findOrdersByStatus(@RequestParam boolean status){
        return service.getOrdersByStatus(status);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Orders createOrders (@RequestBody Orders orders) {
        return service.createOrders(orders);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orders> updateOrders(@PathVariable int id, @RequestBody Orders orders) {
        service.updateOrders(id, orders);
        return new ResponseEntity<>(HttpStatus.OK);
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable int id){
        service.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } 
    
}
