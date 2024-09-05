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

/**
 * Service class for managing stock-related operations.
 */
@Service
public class StockService {

    // Autowire the stockRepo, candyRepo, and warehouseRepo for dependency injection
    @Autowired
    private StockRepo stockRepo;
    @Autowired
    private CandyRepo candyRepo;
    @Autowired
    private WarehouseRepo warehouseRepo;

    /**
     * Get all stock entries and convert them to StockDTO objects.
     * @return List of StockDTO objects
     */
    public List<StockDTO> getAllStocksDTO(){
        List<StockDTO> dtos = new ArrayList<>();
        for (Stock stock : stockRepo.findAll()) {
            dtos.add(convertToDTO(stock));
        }
        return dtos;
    }

    /**
     * Get stock entries by warehouse ID and convert them to StockDTO objects.
     * @param warehouseId The ID of the warehouse
     * @return List of StockDTO objects
     */
    public List<StockDTO> getStocksByWarehouse(Integer warehouseId){
        List<StockDTO> dtos = new ArrayList<>();
        for (Stock stock : stockRepo.findByWarehouseId(warehouseId)){
            dtos.add(convertToDTO(stock));
        }
        return dtos;
    }

    /**
     * Convert a Stock entity to a StockDTO object.
     * @param stock The Stock entity to convert
     * @return The converted StockDTO object
     */
    public StockDTO convertToDTO(Stock stock) {
        StockDTO dto = new StockDTO();
        dto.setId(stock.getId());
        dto.setCandyId(stock.getCandy().getCandyId());
        dto.setWarehouseId(stock.getWarehouse().getId());
        dto.setQuantity(stock.getQuantity());
        return dto;
    }

    /**
     * Create a new Stock entity based on a StockDTO object.
     * @param stockDTO The StockDTO object containing the data
     * @return The created Stock entity
     */
    public Stock createStock(StockDTO stockDTO){
        Candy candy = candyRepo.findById(stockDTO.getCandyId()).orElseThrow(() -> new NoSuchElementException("Candy not found"));
        Warehouse warehouse = warehouseRepo.findById(stockDTO.getWarehouseId()).orElseThrow(() -> new NoSuchElementException("Warehouse not found"));
        
        Stock stock = new Stock();
        stock.setCandy(candy);
        stock.setWarehouse(warehouse);
        stock.setQuantity(stockDTO.getQuantity());
        
        // Update the warehouse's current stock count
        warehouse.setCurrentStock(warehouse.getCurrentStock() + stockDTO.getQuantity());
        warehouseRepo.save(warehouse);
        
        return stockRepo.save(stock);
    }

    /**
     * Update an existing Stock entity based on a StockDTO object.
     * @param id The ID of the existing Stock entity
     * @param stockDTO The StockDTO object containing the updated data
     */
    public void updateStock(int id, StockDTO stockDTO){
        if(!stockRepo.existsById(id)) throw new NoSuchElementException("Stock with id " + id + " does not exist");
        Stock existingStock = stockRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Stock could not be found"));
        int oldQuantity = existingStock.getQuantity();
        
        Candy candy = candyRepo.findById(stockDTO.getCandyId()).orElseThrow(() -> new NoSuchElementException("Candy not found"));
        Warehouse warehouse = warehouseRepo.findById(stockDTO.getWarehouseId()).orElseThrow(() -> new NoSuchElementException("Warehouse not found"));
        
        existingStock.setCandy(candy);
        existingStock.setWarehouse(warehouse);
        existingStock.setQuantity(stockDTO.getQuantity());

        // Update the warehouse's current stock count based on the difference in quantity
        int quantityDifference = stockDTO.getQuantity() - oldQuantity;
        warehouse.setCurrentStock(warehouse.getCurrentStock() + quantityDifference);
        warehouseRepo.save(warehouse);
        
        stockRepo.save(existingStock);
    }

    /**
     * Delete a Stock entity by its ID.
     * @param id The ID of the Stock entity to delete
     */
    public void deleteStock(int id){
        Stock existingStock = stockRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Stock could not be found"));
        Warehouse warehouse = existingStock.getWarehouse();

        // Update the warehouse's current stock count after deletion
        warehouse.setCurrentStock(warehouse.getCurrentStock() - existingStock.getQuantity());
        warehouseRepo.save(warehouse);
        
        stockRepo.deleteById(id);
    }
}
