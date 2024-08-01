package com.skillstorm.project_one.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.project_one.DTOs.StockDTO;
import com.skillstorm.project_one.Models.Candy;
import com.skillstorm.project_one.Models.Stock;
import com.skillstorm.project_one.Models.Warehouse;
import com.skillstorm.project_one.Repositories.CandyRepo;
import com.skillstorm.project_one.Repositories.StockRepo;
import com.skillstorm.project_one.Repositories.WarehouseRepo;

@Service
public class StockService {
    @Autowired
    private StockRepo stockRepo;

    @Autowired
    private CandyRepo candyRepo;

    @Autowired
    private WarehouseRepo warehouseRepo;


    public List<StockDTO> getAllStocksDTO(){
        List<StockDTO> dtos = new ArrayList<>();
        for (Stock stock : stockRepo.findAll()) {
            dtos.add(convertToDTO(stock));
        }
        return dtos;
    }


    public List<StockDTO> getStocksByWarehouse(Integer warehouseId){
        List<StockDTO> dtos = new ArrayList<>();
        for (Stock stock : stockRepo.findByWarehouseId(warehouseId)){
            dtos.add(convertToDTO(stock));
        }
        return dtos;
    }


    private StockDTO convertToDTO(Stock stock) {
        StockDTO dto = new StockDTO();
        dto.setId(stock.getId());
        dto.setCandyId(stock.getCandyId());
        dto.setWarehouseId(stock.getWarehouseId());
        dto.setQuantity(stock.getQuantity());
        return dto;
    }


    
    public Stock createStock(StockDTO stockDTO){
        Candy candy = candyRepo.findById(stockDTO.getCandyId()).orElseThrow(() -> new NoSuchElementException("Candy not found"));
        Warehouse warehouse = warehouseRepo.findById(stockDTO.getWarehouseId()).orElseThrow(() -> new NoSuchElementException("Warehouse not found"));
        
        Stock stock = new Stock();
        stock.setId(stock.getId());
        stock.setCandy(candy);
        stock.setWarehouse(warehouse);
        stock.setQuantity(stockDTO.getQuantity());
        warehouse.setCurrentStock(warehouse.getCurrentStock() + stockDTO.getQuantity());
        warehouseRepo.save(warehouse);
        return stockRepo.save(stock);
    }

    public void updateStock(int id, StockDTO stockDTO){
        if(!stockRepo.existsById(id)) throw new NoSuchElementException("Stock with id " + id + " does not exits");
        Stock existingStock = stockRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Stock could not be found"));
        int oldQuantity = existingStock.getQuantity();
        
        Candy candy = candyRepo.findById(stockDTO.getCandyId()).orElseThrow(() -> new NoSuchElementException("Candy not found"));
        Warehouse warehouse = warehouseRepo.findById(stockDTO.getWarehouseId()).orElseThrow(() -> new NoSuchElementException("Warehouse not found"));
        
        existingStock.setCandy(candy);
        existingStock.setWarehouse(warehouse);
        existingStock.setQuantity(stockDTO.getQuantity());

        int quantityDifference = stockDTO.getQuantity() - oldQuantity;
        warehouse.setCurrentStock(quantityDifference + warehouse.getCurrentStock());
        warehouseRepo.save(warehouse);
        
        stockRepo.save(existingStock);
    }

    public void deleteStock(int id){
        Stock existingStock = stockRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Stock could not be found"));
        Warehouse warehouse = existingStock.getWarehouse();

        warehouse.setCurrentStock(warehouse.getCurrentStock() - existingStock.getQuantity());
        warehouseRepo.save(warehouse);
        
        stockRepo.deleteById(id);
    }


}
