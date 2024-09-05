package com.skillstorm.project_one;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.skillstorm.project_one.DTOs.OrderItemDTO;
import com.skillstorm.project_one.Models.Candy;
import com.skillstorm.project_one.Models.OrderItem;
import com.skillstorm.project_one.Models.Orders;
import com.skillstorm.project_one.Repositories.CandyRepo;
import com.skillstorm.project_one.Repositories.OrderItemRepo;
import com.skillstorm.project_one.Repositories.OrdersRepo;
import com.skillstorm.project_one.Services.OrderItemService;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OrderItemServiceTest {

    //Create mocks of orderRepo, candyRepo, orderItemRepo dependencies
    @Mock
    private OrdersRepo orderRepo;

    @Mock
    private CandyRepo candyRepo;

    @Mock
    private OrderItemRepo orderItemRepo;

    //Inject mocks into OrderItemService
    @InjectMocks
    private OrderItemService orderItemService; //item under test

    AutoCloseable closeable;

    //initializes the mock objects to ensure they are ready before tests are run
    @BeforeMethod
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    //closes the closeable resource to ensure that the mock objects are cleaned up properly - prevents memory leaks
    @AfterMethod
    public void teardown() throws Exception{
        closeable.close();
    }

    @Test
    public void testFindAll() {
        // Given: Create and initialize OrderItem, Candy, Orders entities
        OrderItem orderItem = new OrderItem();
        orderItem.setId(1);
        when(orderItemRepo.findAll()).thenReturn(Arrays.asList(orderItem));

        Candy candy = new Candy();
        candy.setId(1);
        orderItem.setCandy(candy);

        Orders orders = new Orders();
        orders.setId(1);
        orderItem.setOrders(orders);

        // When: findAll() is called
        List<OrderItemDTO> result = orderItemService.findAll();

        // Verify the result should contain the OrderItemDTO with the same ID and verify findAll() was called
        assertEquals(1, result.size());
        assertEquals((Integer) 1, result.get(0).getId());
        verify(orderItemRepo, times(1)).findAll();
    }

    @Test
    public void testFindByOrderId() {
        // Given: Create and initialize OrderItem, Candy, Orders entities
        OrderItem orderItem = new OrderItem();
        orderItem.setId(1);
        when(orderItemRepo.findByOrderId(1)).thenReturn(Arrays.asList(orderItem));

        Candy candy = new Candy();
        candy.setId(1);
        orderItem.setCandy(candy);

        Orders orders = new Orders();
        orders.setId(1);
        orderItem.setOrders(orders);

        // When: findByOrderId(1) is called
        List<OrderItemDTO> result = orderItemService.findByOrderId(1);

        // Verify the result should contain the OrderItemDTO with the same ID and verify findByOrderId(1) was called
        assertEquals(1, result.size());
        assertEquals((Integer) 1, result.get(0).getId());
        verify(orderItemRepo, times(1)).findByOrderId(1);
    }

    @Test
    public void testCreateOrderItem_Success() {
        // Given: Create OrderItemDTO, Candy and Order entities
        OrderItemDTO dto = new OrderItemDTO();
        dto.setCandyId(1);
        dto.setOrderId(1);
        dto.setPrice(new BigDecimal(10));
        dto.setQuantity(5);

        Candy candy = new Candy();
        Orders order = new Orders();

        when(candyRepo.findById(1)).thenReturn(Optional.of(candy));
        when(orderRepo.findById(1)).thenReturn(Optional.of(order));
        when(orderItemRepo.save(any(OrderItem.class))).thenReturn(new OrderItem());

        // When: createOrderItem() is called
        OrderItem result = orderItemService.createOrderItem(dto);

        // Verify the result should not be null, and verify the repositories were accessed
        assertNotNull(result);
        verify(candyRepo, times(1)).findById(1);
        verify(orderRepo, times(1)).findById(1);
        verify(orderItemRepo, times(1)).save(any(OrderItem.class));
    }

    // @Test(expectedExceptions = NoSuchElementException.class)
    // public void testCreateOrderItem_OrderNotFound() {
    //     // Given: A valid OrderItemDTO with Order ID 1, but the Order does not exist
    //     OrderItemDTO dto = new OrderItemDTO();
    //     dto.setCandyId(1);
    //     dto.setOrderId(1);

    //     when(orderRepo.findById(1)).thenReturn(Optional.empty());

    //     // When: createOrderItem() is called
    //     // Then: A NoSuchElementException is expected
    //     orderItemService.createOrderItem(dto);
    // }

    @Test
    public void testUpdateOrderItem_Success() {
        // Given: create OrderItemDTO, Candy, Orders entities
        OrderItemDTO dto = new OrderItemDTO();
        dto.setId(1);
        dto.setCandyId(1);
        dto.setOrderId(1);
        dto.setPrice(new BigDecimal(15));
        dto.setQuantity(10);

        Candy candy = new Candy();
        Orders order = new Orders();

        when(candyRepo.findById(1)).thenReturn(Optional.of(candy));
        when(orderRepo.findById(1)).thenReturn(Optional.of(order));
        when(orderItemRepo.findById(1)).thenReturn(Optional.of(new OrderItem()));
        when(orderItemRepo.save(any(OrderItem.class))).thenReturn(new OrderItem());

        // When: updateOrderItem(1, dto) is called
        orderItemService.updateOrderItem(1, dto);

        // Verify the repositories were accessed and save() was called
        verify(candyRepo, times(1)).findById(1);
        verify(orderRepo, times(1)).findById(1);
        verify(orderItemRepo, times(1)).save(any(OrderItem.class));
    }

    @Test
    public void testDeleteOrderItem() {
        // Given: An existing OrderItem with ID 1
        int id = 1;

        // When: deleteOrderItem(1) is called
        orderItemService.deleteOrderItem(id);

        // Verify that deleteById(1) was called
        verify(orderItemRepo, times(1)).deleteById(id);
    }
}