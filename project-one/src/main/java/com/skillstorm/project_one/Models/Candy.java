package com.skillstorm.project_one.Models;

import java.math.BigDecimal;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "candy")
public class Candy {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    @NotBlank
    private String name;

    @Column(length = 50)
    @NotBlank
    private String type;

    @Column(length = 50)
    @NotBlank
    private String flavor;

    @Min(value = 0)
    private BigDecimal price;

    @Min(value = 0)
    private BigDecimal weight;

    //adding OneToMany relationship with stock table
    @OneToMany(mappedBy = "candy", cascade = CascadeType.ALL)
    private Set<Stock> stocks;

    ///Adding Getters, Setters, and toString method
    public Integer getCandyId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Candy [id=" + id + ", name=" + name + ", type=" + type + ", flavor=" + flavor + ", price=" + price
                + ", weight=" + weight + "]";
    }

    


    
    


}
