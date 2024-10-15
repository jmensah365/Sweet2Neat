package com.skillstorm.project_one;

import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.*;

import com.skillstorm.project_one.Models.Orders;

public class OrderUnitTests {
    private Orders orders;

    @BeforeMethod
    public void init() {
        orders = new Orders(10, "Jeremiah Mensah", "pending","Apt. 404 609 Cruz Walks, Port Meghanmouth, CO 64357-4203", null );
        orders.setId(10);
        orders.setCustomerName("Jeremiah Mensah");
        orders.setStatus("pending");
        orders.setCustomerAddress("Apt. 404 609 Cruz Walks, Port Meghanmouth, CO 64357-4203");
    }

    @Test
    public void testGetters() {
        Assert.assertEquals(orders.getId(), 10);
        Assert.assertEquals(orders.getCustomerName(), "Jeremiah Mensah");
        Assert.assertEquals(orders.getOrderDate(), LocalDate.now());
        Assert.assertEquals(orders.getStatus(), "pending");
        Assert.assertEquals(orders.getCustomerAddress(), "Apt. 404 609 Cruz Walks, Port Meghanmouth, CO 64357-4203");
    }

    @Test
    public void testSetters() {
        orders.setId(2);
        orders.setCustomerName("DJ Kim");
        orders.setStatus("completed");
        orders.setCustomerAddress("Apt. 131 838 Walter Freeway, Geraldoburgh, PA 20991-9051");

        Assert.assertEquals(orders.getId(), 2);
        Assert.assertEquals(orders.getCustomerName(), "DJ Kim");
        Assert.assertEquals(orders.getStatus(), "completed");
        Assert.assertEquals(orders.getCustomerAddress(), "Apt. 131 838 Walter Freeway, Geraldoburgh, PA 20991-9051");
    }

    @Test
    public void testToString() {
        String expectedString = "Orders [id=10, customerName=Jeremiah Mensah, orderDate=" + orders.getOrderDate() + ", status=pending, customerAddress=Apt. 404 609 Cruz Walks, Port Meghanmouth, CO 64357-4203]";
        System.out.println(expectedString);
        System.out.println(orders.toString());
        Assert.assertEquals(orders.toString(), expectedString);
    }
}
