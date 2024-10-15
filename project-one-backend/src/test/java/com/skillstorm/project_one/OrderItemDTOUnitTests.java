package com.skillstorm.project_one;


import java.math.BigDecimal;

import org.testng.Assert;
import org.testng.annotations.*;

import com.skillstorm.project_one.DTOs.OrderItemDTO;

class OrderItemDTOUnitTests {

    //Creating a private instance of OrderItemDTO
    private OrderItemDTO orderItemDTO;

    //creating a new OrderItemDTO object
    @BeforeTest
    void setUp() {
        orderItemDTO = new OrderItemDTO();
    }

    //Testing getter and setter methods
    @Test
    void testGettersAndSetters() {
        orderItemDTO.setId(1);
        orderItemDTO.setOrderId(101);
        orderItemDTO.setCandyId(202);
        orderItemDTO.setPrice(new BigDecimal("19.99"));
        orderItemDTO.setQuantity(5);

        Assert.assertEquals(orderItemDTO.getId(), 1);
        Assert.assertEquals(orderItemDTO.getOrderId(), 101);
        Assert.assertEquals(orderItemDTO.getCandyId(), 202);
        Assert.assertEquals(orderItemDTO.getPrice(), new BigDecimal("19.99"));
        Assert.assertEquals(orderItemDTO.getQuantity(), 5);
    }

    //testing parameterized constructor
    @Test
    void testConstructor() {
        OrderItemDTO dto = new OrderItemDTO(1, 101, 202, new BigDecimal("19.99"), 5);

        Assert.assertEquals(dto.getId(), 1);
        Assert.assertEquals(dto.getOrderId(), 101);
        Assert.assertEquals(dto.getCandyId(), 202);
        Assert.assertEquals(dto.getPrice(), new BigDecimal("19.99"));
        Assert.assertEquals(dto.getQuantity(), 5);
    }

    //testing toString method
    @Test
    void testToString() {
        orderItemDTO.setId(1);
        orderItemDTO.setOrderId(101);
        orderItemDTO.setCandyId(202);
        orderItemDTO.setPrice(new BigDecimal("19.99"));
        orderItemDTO.setQuantity(5);

        String expectedString = "OrderItemDTO [id=1, orderId=101, candyId=202, price=19.99, quantity=5]";
        Assert.assertEquals(orderItemDTO.toString(), expectedString);
    }
}