package com.skillstorm.project_one;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.skillstorm.project_one.Models.Stock;
import com.skillstorm.project_one.Models.Warehouse;
import com.skillstorm.project_one.Repositories.StockRepo;
import com.skillstorm.project_one.Repositories.WarehouseRepo;
import com.skillstorm.project_one.Services.WarehouseService;

public class WarehouseServiceTest {
    @Mock
    private WarehouseRepo warehouseRepo;
    
    @Mock
    private StockRepo stockRepo;

    @InjectMocks
    private WarehouseService warehouseService;
    private AutoCloseable closeable;

    private int warehouseId;
    private Warehouse warehouse;

    @BeforeTest
    public void setup() {
        warehouseId = 1;
        warehouse = new Warehouse();
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterTest
    public void teardown() throws Exception {
        closeable.close();
    }

    @Test
    public void testFindAll() {
        // predefine list of warehouses we will use as return
        List<Warehouse> expectedWarehouses = Arrays.asList(new Warehouse(), new Warehouse());

        // stub the repo method to return the predefined list
        when(warehouseRepo.findAllByOrderByIdAsc())
        .thenReturn(expectedWarehouses);

        // return the expectedWarehouses from func as Iterable obj
        Iterable<Warehouse> warehouses = warehouseService.findAll();

        // find the size of iterable ob
        int count = 0;
        for(Warehouse w : warehouses) {
            count++;
        }

        // ensure correct iterable/list is returned
        Assert.assertEquals(warehouses, expectedWarehouses);
        Assert.assertEquals(count, 2);
    }

    @Test
    public void testFindById() {
        warehouse.setId(warehouseId);

        // create a optional warehouse object to return for stubbing
        Optional<Warehouse> expectedWarehouse = Optional.ofNullable(warehouse);

        // findbyId will return the predefined optional obj
        when(warehouseRepo.findById(warehouseId))
        .thenReturn(expectedWarehouse);

        // call this func and return expectedWarehouse
        Optional<Warehouse> warehouses = warehouseService.findById(warehouseId);

        // ensure warehouses match and have the same ids
        Assert.assertEquals(warehouses, expectedWarehouse);
        Assert.assertEquals(warehouses.get().getId(), expectedWarehouse.get().getId());
    }

    @Test
    public void testCreateWarehouse() {
        warehouse.setId(2);
        warehouse.setCapacity(10000);
        warehouse.setLocation("Location");

        // stub save and have it return predefined warehouse
        when(warehouseRepo.save(warehouse))
        .thenReturn(warehouse);

        // creatwarehouse calls warehouserepo.save which should return expectedwarehouse
        Warehouse actualWarehouse = warehouseService.createWarehouse(warehouse);

        // ensure warehouse are the same, since they are referencing the same obj
        Assert.assertEquals(actualWarehouse, warehouse);
    }

    @Test
    public void testSuccessfulUpdateWarehouse() {
        // create two warehouse objects for stubbing 
        // existingWarehouse will stub the save, updatedWarehouse will stub the updateWarehouse
        Warehouse existingWarehouse = new Warehouse();
        existingWarehouse.setId(warehouseId);
        existingWarehouse.setLocation("Previous Location");
        existingWarehouse.setCapacity(10000);

        Warehouse updatedWarehouse = new Warehouse();
        updatedWarehouse.setLocation("Updated Location");
        updatedWarehouse.setCapacity(50000);

        // this stubs findById and gets the existingWarehouse
        when(warehouseRepo.findById(warehouseId)).thenReturn(Optional.of(existingWarehouse));
        when(warehouseRepo.save(existingWarehouse)).thenReturn(existingWarehouse);

        // calls func to simulate warehouse update with new states
        warehouseService.updateWarehouse(warehouseId, updatedWarehouse);

        // test if existing warehouse details were updated
        Assert.assertEquals(existingWarehouse.getLocation(), "Updated Location");
        Assert.assertEquals(existingWarehouse.getCapacity(), 50000);
        
    }

    @Test
    public void testUpdateStockForWarehouse() {
        // create new instances of warehouse and new stocks with quantities
        warehouse.setId(warehouseId);
        warehouse.setCurrentStock(0);
        Stock stock1 = new Stock();
        stock1.setQuantity(200);
        Stock stock2 = new Stock();
        stock2.setQuantity(300);

        List<Stock> stocks = Arrays.asList(stock1, stock2);

        // stub the func and have it returned the stocks
        when(stockRepo.findByWarehouse(warehouse))
        .thenReturn(stocks);

        // call this to update the stock in the predefine warehouse
        warehouseService.updateStockForWarehouse(warehouse);

        // ensure the new stock for warehouse is updated
        Assert.assertEquals(warehouse.getCurrentStock(), 500);
    }

    @Test
    public void testDeleteWarehouse() {
        // call to delete the warehouse with given id
        warehouseService.deleteWarehouse(warehouseId);

        // ensure this is only called once with the correct warehouseId
        verify(warehouseRepo, times(1)).deleteById(warehouseId);
    }
}
