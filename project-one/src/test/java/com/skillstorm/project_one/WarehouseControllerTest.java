package com.skillstorm.project_one;

import java.util.List;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.skillstorm.project_one.Controllers.WarehouseController;
import com.skillstorm.project_one.Models.Warehouse;
import com.skillstorm.project_one.Services.WarehouseService;

public class WarehouseControllerTest {

    @Mock
    private WarehouseService warehouseService;

    @InjectMocks
    private WarehouseController warehouseController;
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
    public void testGetAllWarehouses() {
        List<Warehouse> expectedWarehouses = Arrays.asList(new Warehouse(), new Warehouse(), new Warehouse());

        when(warehouseService.findAll())
        .thenReturn(expectedWarehouses);

        Iterable<Warehouse> warehouses = warehouseController.getAllWarehouses();

        int count = 0;
        for (Warehouse w : warehouses) {
            count++;
        }

        Assert.assertEquals(warehouses, expectedWarehouses);
        Assert.assertEquals(count, 3);
    }

    @Test
    public void testCreateWarehouse() {
        Warehouse warehouse = new Warehouse();
        
        when(warehouseService.createWarehouse(warehouse))
        .thenReturn(warehouse);

        Warehouse createdWarehouse = warehouseController.createWarehouse(warehouse);

        Assert.assertEquals(createdWarehouse, warehouse);
    }

    @Test
    public void testUpdateWarehouse() {
        Warehouse warehouse = new Warehouse();
        int id = 4;

        ResponseEntity<Warehouse> response = warehouseController.updateWarehouse(id, warehouse);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testDeleteWarehouse() {
        int id = 5;

        ResponseEntity<Void> response = warehouseController.deleteWarehouseById(id);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }
}
