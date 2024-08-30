package com.skillstorm.project_one;

import java.util.List;
import java.util.Optional;

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

import com.skillstorm.project_one.Models.Orders;
import com.skillstorm.project_one.Controllers.OrdersController;
import com.skillstorm.project_one.Services.OrdersService;

public class OrdersControllerTest {

    @Mock
    private OrdersService ordersService;

    @InjectMocks
    private OrdersController ordersController;
    private AutoCloseable closeable;

    private int orderId;
    private Orders order;

    @BeforeTest
    public void setup() {
        orderId = 1;
        order = new Orders();
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterTest
    public void teardown() throws Exception {
        closeable.close();
    }

    @Test
    public void testFindAllOrders() {
        // new order list used for stub return
        List<Orders> expectedOrders = Arrays.asList(new Orders(), new Orders(), new Orders());

        // stub findall func to return order list
        when(ordersService.findAll())
        .thenReturn(expectedOrders);

        // return func as iterable
        Iterable<Orders> orders = ordersController.findAllOrders();

        // get size of iterable
        int count = 0;
        for (Orders o : orders) {
            count++;
        }

        // ensure lists match
        Assert.assertEquals(orders, expectedOrders);

        // ensure lists have same size
        Assert.assertEquals(count, 3);
    }

    @Test
    public void testFindOrdersById() {
        order.setId(orderId);

        // since getordersbyid returns optional obj we need to convert
        Optional<Orders> expectedOrders1 = Optional.ofNullable(order);
        Optional<Orders> expectedOrders2 = Optional.ofNullable(null);

        // stub this func to return the order
        when(ordersService.getOrdersById(orderId))
        .thenReturn(expectedOrders1);

        // get the returns of as optional objs
        Optional<Orders> actual1 = ordersController.findOrdersById(orderId);
        Optional<Orders> actual2 = ordersController.findOrdersById(0);

        // ensure the orders and ids match and also the null orders match
        Assert.assertEquals(actual1, expectedOrders1);
        Assert.assertEquals(actual1.get().getId(), expectedOrders1.get().getId());
        Assert.assertEquals(actual2, expectedOrders2);
    }

    @Test
    public void testFindOrdersByStatus() {
        String status = "Pending";
        List<Orders> expectedOrders = Arrays.asList(new Orders(), new Orders());

        // stub func to return predefined list
        when(ordersService.getOrdersByStatus(status))
        .thenReturn(expectedOrders);

        // need to convert to iterable
        Iterable<Orders> orders = ordersController.findOrdersByStatus(status);

        // get size of iterable
        int count = 0;
        for (Orders o : orders) {
            count++;
        }

        // ensure both order lists are the same and their sizes are the same
        Assert.assertEquals(orders, expectedOrders);
        Assert.assertEquals(count, 2);
    }

    @Test
    public void testCreateOrders() {
        Orders inputOrder = new Orders();
        Orders expectedOrder = new Orders();

        // stub func to take in an order and out the other
        when(ordersService.createOrders(inputOrder))
        .thenReturn(expectedOrder);

        // get the stubbed return from createorders
        Orders outputOrder = ordersController.createOrders(inputOrder);

        // ensure both the orders are the same
        Assert.assertEquals(outputOrder, expectedOrder);
    }

    @Test
    public void testUpdateOrders() {
        // get the response status code from this func
        ResponseEntity<Orders> response = ordersController.updateOrders(orderId, order);

        // ensure the status code matches
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testDeleteOrderById() {
        ResponseEntity<Void> response = ordersController.deleteOrderById(orderId);

        // ensure response returns status code NO CONTENT
        Assert.assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }
}
