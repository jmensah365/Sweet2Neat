package com.skillstorm.project_one;


import java.math.BigDecimal;

import org.testng.Assert;
import org.testng.annotations.*;

import com.skillstorm.project_one.Models.OrderItem;

public class OrderItemUnitTests {
    private OrderItem orderItem;

    @BeforeMethod
    public void init() {
        orderItem = new OrderItem();
        orderItem.setId(20);
        orderItem.setPrice(BigDecimal.valueOf(2.99));
        orderItem.setQuantity(35);
    }

    @Test
    public void testGetters() {
        Assert.assertEquals(20, orderItem.getId());
        Assert.assertEquals(BigDecimal.valueOf(2.99), orderItem.getPrice());
        Assert.assertEquals(35, orderItem.getQuantity());
    }

    @Test
    public void testSetters() {
        orderItem.setId(25);
        orderItem.setPrice(BigDecimal.valueOf(15.99));
        orderItem.setQuantity(40);

        Assert.assertEquals(25, orderItem.getId());
        Assert.assertEquals(BigDecimal.valueOf(15.99), orderItem.getPrice());
        Assert.assertEquals(40, orderItem.getQuantity());
    }

    @Test
    public void testToString() {
        String expectedString = "OrderItem [id=20, orders=null, candy=null, price=2.99, quantity=35]";
        Assert.assertEquals(expectedString, orderItem.toString());
    }
}
