package com.skillstorm.project_one.Services;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.project_one.Models.Stock;
import com.skillstorm.project_one.Models.Warehouse;
import com.skillstorm.project_one.Repositories.StockRepo;
import com.skillstorm.project_one.Repositories.WarehouseRepo;

/**
 * Service class for managing warehouse-related operations.
 */
@Service
public class WarehouseService {

    // Injecting Stock and Warehouse repo dependencies
    @Autowired
    private StockRepo stockRepo;
    @Autowired
    private WarehouseRepo warehouseRepo;

    /**
     * Retrieve all Warehouse entities.
     * @return Iterable of Warehouse entities
     */
    public Iterable<Warehouse> findAll() {
        return warehouseRepo.findAllByOrderByIdAsc();
    }

    /**
     * Retrieve a Warehouse by its ID.
     * @param id The ID of the Warehouse
     * @return Optional containing the Warehouse entity, if found
     */
    public Optional<Warehouse> findById(int id) {
        return warehouseRepo.findById(id);
    }

    /**
     * Create a new Warehouse entity and update its stock information.
     * @param warehouse The Warehouse entity to create
     * @return The created Warehouse entity
     */
    public Warehouse createWarehouse(Warehouse warehouse) {
        Warehouse savedWarehouse = warehouseRepo.save(warehouse);
        updateStockForWarehouse(savedWarehouse);
        return savedWarehouse;
    }

    /**
     * Update an existing Warehouse entity.
     * @param id The ID of the existing Warehouse 
     * @param warehouse The Warehouse entity with updated information
     */
    public void updateWarehouse(int id, Warehouse warehouse) {
        Warehouse existingWarehouse = warehouseRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Warehouse not found"));

        existingWarehouse.setLocation(warehouse.getLocation());
        existingWarehouse.setCapacity(warehouse.getCapacity());
        Warehouse updatedWarehouse = warehouseRepo.save(existingWarehouse);
        updateStockForWarehouse(updatedWarehouse);
    }

    /**
     * Delete a Warehouse by its ID.
     * @param id The ID of the Warehouse entity to delete
     */
    public void deleteWarehouse(int id) {
        warehouseRepo.deleteById(id);
    }

    /**
     * Update the current stock of a Warehouse based on its associated stocks.
     * @param warehouse The Warehouse entity to update
     */
    public void updateStockForWarehouse(Warehouse warehouse) {
        Iterable<Stock> stocks = stockRepo.findByWarehouse(warehouse);
        int totalQuantity = 0;
        for (Stock stock : stocks) {
            totalQuantity += stock.getQuantity();
        }
        warehouse.setCurrentStock(totalQuantity);
        warehouseRepo.save(warehouse);
    }
}
