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

    @Mock
    private CandyService candyService;

    @InjectMocks
    private CandyController candyController;
    AutoCloseable closeable;

    @BeforeMethod
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterMethod
    public void teardown() throws Exception{
        closeable.close();
    }

    @Test
    public void testGetAllCandy() {
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
        List<Candy> candies = Arrays.asList(candy1, candy2);

        when(candyService.findAll()).thenReturn(candies);

        List<Candy> result = candyController.getAllCandy();

        assertNotNull(result);
        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getName(), "Candy1");
    }

    @Test
    public void testGetCandyById() {
        Candy candy = new Candy();
        candy.setId(1);
        candy.setName("Candy1");
        candy.setType("Type1");
        candy.setFlavor("Flavor1");
        when(candyService.findById(1)).thenReturn(Optional.of(candy));

        Optional<Candy> result = candyController.getCandyById(1);

        assertTrue(result.isPresent());
        assertEquals(result.get().getName(), "Candy1");
    }

    @Test
    public void testGetCandyByType() {
        Candy candy = new Candy();
        candy.setId(1);
        candy.setName("Candy1");
        candy.setType("Type1");
        candy.setFlavor("Flavor1");
        List<Candy> candies = Arrays.asList(candy);

        when(candyService.findByType("Type1")).thenReturn(candies);

        List<Candy> result = candyController.getCandyByType("Type1");

        assertNotNull(result);
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getType(), "Type1");
    }

    @Test
    public void testGetByFlavor() {
        Candy candy = new Candy();
        candy.setId(1);
        candy.setName("Candy1");
        candy.setType("Type1");
        candy.setFlavor("Flavor1");
        List<Candy> candies = Arrays.asList(candy);

        when(candyService.findByFlavor("Flavor1")).thenReturn(candies);

        List<Candy> result = candyController.getByFlavor("Flavor1");

        assertNotNull(result);
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getFlavor(), "Flavor1");
    }

    @Test
    public void testCreateCandy() {
        Candy candy = new Candy();
        candy.setId(1);
        candy.setName("Candy1");
        candy.setType("Type1");
        candy.setFlavor("Flavor1");
        when(candyService.createCandy(candy)).thenReturn(candy);

        Candy result = candyController.createCandy(candy);

        assertNotNull(result);
        assertEquals(result.getName(), "Candy1");
    }

    @Test
    public void testUpdateCandy() {
        Candy candy = new Candy();
        candy.setId(1);
        candy.setName("Candy1");
        candy.setType("Type1");
        candy.setFlavor("Flavor1");

        ResponseEntity<Candy> response = candyController.updateCandy(1, candy);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        verify(candyService, times(1)).updateCandy(1, candy);
    }

    @Test
    public void testDeleteCandyById() {
        ResponseEntity<Void> response = candyController.deleteCandyById(1);

        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
        verify(candyService, times(1)).deleteCandy(1);
    }
}