package com.skillstorm.project_one.Controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.project_one.Models.Orders;
import com.skillstorm.project_one.Services.OrdersService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


//Linking frontend and backend using CORS
@CrossOrigin(origins = "http://cim-frontend.s3-website-us-east-1.amazonaws.com")
// @CrossOrigin(origins = "http://localhost:5174")
@RestController
@RequestMapping("/orders")
public class OrdersController {

    //Injecting OrderService dependency
    private OrdersService service;
    public OrdersController(OrdersService service){
        this.service = service;
    }



    //Endpoint to retrieve all orders
    @GetMapping
    public Iterable<Orders> findAllOrders(){
        return service.findAll();
    }

    //Endpoint to retrieve an order by its id
    @GetMapping("/{id}")
    public Optional<Orders> findOrdersById(@PathVariable int id){
        return service.getOrdersById(id);
    }

    //Endpoint to retrieve an order by its status
    @GetMapping("/getByStatus")
    public Iterable<Orders> findOrdersByStatus(@RequestParam String status){
        return service.getOrdersByStatus(status);
    }



    //Endpoint to add a new order
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Orders createOrders (@RequestBody Orders orders) {
        return service.createOrders(orders);
    }



    //Endpoint to update an existing order
    @PutMapping("/{id}")
    public ResponseEntity<Orders> updateOrders(@PathVariable int id, @RequestBody Orders orders) {
        service.updateOrders(id, orders);
        return new ResponseEntity<>(HttpStatus.OK);
        
    }


    
    //Endpoint to delete an existing order by its id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable int id){
        service.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } 
    
}
