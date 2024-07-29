package com.skillstorm.project_one.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skillstorm.project_one.Models.Stock;
import com.skillstorm.project_one.Models.Warehouse;

import java.util.List;


public interface StockRepo extends JpaRepository<Stock, Integer>{
    Stock findByWarehouse(Warehouse warehouse);

    @Query("SELECT s FROM Stock s WHERE s.warehouse.id = :warehouseId")
    List<Stock> findByWarehouseId(@Param("warehouseId") Integer warehouseId);
    
}
