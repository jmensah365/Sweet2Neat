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
import com.skillstorm.project_one.Models.OrderItem;
import com.skillstorm.project_one.Services.OrderItemService;

public class OrderItemControllerTest {
    //Creating a mock instance of the OrderItemService
    @Mock
    private OrderItemService orderItemService;

    // Injecting the mocked service into OrderItemController
    @InjectMocks
    private OrderItemController orderItemController;
    AutoCloseable closeable;

    //initializes the mock objects to ensure they are ready before tests are run
    @BeforeMethod
    public void setUp(){
        closeable = MockitoAnnotations.openMocks(this);
    }

    //closes the closeable resource to ensure that the mock objects are cleaned up properly - prevents memory leaks
    @AfterMethod
    public void teardown() throws Exception{
        closeable.close();
    }


    @Test
    public void testFindAll(){
        // Given: Create two OrderItemDTO objects to represent the data to be returned
        OrderItemDTO orderItem1 = new OrderItemDTO();
        OrderItemDTO orderItem2 = new OrderItemDTO();

        orderItem1.setId(1);
        orderItem1.setPrice(new BigDecimal(15.99));
        orderItem1.setQuantity(20);

        orderItem2.setId(2);
        orderItem2.setPrice(new BigDecimal(20.99));
        orderItem2.setQuantity(25);

        // Given: Create a list of these OrderItemDTOs
        List<OrderItemDTO> orderItems = Arrays.asList(orderItem1, orderItem2);

        // When: Mock the service's findAll method to return the list of OrderItemDTOs
        when(orderItemService.findAll()).thenReturn(orderItems);

        // When: Mocking the controllers findAll()
        ResponseEntity<Iterable<OrderItemDTO>> response = orderItemController.findAll();

        // Assert: Verify that the list is not null, contains two items, and their IDs are correct
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(((List<OrderItemDTO>) response.getBody()).size(), 2); // Check if the list size is 2
    }

    @Test
    public void testCreateOrderItem() {
        // Given: Create an OrderItemDTO object to represent the input
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setId(1);
        orderItemDTO.setPrice(new BigDecimal(15.99));
        orderItemDTO.setQuantity(20);

        // Given: Create an OrderItem object to represent the output from the service
        OrderItem createdOrderItem = new OrderItem();
        createdOrderItem.setId(1);
        createdOrderItem.setPrice(new BigDecimal(15.99));
        createdOrderItem.setQuantity(20);

        // When: Mock the service's createOrderItem method to return the created OrderItem
        when(orderItemService.createOrderItem(orderItemDTO)).thenReturn(createdOrderItem);

        // Act: Call the controller's createOrderItem method
        ResponseEntity<OrderItem> response = orderItemController.createOrderItem(orderItemDTO);

        // Assert: Verify that the response status is OK, the body is not null, and the ID is correct
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getId(), 1);
    }

    @Test
    public void testUpdateOrderItem() {
        // Given: Create an OrderItemDTO object
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setId(1);
        orderItemDTO.setPrice(new BigDecimal(15.99));
        orderItemDTO.setQuantity(20);

        // Calling the controller's updateOrderItem method
        ResponseEntity<Void> response = orderItemController.updateOrderItem(1, orderItemDTO);

        // Assert: Verify that the response status is OK
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        // Verify: Ensure that the service's updateOrderItem method was called exactly once with the correct parameters
        verify(orderItemService, times(1)).updateOrderItem(1, orderItemDTO);
    }

    @Test
    public void testDeleteOrderItem() {
        // Calling the controller's deleteOrderItem method
        ResponseEntity<Void> response = orderItemController.deleteOrderItem(1);

        // Assert: Verify that the response status is NO_CONTENT
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);

        // Verify: Ensure that the service's deleteOrderItem method was called exactly once with the correct ID
        verify(orderItemService, times(1)).deleteOrderItem(1);
    }
}