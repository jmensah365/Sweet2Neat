package com.skillstorm.project_one;

import org.testng.Assert;

import java.math.BigDecimal;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.skillstorm.project_one.Models.Candy;

public class CandyTests {

    private Candy candy;
    
    @BeforeMethod
    public void init() {
        candy = new Candy();
        candy.setId(40);
        candy.setName("Laffy Taffy");
        candy.setType("Taffy Candy");
        candy.setFlavor("Grape");
        candy.setPrice(BigDecimal.valueOf(2.99));
        candy.setWeight(BigDecimal.valueOf(0.5));
    }

    @Test
    public void testGetters() {
        Assert.assertEquals(40, candy.getCandyId());
        Assert.assertEquals("Laffy Taffy", candy.getName());
        Assert.assertEquals("Taffy Candy", candy.getType());
        Assert.assertEquals("Grape", candy.getFlavor());
        Assert.assertEquals(BigDecimal.valueOf(2.99), candy.getPrice());
        Assert.assertEquals(BigDecimal.valueOf(0.5), candy.getWeight());
    }

    @Test
    public void testSetters() {
        candy.setName("Gummy Bear");
        candy.setType("Gummy");
        candy.setFlavor("Strawberry");
        candy.setPrice(BigDecimal.valueOf(1.99));
        candy.setWeight(BigDecimal.valueOf(0.2));

        Assert.assertEquals("Gummy Bear", candy.getName());
        Assert.assertEquals("Gummy", candy.getType());
        Assert.assertEquals("Strawberry", candy.getFlavor());
        Assert.assertEquals(BigDecimal.valueOf(1.99), candy.getPrice());
        Assert.assertEquals(BigDecimal.valueOf(0.2), candy.getWeight());
    }

    @Test
    public void testToString() {
        String expectedString = "Candy [id=40, name=Laffy Taffy, type=Taffy Candy, flavor=Grape, price=2.99, weight=0.5]";
        Assert.assertEquals(expectedString, candy.toString());
    }

}
