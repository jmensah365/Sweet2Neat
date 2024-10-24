package com.skillstorm.project_one.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.project_one.Services.FavoriteCandiesService;
import com.skillstorm.project_one.Models.Candy;
import com.skillstorm.project_one.Models.FavoriteCandies;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/favoriteCandies")
public class FavoriteCandiesController {
    
    @Autowired
    private FavoriteCandiesService favoriteCandiesService;

    //endpoint to retrieve all favorited candies
    @GetMapping
    public List<FavoriteCandies> getAllFavorites(){
        return favoriteCandiesService.getAllFavorites();
    }

    //endpoint to favorite a new candy
    @PostMapping("/{candyId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addCandy(@PathVariable Integer candyId){
        favoriteCandiesService.addFavorite(candyId);
    }

    @DeleteMapping("/candy/{id}")
    public ResponseEntity<Void> deleteFavoriteCandy(@PathVariable int id){
        favoriteCandiesService.deleteFavoriteCandyById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
