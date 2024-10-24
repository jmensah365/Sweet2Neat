package com.skillstorm.project_one.Services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.project_one.Repositories.CandyRepo;
import com.skillstorm.project_one.Repositories.FavoriteCandiesRepo;

import jakarta.transaction.Transactional;

import com.skillstorm.project_one.Models.Candy;
import com.skillstorm.project_one.Models.FavoriteCandies;

@Service
public class FavoriteCandiesService {
    @Autowired
    private FavoriteCandiesRepo favoriteCandiesRepo;

    @Autowired
    private CandyRepo candyRepository;

    //get methods
    public List<FavoriteCandies> getAllFavorites(){
        return favoriteCandiesRepo.findAll();
    }

    public FavoriteCandies addFavorite(Integer candyId){
        if(favoriteCandiesRepo.existsByCandyId(candyId)) {
            System.out.println("Candy is already in favorites");
        }

        Candy candy = candyRepository.findById(candyId).orElseThrow(() -> new NoSuchElementException("Candy not found"));
        FavoriteCandies favoriteCandy = new FavoriteCandies();
        favoriteCandy.setCandy(candy);
        return favoriteCandiesRepo.save(favoriteCandy);
        
    }

    @Transactional
    public void deleteFavoriteCandyById(Integer id){
        
        // Delete by candyId
        favoriteCandiesRepo.deleteByCandyId(id);
    }
    

    
}
