package com.skillstorm.project_one.Services;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.skillstorm.project_one.Models.Stock;
import com.skillstorm.project_one.Models.Warehouse;
import com.skillstorm.project_one.Repositories.StockRepo;

@Service
public class StockService {
    
    private StockRepo repo;

    public StockService(StockRepo repo){
        this.repo = repo;
    }

    public Iterable<Stock> getAllStocks(){
        return repo.findAll();
    }

    // public Iterable<Stock> getStocksByWarehouse(Integer warehouse){
    //     return repo.findByWarehouseId(warehouse);
    // }

    public Stock createStock(Stock stock){
        return repo.save(stock);
    }

    public void updateStock(int id, Stock stock){
        if(!repo.existsById(id)) throw new NoSuchElementException("Stock with id " + id + " does not exits");
        stock.setId(id);
        repo.save(stock);
    }

    public void deleteStock(int id){
        repo.deleteById(id);
    }

}
