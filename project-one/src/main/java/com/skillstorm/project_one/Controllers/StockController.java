package com.skillstorm.project_one.Controllers;


import java.util.List;

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

import com.skillstorm.project_one.DTOs.StockDTO;
import com.skillstorm.project_one.Models.Stock;
import com.skillstorm.project_one.Services.StockService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/stock")
public class StockController {

    //Injecting StockService dependency
    private StockService service;
    public StockController(StockService service){
        this.service = service;
    }
    
    //Endpoint to retrieve all stock items
    // @GetMapping
    // public Iterable<Stock> getAllStocks() {
    //     return service.getAllStocks();
    // }

    @GetMapping
    public ResponseEntity<List<StockDTO>> findAllStocks(){
        List<StockDTO> stocks = service.getAllStocksDTO();
        return new ResponseEntity<>(stocks, HttpStatus.OK);
    }

    @GetMapping("/warehouse/{warehouseId}")
    public ResponseEntity<List<StockDTO>> getStocksByWarehouse(@PathVariable int warehouseId) {
        List<StockDTO> stocks = service.getStocksByWarehouse(warehouseId);
        return new ResponseEntity<>(stocks, HttpStatus.OK);
    }

    // Endpoint to add a new stock item
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Stock> createStock(@Valid @RequestBody StockDTO stockDTO) {
        
        Stock stock = service.createStock(stockDTO);
        return new ResponseEntity<>(stock, HttpStatus.OK);
    }

    // Endpoint to update an existing stock item
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateStock(@PathVariable Integer id, @RequestBody Stock stock) {
        service.updateStock(id, stock);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Endpoint to delete a stock item by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Integer id) {
        service.deleteStock(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
