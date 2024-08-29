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
        List<Orders> expectedOrders = Arrays.asList(new Orders(), new Orders(), new Orders());

        when(ordersRepo.findAllByOrderByIdAsc())
        .thenReturn(expectedOrders);

        Iterable<Orders> orders = ordersService.findAll();

        int count = 0;
        for(Orders o : orders) {
            count++;
        }

        Assert.assertEquals(orders, expectedOrders);
        Assert.assertEquals(3, count);
    }

    @Test
    public void testGetOrdersById() {
        int orderId = 1;
        Orders order = new Orders();
        Optional<Orders> expectedOrder = Optional.ofNullable(order);

        when(ordersRepo.findById(orderId))
        .thenReturn(expectedOrder);

        Optional<Orders> orders = ordersService.getOrdersById(orderId);

        Assert.assertEquals(orders, expectedOrder);
    }

    @Test
    public void testFindByStatus() {
        String status = "Pending";
        List<Orders> expectedOrders = Arrays.asList(new Orders(), new Orders());

        when(ordersRepo.findByStatus(status))
        .thenReturn(expectedOrders);

        Iterable<Orders> orders = ordersService.getOrdersByStatus(status);

        int count = 0;
        for(Orders o : orders) {
            count++;
        }

        Assert.assertEquals(orders, expectedOrders);
        Assert.assertEquals(2, count);
    }

    @Test
    public void testCreateOrders() {
        Orders expectedOrder = new Orders();

        when(ordersRepo.save(expectedOrder))
        .thenReturn(expectedOrder);

        Orders order = ordersService.createOrders(expectedOrder);

        Assert.assertEquals(order, expectedOrder);
    }

    @Test
    public void testUpdateOrders() {
        int orderId = 1;
        Orders order = new Orders();

        when(ordersRepo.existsById(orderId))
        .thenReturn(true);

        ordersService.updateOrders(orderId, order);

        verify(ordersRepo, times(1)).existsById(orderId);
        verify(ordersRepo, times(1)).save(order);
        Assert.assertEquals(order.getId(), orderId);
    }

    @Test
    public void testDeleteOrder() {
        int orderId = 1;

        ordersService.deleteOrder(orderId);

        verify(ordersRepo, times(1)).deleteById(orderId);
    }
}
