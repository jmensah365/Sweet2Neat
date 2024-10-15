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

    private Warehouse warehouse;
    private int warehouseId;

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
    public void testGetAllWarehouses() {
        // predefine a list of warehouses for stub return
        List<Warehouse> expectedWarehouses = Arrays.asList(new Warehouse(), new Warehouse(), new Warehouse());

        // stub findAll func to predefined warehouse list
        when(warehouseService.findAll())
        .thenReturn(expectedWarehouses);

        // get warehouse list from controller func
        Iterable<Warehouse> warehouses = warehouseController.getAllWarehouses();

        // count the size of iterable for test
        int count = 0;
        for (Warehouse w : warehouses) {
            count++;
        }

        // ensure warehouse lists match
        Assert.assertEquals(warehouses, expectedWarehouses);
        // ensure list counts match
        Assert.assertEquals(count, 3);
    }

    @Test
    public void testCreateWarehouse() {
        // stub service func to return warehouse instance
        when(warehouseService.createWarehouse(warehouse))
        .thenReturn(warehouse);

        // get the created warehouse
        Warehouse createdWarehouse = warehouseController.createWarehouse(warehouse);

        // ensure both warehouses match
        Assert.assertEquals(createdWarehouse, warehouse);
    }

    @Test
    public void testUpdateWarehouse() {
        // get the response for updated warehouse func
        ResponseEntity<Warehouse> response = warehouseController.updateWarehouse(warehouseId, warehouse);

        // ensure response status code is OK for update
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }


    @Test
    public void testDeleteWarehouse() {
        // get the response for deleted warehouse
        ResponseEntity<Void> response = warehouseController.deleteWarehouseById(warehouseId);

        // ensure response status code is NO_CONTENT for delete
        Assert.assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }
}
