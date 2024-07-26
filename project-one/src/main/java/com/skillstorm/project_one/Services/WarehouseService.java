package com.skillstorm.project_one.Services;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.project_one.Models.Warehouse;
import com.skillstorm.project_one.Repositories.StockRepo;
import com.skillstorm.project_one.Repositories.WarehouseRepo;

@Service
public class WarehouseService {
    //Injecting 
    @Autowired
    private StockRepo stockRepo;
    @Autowired
    private WarehouseRepo warehouseRepo;


    //For get mappings
    public Iterable<Warehouse> findAll(){
        return warehouseRepo.findAll();
    }

    //For post mappings
    public Warehouse createWarehouse(Warehouse warehouse){
        Warehouse savedWarehouse = warehouseRepo.save(warehouse);
        //updateStockForWarehouse(savedWarehouse);
        return savedWarehouse;
    }

    //For put mappings
    public void updateWarehouse(int id, Warehouse warehouse){
        if(!warehouseRepo.existsById(id)) throw new NoSuchElementException("Warehouse with id " + id + " does not exist");
        warehouse.setId(id);
        warehouseRepo.save(warehouse);
    }


    //For delete mappings
    public void deleteWarehouse(int id){
        warehouseRepo.deleteById(id);
    }
}
