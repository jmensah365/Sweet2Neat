package com.skillstorm.project_one;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.skillstorm.project_one.Models.Warehouse;

public class WarehouseModelTest {

    private Warehouse warehouse;
    
    @BeforeMethod
    public void setupWarehouse() {
        warehouse = new Warehouse();
    }

    @Test
    public void testSetIdAndGetId() {
        int expected = 1;

        // set the id the warehouse should have
        warehouse.setId(expected);

        // get the id this warehouse has
        int actual = warehouse.getId();

        // ensure warehouse id matched
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testSetLocationAndGetLocation() {
        String expected = "12345 Candy Lane, Candyville, CandyLand 678910";
        
        //set the location the warehouse should have
        warehouse.setLocation(expected);

        // ensure the warehouse location matches
        Assert.assertEquals(warehouse.getLocation(), expected);
    }

    @Test
    public void testSetCapacityAndGetCapacity() {
        int expected = 10000;
        
        // set the warehouse capacity
        warehouse.setCapacity(expected);
    
        // get this warehouse's capacity
        int actual = warehouse.getCapacity();

        // ensure the capacity matches
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testSetCurrentStockAndGetCurrentStock() {
        int expected = 400;

        // set warehouse current stock
        warehouse.setCurrentStock(expected);

        // get this warehouse current stock
        int actual = warehouse.getCurrentStock();

        // ensure the current stock matches
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testWarehouseToString() {
        int id = 1;
        String location = "12345 Candy Lane, Candyville, CandyLand 678910";
        int capacity = 10000;
        int currentStock = 400;

        // set all the properties and prep for tostring()
        warehouse.setId(id);
        warehouse.setLocation(location);
        warehouse.setCapacity(capacity);
        warehouse.setCurrentStock(currentStock);

        String expected = "Warehouse [id=" + id + ", location=" + location + ", capacity=" + capacity + ", currentStock=" + currentStock + "]";

        // get warehouses contents with tostring()
        String actual = warehouse.toString();

        // ensure content matches
        Assert.assertEquals(actual, expected);
    }
}
