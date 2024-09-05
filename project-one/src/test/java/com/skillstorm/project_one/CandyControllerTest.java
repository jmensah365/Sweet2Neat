package com.skillstorm.project_one;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.skillstorm.project_one.Controllers.CandyController;
import com.skillstorm.project_one.Models.Candy;
import com.skillstorm.project_one.Services.CandyService;

public class CandyControllerTest {

    //Creating Mock of Candy Service
    @Mock
    private CandyService candyService;

    //Injecting mock into candy controller
    @InjectMocks
    private CandyController candyController;
    AutoCloseable closeable;

    //initializes the mock objects to ensure they are ready before each test is run
    @BeforeMethod
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    //closes the closeable resource to ensure that the mock objects are cleaned up properly - prevents memory leaks
    @AfterMethod
    public void teardown() throws Exception{
        closeable.close();
    }

    //Testing the getAllCandy method
    @Test
    public void testGetAllCandy() {
        //Creating two new candy objects
        Candy candy1 = new Candy();
        Candy candy2 = new Candy();
        candy1.setId(1);
        candy1.setName("Candy1");
        candy1.setType("Type1");
        candy1.setFlavor("Flavor1");
        
        candy2.setId(2);
        candy2.setName("Candy2");
        candy2.setType("Type2");
        candy2.setFlavor("Flavor2");

        //Given
        List<Candy> candies = Arrays.asList(candy1, candy2);

        //when - simulating the behavior of findAll()
        when(candyService.findAll()).thenReturn(candies);

        List<Candy> result = candyController.getAllCandy();

        //Asserting that the given response matches the expected response
        assertNotNull(result);
        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getName(), "Candy1");
    }

    //Testing the getCandyById method in the controller class
    @Test
    public void testGetCandyById() {
        //Creating a new candy object
        Candy candy = new Candy();
        candy.setId(1);
        candy.setName("Candy1");
        candy.setType("Type1");
        candy.setFlavor("Flavor1");

        //when (stubbing) - simulating the behavior of findById()
        when(candyService.findById(1)).thenReturn(Optional.of(candy));

        //candy controller response
        Optional<Candy> result = candyController.getCandyById(1);

        //Asserting expected response matches given response
        assertTrue(result.isPresent());
        assertEquals(result.get().getName(), "Candy1");
    }

    //Testing the getCandyByType method 
    @Test
    public void testGetCandyByType() {
        //creating a new candy object
        Candy candy = new Candy();
        candy.setId(1);
        candy.setName("Candy1");
        candy.setType("Type1");
        candy.setFlavor("Flavor1");
        List<Candy> candies = Arrays.asList(candy);

        //when (stubbing) - simulating the behavior of findByType
        when(candyService.findByType("Type1")).thenReturn(candies);

        //calling the method we are testing
        List<Candy> result = candyController.getCandyByType("Type1");

        //Verifying the result matches the expected response
        assertNotNull(result);
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getType(), "Type1");
    }

    //Testing the getByFlavor method
    @Test
    public void testGetByFlavor() {
        //creating a new candy object
        Candy candy = new Candy();
        candy.setId(1);
        candy.setName("Candy1");
        candy.setType("Type1");
        candy.setFlavor("Flavor1");
        List<Candy> candies = Arrays.asList(candy);

        //when(stubbing) - simulating the behavior of findByFlavor()
        when(candyService.findByFlavor("Flavor1")).thenReturn(candies);

        //calling the method we are testing from the controller class
        List<Candy> result = candyController.getByFlavor("Flavor1");

        //Verifying the result matches the expected response
        assertNotNull(result);
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getFlavor(), "Flavor1");
    }

    //Testing the CREATE method
    @Test
    public void testCreateCandy() {
        //creating a new candy
        Candy candy = new Candy();
        candy.setId(1);
        candy.setName("Candy1");
        candy.setType("Type1");
        candy.setFlavor("Flavor1");

        //when(stubbing) - simulating the behavior of the CREATE operation
        when(candyService.createCandy(candy)).thenReturn(candy);

        //calling the createCandy method we are testing
        Candy result = candyController.createCandy(candy);

        //Verifying the result matches the expected response
        assertNotNull(result);
        assertEquals(result.getName(), "Candy1");
    }

    //Testing the UPDATE operation
    @Test
    public void testUpdateCandy() {
        //creating a new candy
        Candy candy = new Candy();
        candy.setId(1);
        candy.setName("Candy1");
        candy.setType("Type1");
        candy.setFlavor("Flavor1");

        //calling the updateCandy method we are testing
        ResponseEntity<Candy> response = candyController.updateCandy(1, candy);

        //verifying the expected response matches the given response
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        //verifying the method updateCandy was called once with 1 and candy as its params
        verify(candyService, times(1)).updateCandy(1, candy);
    }

    //Testing the DELETE operation
    @Test
    public void testDeleteCandyById() {
        //calling the deleteCandyById method
        ResponseEntity<Void> response = candyController.deleteCandyById(1);

        //verifying the expected response matches the given response
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
        //verifying the method deleteCandy is called once with 1 as its param
        verify(candyService, times(1)).deleteCandy(1);
    }
}