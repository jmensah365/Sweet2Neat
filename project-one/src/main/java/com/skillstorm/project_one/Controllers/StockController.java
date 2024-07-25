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
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.project_one.Models.Stock;
import com.skillstorm.project_one.Models.Warehouse;
import com.skillstorm.project_one.Services.StockService;

@RestController
@RequestMapping("/stock")
public class StockController {

    private StockService service;

    public StockController(StockService service){
        this.service = service;
    }
    
    
    @GetMapping
    public Iterable<Stock> getAllStocks() {
        return service.getAllStocks();
    }

    // @GetMapping("/warehouse/{warehouseId}")
    // public Iterable<Stock> getStocksByWarehouse(@PathVariable Integer warehouse) {
    //     return service.getStocksByWarehouse(warehouse);
    // }

    @PostMapping
    public Stock createStock(@RequestBody Stock stock) {
        return service.createStock(stock);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateStock(@PathVariable Integer id, @RequestBody Stock stock) {
        service.updateStock(id, stock);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Integer id) {
        service.deleteStock(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
