package com.skillstorm.project_one;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.skillstorm.project_one.Models.Candy;
import com.skillstorm.project_one.Repositories.CandyRepo;
import com.skillstorm.project_one.Services.CandyService;

public class CandyServiceTests {

    //mocks the candy repo
    @Mock
    private CandyRepo candyRepo;

    //injects the CandyRepo into the CandyService
    @InjectMocks
    private CandyService candyService;  // Service under test
    private AutoCloseable closeable;

    //initializes the mock objects to ensure they are ready before tests are run
    @BeforeMethod
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this); 
    }

    //closes the closeable resource to ensure that the mock objects are cleaned up properly - prevents memory leaks
    @AfterMethod
    public void teardown() throws Exception {
        closeable.close();
    }

    @Test
    void testFindAll() {
        // Given: Prepare a list of Candy objects that the repository is expected to return
        List<Candy> expectedCandies = Arrays.asList(new Candy(), new Candy());
    
        // When: Mock the repository's findAllByOrderByIdAsc method to return the prepared list
        when(candyRepo.findAllByOrderByIdAsc()).thenReturn(expectedCandies);
    
        // Call the service method to get the list of candies
        List<Candy> actualCandies = candyService.findAll();
    
        // Assert: Check that the actual list matches the expected list
        Assert.assertEquals(expectedCandies, actualCandies);
    
        // Verify: Ensure that the repository's method was called exactly once
        verify(candyRepo, times(1)).findAllByOrderByIdAsc();
    }
    
    @Test
    void testFindById() {
        // Given: Create a Candy object with a specific ID
        Candy candy = new Candy();
        candy.setId(1);
    
        // When: Mock the repository's findById method to return the Candy wrapped in an Optional
        when(candyRepo.findById(1)).thenReturn(Optional.of(candy));
    
        // Call the service method to find the candy by its ID
        Optional<Candy> result = candyService.findById(1);
    
        // Assert: Check that the result is present and the ID matches
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(1, result.get().getCandyId());
    
        // Verify: Ensure that the repository's findById method was called exactly once
        verify(candyRepo, times(1)).findById(1);
    }
    
    @Test
    void testFindByFlavor() {
        // Given: Prepare a list of Candy objects that match the flavor
        List<Candy> expectedCandies = Arrays.asList(new Candy(), new Candy());
    
        // When: Mock the repository's findByFlavor method to return the list of candies
        when(candyRepo.findByFlavor("chocolate")).thenReturn(expectedCandies);
    
        // Call the service method to find candies by flavor
        List<Candy> actualCandies = candyService.findByFlavor("chocolate");
    
        // Assert: Check that the actual list matches the expected list
        Assert.assertEquals(expectedCandies, actualCandies);
    
        // Verify: Ensure that the repository's findByFlavor method was called exactly once
        verify(candyRepo, times(1)).findByFlavor("chocolate");
    }
    
    @Test
    void testFindByType() {
        // Given: Prepare a list of Candy objects that match the type
        List<Candy> expectedCandies = Arrays.asList(new Candy(), new Candy());
    
        // When: Mock the repository's findByType method to return the list of candies
        when(candyRepo.findByType("Gummy Candy")).thenReturn(expectedCandies);
    
        // Call the service method to find candies by type
        List<Candy> actualCandies = candyService.findByType("Gummy Candy");
    
        // Assert: Check that the actual list matches the expected list
        Assert.assertEquals(expectedCandies, actualCandies);
    
        // Verify: Ensure that the repository's findByType method was called exactly once
        verify(candyRepo, times(1)).findByType("Gummy Candy");
    }
    
    @Test
    void testCreateCandy() {
        // Given: Create a new Candy object
        Candy candy = new Candy();
    
        // When: Mock the repository's save method to return the same Candy object
        when(candyRepo.save(candy)).thenReturn(candy);
    
        // Call the service method to create a new candy
        Candy createdCandy = candyService.createCandy(candy);
    
        // Assert: Check that the created candy matches the original candy
        Assert.assertEquals(candy, createdCandy);
    
        // Verify: Ensure that the repository's save method was called exactly once
        verify(candyRepo, times(1)).save(candy);
    }
    
    @Test
    void testUpdateCandy() {
        // Given: Create a Candy object with a specific ID
        Candy candy = new Candy();
        candy.setId(1);
    
        // When: Mock the repository's existsById method to return true, 
        // and the save method to return the updated candy
        when(candyRepo.existsById(1)).thenReturn(true);
        when(candyRepo.save(candy)).thenReturn(candy);
    
        // Act: Call the service method to update the candy
        candyService.updateCandy(1, candy);
    
        // Verify: Ensure that the repository's existsById and save methods were called exactly once
        verify(candyRepo, times(1)).existsById(1);
        verify(candyRepo, times(1)).save(candy);
    }
    
    @Test
    void testUpdateCandyThrowsException() {
        // Given: Create a new Candy object
        Candy candy = new Candy();
    
        // When: Mock the repository's existsById method to return false
        when(candyRepo.existsById(1)).thenReturn(false);
    
        // Assert: Verify that the service method throws a NoSuchElementException
        Assert.assertThrows(NoSuchElementException.class, () -> candyService.updateCandy(1, candy));
    
        // Verify: Ensure that existsById is called once with 1 as its param, but save was never called
        verify(candyRepo, times(1)).existsById(1);
        verify(candyRepo, never()).save(any(Candy.class));
    }
    
    @Test
    void testDeleteCandy() {
        // Act: Call the service method to delete a candy by its ID
        candyService.deleteCandy(1);
    
        // Verify: Ensure that the repository's deleteById method was called exactly once with 1 as its param
        verify(candyRepo, times(1)).deleteById(1);
    }
}