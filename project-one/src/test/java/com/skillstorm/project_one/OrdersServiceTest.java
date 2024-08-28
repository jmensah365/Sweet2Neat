package com.skillstorm.project_one;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

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
        
    }

}
