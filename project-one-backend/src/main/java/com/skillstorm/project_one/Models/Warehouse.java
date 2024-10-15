package com.skillstorm.project_one.Models;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "warehouse")
public class Warehouse {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    @NotBlank
    private String location;

    @Column(length = 50)
    @PositiveOrZero
    private Integer capacity;

    @Column(length = 50)
    @PositiveOrZero
    private Integer currentStock;

    //Adding oneToMany relationship with stock table
    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
    private Set<Stock> stocks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(Integer currentStock) {
        this.currentStock = currentStock;
    }

    @Override
    public String toString() {
        return "Warehouse [id=" + id + ", location=" + location + ", capacity=" + capacity + ", currentStock="
                + currentStock + "]";
    }
}
