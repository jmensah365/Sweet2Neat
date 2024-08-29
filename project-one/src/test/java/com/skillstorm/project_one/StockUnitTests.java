package com.skillstorm.project_one;

import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.skillstorm.project_one.Models.Candy;
import com.skillstorm.project_one.Models.Stock;
import com.skillstorm.project_one.Models.Warehouse;

class StockTest {

    @Mock
    private Candy candy;

    @Mock
    private Warehouse warehouse;

    @InjectMocks
    private Stock stock;
    private AutoCloseable closeable;

    @BeforeMethod
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterTest
    public void teardown() throws Exception {
        closeable.close();
    }

    @Test
    public void testGetAndSetId() {
        stock.setId(10);
        Assert.assertEquals(10, stock.getId());
    }

    @Test
    public void testGetAndSetWarehouse() {
        stock.setWarehouse(warehouse);
        Assert.assertEquals(warehouse, stock.getWarehouse());
    }

    @Test
    public void testGetAndSetCandy() {
        stock.setCandy(candy);
        Assert.assertEquals(candy, stock.getCandy());
    }

    @Test
    public void testGetAndSetQuantity() {
        stock.setQuantity(1000);
        Assert.assertEquals(1000, stock.getQuantity());
    }

    @Test
    public void testGetWarehouseId() {
        when(warehouse.getId()).thenReturn(100);
        stock.setWarehouse(warehouse);

        Assert.assertEquals(100, stock.getWarehouseId());

        stock.setWarehouse(null);
        Assert.assertEquals(null, stock.getWarehouseId());
    }

    @Test
    public void testGetCandyId() {
        when(candy.getCandyId()).thenReturn(200);
        stock.setCandy(candy);;

        Assert.assertEquals(200, stock.getCandyId());

        stock.setCandy(null);
        Assert.assertEquals(null, stock.getCandyId());
    }

    @Test
    void testToString() {
        stock.setId(1);
        stock.setQuantity(10);
        stock.setCandy(candy);
        stock.setWarehouse(warehouse);

        String expectedString = "Stock [id=1, warehouse=" + warehouse + ", candy=" + candy + ", quantity=10]";
        Assert.assertEquals(expectedString, stock.toString());
    }
}
