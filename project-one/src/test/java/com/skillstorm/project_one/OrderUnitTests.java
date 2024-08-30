package com.skillstorm.project_one;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.*;

import com.skillstorm.project_one.Models.Orders;

public class OrderUnitTests {
    private Orders orders;

    @BeforeMethod
    public void init() {
        // Given: An Orders object with initial values for ID, customer name, order date, status, and customer address
        orders = new Orders();
        orders.setId(10);
        orders.setCustomerName("Jeremiah Mensah");
        
        //Using simple date format to format the string to match the intended pattern for the backend
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //Parsing the date I want to set (2023-01-01)
            Date date = sdf.parse("2023-01-01");
            orders.setOrderDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        orders.setStatus("pending");
        orders.setCustomerAddress("Apt. 404 609 Cruz Walks, Port Meghanmouth, CO 64357-4203");
    }

    @Test
    public void testGetters() {
        // When: The getters are called
        // Then: The values should match the initially set values
        Assert.assertEquals(10, orders.getId());
        Assert.assertEquals("Jeremiah Mensah", orders.getCustomerName());
        
        //Using simple date format to format the string to match the intended pattern for the backend
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse("2023-01-01");
            Assert.assertEquals(date, orders.getOrderDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        Assert.assertEquals("pending", orders.getStatus());
        Assert.assertEquals("Apt. 404 609 Cruz Walks, Port Meghanmouth, CO 64357-4203", orders.getCustomerAddress());
    }

    @Test
    public void testSetters() {
        // Given: New values for ID, customer name, order date, status, and customer address
        orders.setId(2);
        orders.setCustomerName("DJ Kim");
        
        //Using simple date format to format the string to match the intended pattern for the backend
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse("2013-08-05");
            orders.setOrderDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        orders.setStatus("completed");
        orders.setCustomerAddress("Apt. 131 838 Walter Freeway, Geraldoburgh, PA 20991-9051");

        // When: The setters are called to update the Orders' attributes
        // Then: The values should reflect the updated values
        Assert.assertEquals(2, orders.getId());
        Assert.assertEquals("DJ Kim", orders.getCustomerName());
        
        try {
            Date date = sdf.parse("2013-08-05");
            Assert.assertEquals(date, orders.getOrderDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        Assert.assertEquals("completed", orders.getStatus());
        Assert.assertEquals("Apt. 131 838 Walter Freeway, Geraldoburgh, PA 20991-9051", orders.getCustomerAddress());
    }

    @Test
    public void testToString() {
        // Given: The expected string representation of the Orders object
        String expectedString = "Orders [id=10, customerName=Jeremiah Mensah, orderDate=Sun Jan 01 00:00:00 EST 2023, status=pending, customerAddress=Apt. 404 609 Cruz Walks, Port Meghanmouth, CO 64357-4203]";
        
        // When: The toString() method is called
        // Then: The output should match the expected string
        Assert.assertEquals(expectedString, orders.toString());
    }
}
