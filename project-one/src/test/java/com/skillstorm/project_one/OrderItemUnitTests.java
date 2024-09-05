package com.skillstorm.project_one;

import java.math.BigDecimal;

import org.testng.Assert;
import org.testng.annotations.*;

import com.skillstorm.project_one.Models.OrderItem;

public class OrderItemUnitTests {
    private OrderItem orderItem;

    @BeforeMethod
    public void init() {
        // Given: An OrderItem object with initial values for ID, price, and quantity
        orderItem = new OrderItem();
        orderItem.setId(20);
        orderItem.setPrice(BigDecimal.valueOf(2.99));
        orderItem.setQuantity(35);
    }

    @Test
    public void testGetters() {
        // When: The getters are called
        // Then: The values should match the initially set values
        Assert.assertEquals(20, orderItem.getId());
        Assert.assertEquals(BigDecimal.valueOf(2.99), orderItem.getPrice());
        Assert.assertEquals(35, orderItem.getQuantity());
    }

    @Test
    public void testSetters() {
        // Given: New values for ID, price, and quantity
        orderItem.setId(25);
        orderItem.setPrice(BigDecimal.valueOf(15.99));
        orderItem.setQuantity(40);

        // When: The setters are called to update the OrderItem's attributes
        // Then: The values should match the expected result
        Assert.assertEquals(25, orderItem.getId());
        Assert.assertEquals(BigDecimal.valueOf(15.99), orderItem.getPrice());
        Assert.assertEquals(40, orderItem.getQuantity());
    }

    @Test
    public void testToString() {
        // Given: The expected string representation of the OrderItem
        String expectedString = "OrderItem [id=20, orders=null, candy=null, price=2.99, quantity=35]";

        // When: The toString() method is called
        // Then: The output should match the expected string
        Assert.assertEquals(expectedString, orderItem.toString());
    }
}