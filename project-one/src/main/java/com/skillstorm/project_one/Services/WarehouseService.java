package com.skillstorm.project_one.Services;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.project_one.Models.Stock;
import com.skillstorm.project_one.Models.Warehouse;
import com.skillstorm.project_one.Repositories.StockRepo;
import com.skillstorm.project_one.Repositories.WarehouseRepo;

@Service
public class WarehouseService {
    //Injecting Stock and Warehouse Repository dependency
    @Autowired
    private StockRepo stockRepo;
    @Autowired
    private WarehouseRepo warehouseRepo;


    //For get mappings
    public Iterable<Warehouse> findAll(){
        return warehouseRepo.findAll();
    }
    //For get mappings
    public Optional<Warehouse> findById(int id){
        return warehouseRepo.findById(id);
    }


    //For post mappings
    public Warehouse createWarehouse(Warehouse warehouse){
        Warehouse savedWarehouse = warehouseRepo.save(warehouse);
        updateStockForWarehouse(savedWarehouse);
        return savedWarehouse;
    }

    //For put mappings
    public void updateWarehouse(int id, Warehouse warehouse){
        Warehouse existingWarehouse = warehouseRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Warehouse not found"));

        existingWarehouse.setLocation(warehouse.getLocation());
        existingWarehouse.setCapacity(warehouse.getCapacity());
        existingWarehouse.setCurrentStock(warehouse.getCurrentStock());
        Warehouse updatWarehouse = warehouseRepo.save(existingWarehouse);
        updateStockForWarehouse(updatWarehouse);

    }


    //For delete mappings
    public void deleteWarehouse(int id){
        warehouseRepo.deleteById(id);
    }

    private void updateStockForWarehouse(Warehouse warehouse){
        //Retrieve the existing stock record for the warehouse, if there is any
        Stock stock = stockRepo.findByWarehouse(warehouse);
        if(stock != null){
            stock.setQuantity(warehouse.getCurrentStock());
            stockRepo.save(stock);
        } else {
            Stock newStock = new Stock();
            newStock.setWarehouse(warehouse);
            newStock.setQuantity(warehouse.getCurrentStock());
            stockRepo.save(newStock);
        }
    }
}
