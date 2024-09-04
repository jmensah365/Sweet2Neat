package com.skillstorm.project_one.Services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillstorm.project_one.Models.Candy;
import com.skillstorm.project_one.Repositories.CandyRepo;

/**
 * Service class for managing operations related to Candy entities.
 */
@Service
public class CandyService {
    
    private CandyRepo repo;

    // Constructor injection for CandyRepo
    public CandyService(CandyRepo repo){
        this.repo = repo;
    }

    // Retrieve all Candy entities from the repo
    public List<Candy> findAll(){
        return repo.findAllByOrderByIdAsc();
    }


    /**
     * Retrieve a Candy entity by its ID
     * @param id ID of the candy entity
     * @return A candy entity
     */
    public Optional<Candy> findById(int id){
        return repo.findById(id);
    }


    /**
     * Retrieve a list of Candy entities by their flavor
     * @param flavor the flavor field associated with the candy entity
     * @return A list of candy entities matching flavor
     */
    public List<Candy> findByFlavor(String flavor){
        return repo.findByFlavor(flavor);
    }

    /**
     *  Retrieve a list of Candy entities by their type
     * @param type the type field associated with the candy entity
     * @return A list of candy entities with matching type
     */
    public List<Candy> findByType(String type){
        return repo.findByType(type);
    }

    /**
     * Create a new Candy in the repo
     * @param candy the candy entity
     */
    public Candy createCandy(Candy candy){
        return repo.save(candy);
    }

    /**
     * Update an existing Candy in the repo
     * @param id The ID of the candy
     * @param candy the candy entity
     */
    public void updateCandy(int id, Candy candy){
        if(!repo.existsById(id)) throw new NoSuchElementException("Candy with id " + id + " does not exist");
        candy.setId(id);
        repo.save(candy);
    }

    /**
     * Delete a Candy from the repo by its ID
     * @param id the ID of the candy
     */
    public void deleteCandy(int id){
        repo.deleteById(id);
    }
}
