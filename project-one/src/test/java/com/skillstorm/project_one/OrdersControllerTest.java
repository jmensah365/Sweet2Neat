package com.skillstorm.project_one;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.apache.catalina.connector.Response;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.skillstorm.project_one.Models.Orders;
import com.skillstorm.project_one.Controllers.OrdersController;
import com.skillstorm.project_one.Services.OrdersService;

public class OrdersControllerTest {

    @Mock
    private OrdersService ordersService;

    @InjectMocks
    private OrdersController ordersController;
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
    public void testFindAllOrders() {
        List<Orders> expectedOrders = Arrays.asList(new Orders(), new Orders(), new Orders());
        when(ordersService.findAll())
        .thenReturn(expectedOrders);

        Iterable<Orders> orders = ordersController.findAllOrders();

        int count = 0;
        for (Orders o : orders) {
            count++;
        }

        Assert.assertEquals(orders, expectedOrders);
        Assert.assertEquals(count, 3);
    }

    @Test
    public void testFindOrdersById() {
        int id = 1;
        Orders order = new Orders();
        order.setId(id);

        Optional<Orders> expectedOrders1 = Optional.ofNullable(order);
        Optional<Orders> expectedOrders2 = Optional.ofNullable(null);

        when(ordersService.getOrdersById(id))
        .thenReturn(expectedOrders1);

        Optional<Orders> actual1 = ordersController.findOrdersById(id);
        Optional<Orders> actual2 = ordersController.findOrdersById(0);

        Assert.assertEquals(actual1, expectedOrders1);
        Assert.assertEquals(actual2, expectedOrders2);

    }

    @Test
    public void testFindOrdersByStatus() {
        String status = "Pending";
        List<Orders> expectedOrders = Arrays.asList(new Orders(), new Orders());

        when(ordersService.getOrdersByStatus(status))
        .thenReturn(expectedOrders);

        Iterable<Orders> orders = ordersController.findOrdersByStatus(status);

        int count = 0;
        for (Orders o : orders) {
            count++;
        }

        Assert.assertEquals(orders, expectedOrders);
        Assert.assertEquals(count, 2);
    }

    @Test
    public void testCreateOrders() {
        Orders inputOrder = new Orders();
        Orders outputOrder = new Orders();

        when(ordersService.createOrders(inputOrder))
        .thenReturn(outputOrder);

        Orders expectedOrder = ordersController.createOrders(inputOrder);

        Assert.assertEquals(outputOrder, expectedOrder);
    }

    @Test
    public void testUpdateOrders() {
        int id = 1;
        Orders order = new Orders();

        // ordersService.updateOrders(id, order);

        ResponseEntity<Orders> response = ordersController.updateOrders(id, order);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDeleteOrderById() {
        int id = 1;
        
        // ordersService.deleteOrder(id);

        ResponseEntity<Void> response = ordersController.deleteOrderById(id);

        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
