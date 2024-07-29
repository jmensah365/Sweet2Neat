package com.skillstorm.project_one.Models;

import java.sql.Date;
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

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotBlank
    private String customerName;

    @Column
    @NotBlank
    private Date orderDate;

    @Column
    @NotBlank
    private Boolean status;

    @Column
    @NotBlank
    private String customerAddress;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems;
    
    
    public String getCustomerAddress() {
        return customerAddress;
    }


    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }


    @OneToMany(mappedBy = "orders")
    private Set<OrderItem> orderItem;
    
    
    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getCustomerName() {
        return customerName;
    }


    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    public Date getOrderDate() {
        return orderDate;
    }


    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }


    public Boolean getStatus() {
        return status;
    }


    public void setStatus(Boolean status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Orders [id=" + id + ", customerName=" + customerName + ", orderDate=" + orderDate + ", status=" + status
                + "]";
    }

    
    
}
