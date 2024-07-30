package com.skillstorm.project_one.Controllers;


import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.project_one.Models.Candy;
import com.skillstorm.project_one.Services.CandyService;

@CrossOrigin(origins = "http://localhost:5174")
@RestController
@RequestMapping("/candy")
public class CandyController {

    //Injecting CandyService dependency
    private CandyService service;
    public CandyController(CandyService service){
        this.service = service;
    }

    // Endpoint to retrieve all candies
    @GetMapping
    public Iterable<Candy> getAllCandy() {
        return service.findAll();
    }

    //Endpoint to retrieve candies by id
    @GetMapping("/{id}")
    public Optional<Candy> getCandyById(@PathVariable int id){
        return service.findById(id);
    }

    // Endpoint to add a new candy
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Candy createCandy(@RequestBody Candy candy) {
        return service.createCandy(candy);
    }

    // Endpoint to update an existing candy
    @PutMapping("/{id}")
    public ResponseEntity<Candy> updateCandy(@PathVariable int id, @RequestBody Candy candy) {
        service.updateCandy(id, candy);
        return new ResponseEntity<>(HttpStatus.OK);
        
    }

    // Endpoint to delete a candy by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandyById(@PathVariable int id){
        service.deleteCandy(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } 
    
}
