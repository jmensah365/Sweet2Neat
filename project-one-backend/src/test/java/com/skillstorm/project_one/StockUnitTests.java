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
        // set stock id
        stock.setId(10);

        // ensure stock returns correct id
        Assert.assertEquals(stock.getId(), 10);
    }

    @Test
    public void testGetAndSetWarehouse() {
        // set stock warehouse
        stock.setWarehouse(warehouse);

        // ensure stock returns correct warehouse
        Assert.assertEquals(stock.getWarehouse(), warehouse);
    }

    @Test
    public void testGetAndSetCandy() {
        // set stock candy
        stock.setCandy(candy);

        // ensure stock return correct candy
        Assert.assertEquals(stock.getCandy(), candy);
    }

    @Test
    public void testGetAndSetQuantity() {
        // set stock quantity
        stock.setQuantity(1000);

        // ensure stock returns correct quantity
        Assert.assertEquals(stock.getQuantity(), 1000);
    }

    @Test
    public void testGetWarehouseId() {
        // stub warehouse getid to return warehouseid
        when(warehouse.getId()).thenReturn(100);

        // ensure warehouseid matches
        Assert.assertEquals(stock.getWarehouseId(), 100);

        // stub again for null condition this time
        when(warehouse.getId()).thenReturn(null);

        // ensure warehouseid is null
        Assert.assertEquals(stock.getWarehouseId(), null);
    }

    @Test
    public void testGetCandyId() {
        // stub candy getid to return set candyid
        when(candy.getCandyId()).thenReturn(200);

        // ensure candyid matches 
        Assert.assertEquals(stock.getCandyId(), 200);

        // stub again for null condition
        when(candy.getCandyId()).thenReturn(null);

        // ensure candyid is null
        Assert.assertEquals(stock.getCandyId(), null);
    }

    @Test
    void testToString() {
        //set up stock contents for tostring
        stock.setId(1);
        stock.setQuantity(10);
        stock.setCandy(candy);
        stock.setWarehouse(warehouse);

        String expectedString = "Stock [id=1, warehouse=" + warehouse + ", candy=" + candy + ", quantity=10]";

        // ensure strings match
        Assert.assertEquals(expectedString, stock.toString());
    }
}
