package com.skillstorm.project_one.DTOs;

public class StockDTO {
    private Integer id;
    private Integer candyId;
    private Integer warehouseId;
    private Integer quantity;

    public StockDTO(){}

    
    public StockDTO(Integer id, Integer candyId, Integer warehouseId, Integer quantity) {
        this.id = id;
        this.candyId = candyId;
        this.warehouseId = warehouseId;
        this.quantity = quantity;
    }


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getCandyId() {
        return candyId;
    }
    public void setCandyId(Integer candyId) {
        this.candyId = candyId;
    }
    public Integer getWarehouseId() {
        return warehouseId;
    }
    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        return "StockDTO [id=" + id + ", candyId=" + candyId + ", warehouseId=" + warehouseId + ", quantity=" + quantity
                + "]";
    }

    
}
