package com.skillstorm.project_one;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.skillstorm.project_one.Models.Warehouse;

public class WarehouseModelTest {

    private Warehouse warehouse;
    
    @BeforeEach
    public void setupWarehouse() {
        warehouse = new Warehouse();
    }

    @Test
    public void testSetIdAndGetId() {
        int expected = 1;
        warehouse.setId(expected);
        int actual = warehouse.getId();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetLocationAndGetLocation() {
        String expected = "12345 Candy Lane, Candyville, CandyLand 678910";
        warehouse.setLocation(expected);
        assertEquals(expected, warehouse.getLocation());
    }

    @Test
    public void testSetCapacityAndGetCapacity() {
        int expected = 10000;
        warehouse.setCapacity(expected);
        int actual = warehouse.getCapacity();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetCurrentStockAndGetCurrentStock() {
        int expected = 400;
        warehouse.setCurrentStock(expected);
        int actual = warehouse.getCurrentStock();
        assertEquals(expected, actual);
    }

    @Test
    public void testWarehouseToString() {
        int id = 1;
        String location = "12345 Candy Lane, Candyville, CandyLand 678910";
        int capacity = 10000;
        int currentStock = 400;

        warehouse.setId(id);
        warehouse.setLocation(location);
        warehouse.setCapacity(capacity);
        warehouse.setCurrentStock(currentStock);

        String expected = "Warehouse [id=" + id + ", location=" + location + ", capacity=" + capacity + ", currentStock=" + currentStock + "]";
        String actual = warehouse.toString();
        assertEquals(expected, actual);
    }
}
