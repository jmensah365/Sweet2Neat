package com.skillstorm.project_one;

import java.math.BigDecimal;
import java.util.List;
import java.util.Arrays;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

import com.skillstorm.project_one.Controllers.OrderItemController;
import com.skillstorm.project_one.DTOs.OrderItemDTO;
import com.skillstorm.project_one.Models.Candy;
import com.skillstorm.project_one.Models.OrderItem;
import com.skillstorm.project_one.Models.Orders;
import com.skillstorm.project_one.Services.OrderItemService;

public class OrderItemControllerTest {
    @Mock
    private OrderItemService orderItemService;

    @InjectMocks
    private OrderItemController orderItemController;
    AutoCloseable closeable;

    @BeforeMethod
    public void setUp(){
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterMethod
    public void teardown() throws Exception{
        closeable.close();
    }

    @Test
    public void testFindAll(){
        OrderItemDTO orderItem1 = new OrderItemDTO();
        OrderItemDTO orderItem2 = new OrderItemDTO();


        orderItem1.setId(1);
        orderItem1.setPrice(new BigDecimal(15.99));
        orderItem1.setQuantity(20);

        orderItem2.setId(2);
        orderItem2.setPrice(new BigDecimal(20.99));
        orderItem2.setQuantity(25);

        List<OrderItemDTO> orderItems = Arrays.asList(orderItem1, orderItem2);

        when(orderItemService.findAll()).thenReturn(orderItems);

        assertNotNull(orderItems);
        assertEquals(orderItems.size(), 2);
        assertEquals(orderItems.get(0).getId(), 1);
        assertEquals(orderItems.get(1).getId(), 2);
    }
        @Test
    public void testCreateOrderItem() {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setId(1);
        orderItemDTO.setPrice(new BigDecimal(15.99));
        orderItemDTO.setQuantity(20);

        OrderItem createdOrderItem = new OrderItem();
        createdOrderItem.setId(1);
        createdOrderItem.setPrice(new BigDecimal(15.99));
        createdOrderItem.setQuantity(20);

        when(orderItemService.createOrderItem(orderItemDTO)).thenReturn(createdOrderItem);

        ResponseEntity<OrderItem> response = orderItemController.createOrderItem(orderItemDTO);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getId(), 1);
    }

    @Test
    public void testUpdateOrderItem() {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setId(1);
        orderItemDTO.setPrice(new BigDecimal(15.99));
        orderItemDTO.setQuantity(20);

        ResponseEntity<Void> response = orderItemController.updateOrderItem(1, orderItemDTO);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        verify(orderItemService, times(1)).updateOrderItem(1, orderItemDTO);
    }

    @Test
    public void testDeleteOrderItem() {
        ResponseEntity<Void> response = orderItemController.deleteOrderItem(1);

        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
        verify(orderItemService, times(1)).deleteOrderItem(1);
    }
}
