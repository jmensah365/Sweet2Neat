package com.skillstorm.project_one.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skillstorm.project_one.Models.FavoriteCandies;
import java.util.List;


public interface FavoriteCandiesRepo extends JpaRepository<FavoriteCandies, Integer>{
    List<FavoriteCandies> findByCandyId(Integer candyId);
    boolean existsByCandyId(Integer id);

    @Modifying
    @Query("DELETE FROM FavoriteCandies f WHERE f.candy.id = :id")
    void deleteByCandyId(@Param("id") Integer id);
}
