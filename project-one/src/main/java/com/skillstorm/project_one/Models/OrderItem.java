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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * Entity class representing an item in an order.
 */
@Entity
@Table(name = "orderitem")
public class OrderItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Many-to-One relationship with Orders entity.
    // Each OrderItem is linked to a single Orders entity.
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "orders_id")
    private Orders orders;

    // Many-to-One relationship with Candy entity.
    // Each OrderItem is linked to a single Candy entity.
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "candy_id")
    private Candy candy;

    // Price of the item in the order, must be positive or zero.
    @Column
    @PositiveOrZero
    private BigDecimal price;

    // Quantity of the item in the order, must be positive or zero.
    @Column
    @PositiveOrZero
    private Integer quantity;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Orders getOrders() {
        return orders;
    }

    public Integer getOrderId() {
        return orders.getId();
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Candy getCandy() {
        return candy;
    }

    public Integer getCandyId() {
        return candy.getCandyId();
    }

    public void setCandy(Candy candy) {
        this.candy = candy;
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

    // Override toString method to provide a string representation of the OrderItem object.
    @Override
    public String toString() {
        return "OrderItem [id=" + id + ", orders=" + orders + ", candy=" + candy + ", price=" + price + ", quantity="
                + quantity + "]";
    }
}
