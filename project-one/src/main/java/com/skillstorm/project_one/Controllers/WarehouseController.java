package com.skillstorm.project_one.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.project_one.Services.WarehouseService;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.skillstorm.project_one.Models.Warehouse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



//Linking frontend and backend using CORS
//@CrossOrigin(origins = "http://cim-frontend.s3-website-us-east-1.amazonaws.com")
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    //Injecting WarehouseService dependency
    private WarehouseService service;
    public WarehouseController(WarehouseService service){
        this.service = service;
    }


    
    //Endpoint to retrieve all warehouses
    @GetMapping
    public Iterable<Warehouse> getAllWarehouses() {
        return service.findAll();
    }


    //Endpoint to create a new warehouse
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Warehouse createWarehouse(@RequestBody Warehouse warehouse) {
        return service.createWarehouse(warehouse);
    }



    //Endpoint to update an existing warehouse
    @PutMapping("/{id}")
    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable int id, @RequestBody Warehouse warehouse) {
        service.updateWarehouse(id, warehouse);
        return new ResponseEntity<>(HttpStatus.OK);
        
    }



    //Endpoint to delete a warehouse by its id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarehouseById(@PathVariable int id){
        service.deleteWarehouse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    

}
