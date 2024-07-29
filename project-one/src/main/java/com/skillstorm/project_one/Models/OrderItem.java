package com.skillstorm.project_one.Models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "orderitem")
public class OrderItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotBlank
    @JoinColumn(name = "orders_id")
    private Orders orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotBlank
    @JoinColumn(name = "candy_id")
    private Candy candy;

    @Column
    @PositiveOrZero
    private BigDecimal price;

    @Column
    @PositiveOrZero
    private Integer quantity;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getCandyId(){
        return candy != null ? candy.getCandyId() : null;
    }

    public Integer getOrderId(){
        return orders != null ? orders.getId() : null;
    }

    public void setCandy(Candy candy){
        this.candy = candy;
    }

    public void setOrder(Orders orders){
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "OrderItem [id=" + id + ", price=" + price + ", quantity=" + quantity + "]";
    }

    
}
