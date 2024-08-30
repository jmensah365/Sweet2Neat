package com.skillstorm.project_one;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.skillstorm.project_one.Controllers.GlobalExceptionHandler;

public class GlobalExceptionHandlerTest {
    
    //mocking the MethodArgumentNotValidException class
    @Mock
    MethodArgumentNotValidException methodArgumentNotValidException;
    
    //Injecting created mock into GlobalExceptionHandler
    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;
    private AutoCloseable closeable;

    //initializes the mock objects to ensure they are ready before tests are run
    @BeforeTest
    public void setUp(){
        closeable = MockitoAnnotations.openMocks(this);
    }

    //closes the closeable resource to ensure that the mock objects are cleaned up properly - prevents memory leaks
    @AfterTest
    public void teardown() throws Exception{
        closeable.close();
    }

    @Test
    public void testHandleValidationError(){
        //simulates the getMessage method for the mocked class
        when(methodArgumentNotValidException.getMessage()).thenReturn("Validation error occurred");

        ResponseEntity<Object> response = globalExceptionHandler.handleValidationError(methodArgumentNotValidException);

        //Asserting the response from the exception handler is not null
        assertNotNull(response);
        //Asserting the HttpStatus and message from the response match the expected response
        Assert.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        Assert.assertEquals(response.getBody(), "Validation error occurred");

        // Verify that getMessage() was called once 
        verify(methodArgumentNotValidException, times(1)).getMessage();
    }
}
