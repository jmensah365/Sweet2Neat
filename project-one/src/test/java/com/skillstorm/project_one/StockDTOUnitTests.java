package com.skillstorm.project_one;


import org.testng.Assert;
import org.testng.annotations.*;

import com.skillstorm.project_one.DTOs.StockDTO;

public class StockDTOUnitTests {

    private StockDTO stockDTO;

    //Creating a new stockDTO object before each test
    @BeforeMethod
    public void init(){
        stockDTO = new StockDTO();
    }

    //Testing getters and setters
    @Test
    public void testGettersAndSetters(){
        stockDTO.setId(1);
        stockDTO.setCandyId(2);
        stockDTO.setWarehouseId(3);
        stockDTO.setQuantity(30);

        Assert.assertEquals(stockDTO.getId(), 1);
        Assert.assertEquals(stockDTO.getCandyId(), 2);
        Assert.assertEquals(stockDTO.getWarehouseId(), 3);
        Assert.assertEquals(stockDTO.getQuantity(), 30);
    }

    //Testing the paramterized constructor
    @Test
    public void testConstructor(){
        StockDTO dto = new StockDTO(5,10,15,50);
        Assert.assertEquals(dto.getId(), 5);
        Assert.assertEquals(dto.getCandyId(), 10);
        Assert.assertEquals(dto.getWarehouseId(), 15);
        Assert.assertEquals(dto.getQuantity(), 50);
    }

    //Testing toString method
    @Test
    public void testToString(){
        stockDTO.setId(2);
        stockDTO.setCandyId(4);
        stockDTO.setWarehouseId(6);
        stockDTO.setQuantity(100);
        String expectedString = "StockDTO [id=2, candyId=4, warehouseId=6, quantity=100]";
        Assert.assertEquals(stockDTO.toString(), expectedString);
    }
}