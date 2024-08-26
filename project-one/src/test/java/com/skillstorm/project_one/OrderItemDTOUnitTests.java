package com.skillstorm.project_one;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skillstorm.project_one.DTOs.OrderItemDTO;

class OrderItemDTOUnitTests {

    private OrderItemDTO orderItemDTO;

    @BeforeEach
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

        assertEquals(1, orderItemDTO.getId());
        assertEquals(101, orderItemDTO.getOrderId());
        assertEquals(202, orderItemDTO.getCandyId());
        assertEquals(new BigDecimal("19.99"), orderItemDTO.getPrice());
        assertEquals(5, orderItemDTO.getQuantity());
    }

    @Test
    void testConstructor() {
        OrderItemDTO dto = new OrderItemDTO(1, 101, 202, new BigDecimal("19.99"), 5);

        assertEquals(1, dto.getId());
        assertEquals(101, dto.getOrderId());
        assertEquals(202, dto.getCandyId());
        assertEquals(new BigDecimal("19.99"), dto.getPrice());
        assertEquals(5, dto.getQuantity());
    }

    @Test
    void testToString() {
        orderItemDTO.setId(1);
        orderItemDTO.setOrderId(101);
        orderItemDTO.setCandyId(202);
        orderItemDTO.setPrice(new BigDecimal("19.99"));
        orderItemDTO.setQuantity(5);

        String expectedString = "OrderItemDTO [id=1, orderId=101, candyId=202, price=19.99, quantity=5]";
        assertEquals(expectedString, orderItemDTO.toString());
    }
}
