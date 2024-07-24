package com.skillstorm.project_one.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "candy")
public class Candy {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String candyName;

    @Column(length = 50)
    private String candyType;

    @Column(length = 50)
    private String candyFlavor;

    @Min(value = 0)
    @Max(value = 15)
    private Double price;

    @Min(value = 0)
    @Max(value = 5)
    private Double weight;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCandyName() {
        return candyName;
    }

    public void setCandyName(String candyName) {
        this.candyName = candyName;
    }

    public String getCandyType() {
        return candyType;
    }

    public void setCandyType(String candyType) {
        this.candyType = candyType;
    }

    public String getCandyFlavor() {
        return candyFlavor;
    }

    public void setCandyFlavor(String candyFlavor) {
        this.candyFlavor = candyFlavor;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Candy [id=" + id + ", candyName=" + candyName + ", candyType=" + candyType + ", candyFlavor="
                + candyFlavor + ", price=" + price + ", weight=" + weight + "]";
    }


    
    


}
