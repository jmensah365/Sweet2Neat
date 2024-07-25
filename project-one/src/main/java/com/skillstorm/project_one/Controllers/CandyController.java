package com.skillstorm.project_one.Controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/candy")
public class CandyController {

    private CandyService service;

    public CandyController(CandyService service){
        this.service = service;
    }

    @GetMapping
    public Iterable<Candy> getAllCandy() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Candy createCandy(@RequestBody Candy candy) {
        return service.createCandy(candy);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candy> updateWarehouse(@PathVariable int id, @RequestBody Candy candy) {
        service.updateCandy(id, candy);
        return new ResponseEntity<>(HttpStatus.OK);
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandyById(@PathVariable int id){
        service.deleteCandy(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } 
    
}
