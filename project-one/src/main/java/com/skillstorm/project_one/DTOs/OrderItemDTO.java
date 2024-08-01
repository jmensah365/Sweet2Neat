package com.skillstorm.project_one.DTOs;

import java.math.BigDecimal;


//DTO for my order item entity to correctly initialize order and candy ID's
public class OrderItemDTO {
    
    //DTO fields
    private Integer id;
    private Integer orderId;
    private Integer candyId;
    private BigDecimal price;
    private Integer quantity;

    public OrderItemDTO(){}

    public OrderItemDTO(Integer id, Integer orderId, Integer candyId, BigDecimal price, Integer quantity) {
        this.id = id;
        this.orderId = orderId;
        this.candyId = candyId;
        this.price = price;
        this.quantity = quantity;
    }

    

    //Getters and Setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public Integer getCandyId() {
        return candyId;
    }
    public void setCandyId(Integer candyId) {
        this.candyId = candyId;
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


    @Override
    public String toString() {
        return "OrderItemDTO [id=" + id + ", orderId=" + orderId + ", candyId=" + candyId + ", price=" + price
                + ", quantity=" + quantity + "]";
    }

    
}
