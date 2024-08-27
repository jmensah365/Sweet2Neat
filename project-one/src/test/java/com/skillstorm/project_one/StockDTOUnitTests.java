package com.skillstorm.project_one;


import org.testng.Assert;
import org.testng.annotations.*;

import com.skillstorm.project_one.DTOs.StockDTO;

public class StockDTOUnitTests {
    private StockDTO stockDTO;

    @BeforeMethod
    public void init(){
        stockDTO = new StockDTO();
    }

    @Test
    public void testGettersAndSetters(){
        stockDTO.setId(1);
        stockDTO.setCandyId(2);
        stockDTO.setWarehouseId(3);
        stockDTO.setQuantity(30);

        Assert.assertEquals(1, stockDTO.getId());
        Assert.assertEquals(2, stockDTO.getCandyId());
        Assert.assertEquals(3, stockDTO.getWarehouseId());
        Assert.assertEquals(30, stockDTO.getQuantity());
    }

    @Test
    public void testConstructor(){
        StockDTO dto = new StockDTO(5,10,15,50);
        Assert.assertEquals(5, dto.getId());
        Assert.assertEquals(10, dto.getCandyId());
        Assert.assertEquals(15, dto.getWarehouseId());
        Assert.assertEquals(50, dto.getQuantity());
    }

    @Test
    public void testToString(){
        stockDTO.setId(2);
        stockDTO.setCandyId(4);
        stockDTO.setWarehouseId(6);
        stockDTO.setQuantity(100);
        String expectedString = "StockDTO [id=2, candyId=4, warehouseId=6, quantity=100]";
        Assert.assertEquals(expectedString, stockDTO.toString());
    }
}
