package com.skillstorm.project_one.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillstorm.project_one.Models.Candy;
import java.util.List;


public interface CandyRepo extends JpaRepository<Candy, Integer>{
    List<Candy> findByFlavor(String flavor);

    List<Candy> findByType(String type);

    List<Candy> findAllByOrderByIdAsc();
}
