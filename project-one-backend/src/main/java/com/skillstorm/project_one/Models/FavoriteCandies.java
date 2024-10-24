package com.skillstorm.project_one.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "favorites")
public class FavoriteCandies {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    @JoinColumn(name = "candy_id")
    private Candy candy;


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public Candy getCandy() {
        return candy;
    }


    public void setCandy(Candy candy) {
        this.candy = candy;
    }


    @Override
    public String toString() {
        return "FavoriteCandies [id=" + id + ", candy=" + candy + "]";
    }


    
}
