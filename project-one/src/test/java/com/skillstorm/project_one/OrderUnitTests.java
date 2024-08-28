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
        orders = new Orders();
        orders.setId(10);
        orders.setCustomerName("Jeremiah Mensah");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //TimeZone.setDefault(TimeZone.getTimeZone("EST"));
        try {
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
        Assert.assertEquals(10, orders.getId());
        Assert.assertEquals("Jeremiah Mensah", orders.getCustomerName());
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
        orders.setId(2);
        orders.setCustomerName("DJ Kim");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse("2013-08-05");
            orders.setOrderDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        orders.setStatus("completed");
        orders.setCustomerAddress("Apt. 131 838 Walter Freeway, Geraldoburgh, PA 20991-9051");

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

    // @Test
    // public void testToString() {
    //     String expectedString = "Orders [id=10, customerName=Jeremiah Mensah, orderDate=Sun Jan 01 00:00:00 EST 2023, status=pending, customerAddress=Apt. 404 609 Cruz Walks, Port Meghanmouth, CO 64357-4203]";
    //     System.out.println(expectedString);
    //     System.out.println(orders.toString());
    //     Assert.assertEquals(expectedString, orders.toString());
    // }
}
