package com.skillstorm.project_one.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillstorm.project_one.Models.Candy;

public interface CandyRepo extends JpaRepository<Candy, Integer>{
    
}
