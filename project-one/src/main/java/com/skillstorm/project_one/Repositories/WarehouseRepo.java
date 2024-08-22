package com.skillstorm.project_one.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.skillstorm.project_one.Models.Warehouse;

public interface WarehouseRepo extends JpaRepository<Warehouse, Integer>{
    List<Warehouse> findAllByOrderByIdAsc();
}
