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
import com.skillstorm.project_one.Repositories.OrdersRepo;
import com.skillstorm.project_one.Services.OrdersService;

public class OrdersServiceTest {

    @Mock
    private OrdersRepo ordersRepo;

    @InjectMocks
    private OrdersService ordersService;
    private AutoCloseable closeable;

    private int orderId;

    @BeforeTest
    public void setup() {
        orderId = 1;
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterTest
    public void teardown() throws Exception {
        closeable.close();
    }

    @Test
    public void testFindAll() {
        List<Orders> expectedOrders = Arrays.asList(new Orders(), new Orders(), new Orders());

        // stub this func it uses orderreop to return all the orders in ascending order
        when(ordersRepo.findAllByOrderByIdAsc())
        .thenReturn(expectedOrders);

        // orders need to be an iterable obj
        Iterable<Orders> orders = ordersService.findAll();

        // get the size of ordres
        int count = 0;
        for(Orders o : orders) {
            count++;
        }

        // ensure orders and sizes match
        Assert.assertEquals(orders, expectedOrders);
        Assert.assertEquals(count, 3);
    }

    @Test
    public void testGetOrdersById() {
        Orders order = new Orders();
        Optional<Orders> expectedOrder = Optional.ofNullable(order);

        // stub func this returns the order by given id
        when(ordersRepo.findById(orderId))
        .thenReturn(expectedOrder);

        // orders need to be optional
        Optional<Orders> orders = ordersService.getOrdersById(orderId);

        // ensure that orders match
        Assert.assertEquals(orders, expectedOrder);
    }

    @Test
    public void testFindByStatus() {
        String status = "Pending";
        List<Orders> expectedOrders = Arrays.asList(new Orders(), new Orders());

        // stub func finds order by given status
        when(ordersRepo.findByStatus(status))
        .thenReturn(expectedOrders);

        // orders must be iterable obj
        Iterable<Orders> orders = ordersService.getOrdersByStatus(status);

        // size of iterable
        int count = 0;
        for(Orders o : orders) {
            count++;
        }

        // ensure orders and sizes match
        Assert.assertEquals(orders, expectedOrders);
        Assert.assertEquals(count, 2);
    }

    @Test
    public void testCreateOrders() {
        Orders expectedOrder = new Orders();

        // stub func returns order that was saved
        when(ordersRepo.save(expectedOrder))
        .thenReturn(expectedOrder);

        Orders order = ordersService.createOrders(expectedOrder);

        // ensure orders match
        Assert.assertEquals(order, expectedOrder);
    }

    @Test
    public void testUpdateOrders() {
        Orders order = new Orders();

        // stub func returns if order is found
        when(ordersRepo.existsById(orderId))
        .thenReturn(true);

        ordersService.updateOrders(orderId, order);

        // ensure order ids matches
        verify(ordersRepo, times(1)).existsById(orderId);
        verify(ordersRepo, times(1)).save(order);
        Assert.assertEquals(order.getId(), orderId);
    }

    @Test
    public void testDeleteOrder() {
        ordersService.deleteOrder(orderId);

        // just verify that it was called once with the right id
        verify(ordersRepo, times(1)).deleteById(orderId);
    }
}
