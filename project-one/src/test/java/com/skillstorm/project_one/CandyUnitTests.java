package com.skillstorm.project_one;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.testng.annotations.*;

import com.skillstorm.project_one.Models.Candy;

public class CandyUnitTests {

    private Candy candy;
    
    @BeforeTest
    void init() {
        candy = new Candy();
        candy.setId(40);
        candy.setName("Laffy Taffy");
        candy.setType("Taffy Candy");
        candy.setFlavor("Grape");
        candy.setPrice(BigDecimal.valueOf(2.99));
        candy.setWeight(BigDecimal.valueOf(0.5));
    }

    @Test
    void testGetters() {
        assertEquals(40, candy.getCandyId());
        assertEquals("Laffy Taffy", candy.getName());
        assertEquals("Taffy Candy", candy.getType());
        assertEquals("Grape", candy.getFlavor());
        assertEquals(BigDecimal.valueOf(2.99), candy.getPrice());
        assertEquals(BigDecimal.valueOf(0.5), candy.getWeight());
    }

    @Test
    void testSetters() {
        candy.setName("Gummy Bear");
        candy.setType("Gummy Candy");
        candy.setFlavor("Strawberry");
        candy.setPrice(BigDecimal.valueOf(1.99));
        candy.setWeight(BigDecimal.valueOf(0.2));

        assertEquals("Gummy Bear", candy.getName());
        assertEquals("Gummy Candy", candy.getType());
        assertEquals("Strawberry", candy.getFlavor());
        assertEquals(BigDecimal.valueOf(1.99), candy.getPrice());
        assertEquals(BigDecimal.valueOf(0.2), candy.getWeight());
    }

    @Test
    void testToString() {
        String expectedString = "Candy [id=40, name=Gummy Bear, type=Gummy Candy, flavor=Strawberry, price=1.99, weight=0.2]";
        assertEquals(expectedString, candy.toString());
    }

}
