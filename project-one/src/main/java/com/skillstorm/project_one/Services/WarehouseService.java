package com.skillstorm.project_one.Services;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.skillstorm.project_one.Models.Warehouse;
import com.skillstorm.project_one.Repositories.WarehouseRepo;

@Service
public class WarehouseService {
    private WarehouseRepo repo;

    public WarehouseService(WarehouseRepo repo){
        this.repo = repo;
    }

    //For get mappings
    public Iterable<Warehouse> findAll(){
        return repo.findAll();
    }

    //For post mappings
    public Warehouse createWarehouse(Warehouse warehouse){
        return repo.save(warehouse);
    }

    //For put mappings
    public void updateWarehouse(int id, Warehouse warehouse){
        if(!repo.existsById(id)) throw new NoSuchElementException("Warehouse with id " + id + " does not exists");
        warehouse.setId(id);
        repo.save(warehouse);
    }


    //For delete mappings
    public void deleteWarehouse(int id){
        repo.deleteById(id);
    }
}
