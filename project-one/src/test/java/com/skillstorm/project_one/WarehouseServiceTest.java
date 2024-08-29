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

import com.skillstorm.project_one.Models.Orders;
import com.skillstorm.project_one.Models.Warehouse;
import com.skillstorm.project_one.Repositories.WarehouseRepo;
import com.skillstorm.project_one.Services.WarehouseService;

public class WarehouseServiceTest {
    @Mock
    private WarehouseRepo warehouseRepo;

    @InjectMocks
    private WarehouseService warehouseService;
    private AutoCloseable closeable;

    @BeforeTest
    public void setup() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterTest
    public void teardown() throws Exception {
        closeable.close();
    }

    @Test
    public void testFindAll() {
        List<Warehouse> expectedWarehouses = Arrays.asList(new Warehouse(), new Warehouse());

        when(warehouseRepo.findAllByOrderByIdAsc())
        .thenReturn(expectedWarehouses);

        Iterable<Warehouse> warehouses = warehouseService.findAll();

        int count = 0;
        for(Warehouse w : warehouses) {
            count++;
        }

        Assert.assertEquals(expectedWarehouses, warehouses);
        Assert.assertEquals(2, count);
    }

    @Test
    public void testFindById() {
        int warehouseId = 1;
        Warehouse warehouse = new Warehouse();
        Optional<Warehouse> expectedWarehouse = Optional.ofNullable(warehouse);

        when(warehouseRepo.findById(warehouseId))
        .thenReturn(expectedWarehouse);

        Optional<Warehouse> warehouses = warehouseService.findById(warehouseId);

        Assert.assertEquals(warehouses, expectedWarehouse);
    }

    // @Test
    // public void testCreateWarehouse() {
    //     Warehouse expectedWarehouse = new Warehouse();

    //     when(warehouseRepo.save(expectedWarehouse))
    //     .thenReturn(expectedWarehouse);

    //     Warehouse warehouse = warehouseService.createWarehouse(expectedWarehouse);

    //     Assert.assertEquals(warehouse, expectedWarehouse);
    // }

    @Test
    public void testDeleteWarehouse() {
        int warehouseId = 1;

        warehouseService.deleteWarehouse(warehouseId);

        verify(warehouseRepo, times(1)).deleteById(warehouseId);
    }
}
