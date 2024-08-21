package com.skillstorm.project_one;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.skillstorm.project_one.Models.OrderItem;

public class OrderItemUnitTests {
    private OrderItem orderItem;

    @BeforeTest
    public void init(){
        orderItem.setId(20);
        orderItem.setPrice(BigDecimal.valueOf(2.99));
        orderItem.setQuantity(35);
    }

    @Test
    public void testGetters(){
        assertEquals(20, orderItem.getId());
        assertEquals(BigDecimal.valueOf(2.99), orderItem.getPrice());
        assertEquals(35, orderItem.getQuantity());
    }

    @Test
    public void testSetters(){
        orderItem.setId(25);
        orderItem.setPrice(BigDecimal.valueOf(15.99));
        orderItem.setQuantity(40);
    }

    @Test
    public void testToString() {
        //String expectedString = "OrderItem [id=20, ]"
    }
}
