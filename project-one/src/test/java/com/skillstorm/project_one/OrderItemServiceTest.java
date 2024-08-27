package com.skillstorm.project_one;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OrderItemServiceTest {

    @Mock
    private OrdersRepo orderRepo;

    @Mock
    private CandyRepo candyRepo;

    @Mock
    private OrderItemRepo orderItemRepo;

    @InjectMocks
    private OrderItemService orderItemService;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        // Arrange
        OrderItem orderItem = new OrderItem();
        orderItem.setId(1);
        when(orderItemRepo.findAll()).thenReturn(Arrays.asList(orderItem));

        Candy candy = new Candy();
        candy.setId(1);
        orderItem.setCandy(candy);

        Orders orders = new Orders();
        orders.setId(1);
        orderItem.setOrders(orders);

        // Act
        List<OrderItemDTO> result = orderItemService.findAll();

        // Assert
        assertEquals(1, result.size());
        assertEquals((Integer) 1, result.get(0).getId());
        verify(orderItemRepo, times(1)).findAll();
    }

    @Test
    public void testFindByOrderId() {
        // Arrange
        OrderItem orderItem = new OrderItem();
        orderItem.setId(1);
        when(orderItemRepo.findByOrderId(1)).thenReturn(Arrays.asList(orderItem));

        Candy candy = new Candy();
        candy.setId(1);
        orderItem.setCandy(candy);

        Orders orders = new Orders();
        orders.setId(1);
        orderItem.setOrders(orders);

        // Act
        List<OrderItemDTO> result = orderItemService.findByOrderId(1);

        // Assert
        assertEquals(1, result.size());
        assertEquals((Integer) 1, result.get(0).getId());
        verify(orderItemRepo, times(1)).findByOrderId(1);
    }

    @Test
    public void testCreateOrderItem_Success() {
        // Arrange
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

        // Act
        OrderItem result = orderItemService.createOrderItem(dto);

        // Assert
        assertNotNull(result);
        verify(candyRepo, times(1)).findById(1);
        verify(orderRepo, times(1)).findById(1);
        verify(orderItemRepo, times(1)).save(any(OrderItem.class));
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void testCreateOrderItem_CandyNotFound() {
        // Arrange
        OrderItemDTO dto = new OrderItemDTO();
        dto.setCandyId(1);
        dto.setOrderId(1);

        when(candyRepo.findById(1)).thenReturn(Optional.empty());

        // Act
        orderItemService.createOrderItem(dto);
    }

    @Test
    public void testUpdateOrderItem_Success() {
        // Arrange
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

        // Act
        orderItemService.updateOrderItem(1, dto);

        // Assert
        verify(candyRepo, times(1)).findById(1);
        verify(orderRepo, times(1)).findById(1);
        verify(orderItemRepo, times(1)).save(any(OrderItem.class));
    }

    @Test
    public void testDeleteOrderItem() {
        // Arrange
        int id = 1;

        // Act
        orderItemService.deleteOrderItem(id);

        // Assert
        verify(orderItemRepo, times(1)).deleteById(id);
    }
}
