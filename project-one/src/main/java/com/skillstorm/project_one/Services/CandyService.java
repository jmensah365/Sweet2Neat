package com.skillstorm.project_one.Services;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillstorm.project_one.Models.Candy;
import com.skillstorm.project_one.Repositories.CandyRepo;

@Service
public class CandyService {
    
    private CandyRepo repo;

    public CandyService(CandyRepo repo){
        this.repo = repo;
    }

    //For get mappings
    public Iterable<Candy> findAll(){
        return repo.findAll();
    }

    public Optional<Candy> findById(int id){
        return repo.findById(id);
    }

    //For post mappings
    public Candy createCandy(Candy candy){
        return repo.save(candy);
    }

    //For put mappings
    public void updateCandy(int id, Candy candy){
        if(!repo.existsById(id)) throw new NoSuchElementException("Candy with id " + id + " does not exist");
        candy.setId(id);
        repo.save(candy);
    }

    //For delete mappings
    public void deleteCandy(int id){
        repo.deleteById(id);
    }
}
