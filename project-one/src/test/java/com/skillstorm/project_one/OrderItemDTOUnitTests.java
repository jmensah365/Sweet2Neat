package com.skillstorm.project_one;

//import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.testng.Assert;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
import org.testng.annotations.*;

import com.skillstorm.project_one.DTOs.OrderItemDTO;

class OrderItemDTOUnitTests {

    private OrderItemDTO orderItemDTO;

    @BeforeTest
    void setUp() {
        orderItemDTO = new OrderItemDTO();
    }

    @Test
    void testGettersAndSetters() {
        orderItemDTO.setId(1);
        orderItemDTO.setOrderId(101);
        orderItemDTO.setCandyId(202);
        orderItemDTO.setPrice(new BigDecimal("19.99"));
        orderItemDTO.setQuantity(5);

        Assert.assertEquals(1, orderItemDTO.getId());
        Assert.assertEquals(101, orderItemDTO.getOrderId());
        Assert.assertEquals(202, orderItemDTO.getCandyId());
        Assert.assertEquals(new BigDecimal("19.99"), orderItemDTO.getPrice());
        Assert.assertEquals(5, orderItemDTO.getQuantity());
    }

    @Test
    void testConstructor() {
        OrderItemDTO dto = new OrderItemDTO(1, 101, 202, new BigDecimal("19.99"), 5);

        Assert.assertEquals(1, dto.getId());
        Assert.assertEquals(101, dto.getOrderId());
        Assert.assertEquals(202, dto.getCandyId());
        Assert.assertEquals(new BigDecimal("19.99"), dto.getPrice());
        Assert.assertEquals(5, dto.getQuantity());
    }

    @Test
    void testToString() {
        orderItemDTO.setId(1);
        orderItemDTO.setOrderId(101);
        orderItemDTO.setCandyId(202);
        orderItemDTO.setPrice(new BigDecimal("19.99"));
        orderItemDTO.setQuantity(5);

        String expectedString = "OrderItemDTO [id=1, orderId=101, candyId=202, price=19.99, quantity=5]";
        Assert.assertEquals(expectedString, orderItemDTO.toString());
    }
}
