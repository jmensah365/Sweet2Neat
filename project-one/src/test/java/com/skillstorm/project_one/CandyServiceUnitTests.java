package com.skillstorm.project_one;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterTest;

import com.skillstorm.project_one.Models.Candy;
import com.skillstorm.project_one.Repositories.CandyRepo;
import com.skillstorm.project_one.Services.CandyService;

class CandyServiceUnitTests {

    @Mock
    private CandyRepo candyRepo;  // Mocked repository

    @InjectMocks
    private CandyService candyService;  // Service under test
    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);  // Initialize mocks
    }

    @AfterTest
    public void teardown() throws Exception {
        closeable.close();
    }

    @Test
    void testFindAll() {
        //Given
        List<Candy> expectedCandies = Arrays.asList(new Candy(), new Candy());
        //When
        when(candyRepo.findAllByOrderByIdAsc()).thenReturn(expectedCandies);

        List<Candy> actualCandies = candyService.findAll();

        assertEquals(expectedCandies, actualCandies);
        
        //verify
        verify(candyRepo, times(1)).findAllByOrderByIdAsc();
    }

    @Test
    void testFindById() {
        // Given
        Candy candy = new Candy();
        candy.setId(1);

        //When
        when(candyRepo.findById(1)).thenReturn(Optional.of(candy));

        Optional<Candy> result = candyService.findById(1);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getCandyId());
        
        //Verify
        verify(candyRepo, times(1)).findById(1);
    }

    @Test
    void testFindByFlavor() {
        //Given
        List<Candy> expectedCandies = Arrays.asList(new Candy(), new Candy());

        //When
        when(candyRepo.findByFlavor("chocolate")).thenReturn(expectedCandies);

        List<Candy> actualCandies = candyService.findByFlavor("chocolate");

        
        assertEquals(expectedCandies, actualCandies);
        
        //Verify
        verify(candyRepo, times(1)).findByFlavor("chocolate");
    }

    @Test
    void testFindByType() {
        //Given
        List<Candy> expectedCandies = Arrays.asList(new Candy(), new Candy());

        //When
        when(candyRepo.findByType("Gummy Candy")).thenReturn(expectedCandies);

        List<Candy> actualCandies = candyService.findByType("Gummy Candy");

        
        assertEquals(expectedCandies, actualCandies);
        
        //Verify
        verify(candyRepo, times(1)).findByType("Gummy Candy");
    }


    @Test
    void testCreateCandy() {
        //Given
        Candy candy = new Candy();

        //When
        when(candyRepo.save(candy)).thenReturn(candy);

        Candy createdCandy = candyService.createCandy(candy);

        assertEquals(candy, createdCandy);
        
        //Verify
        verify(candyRepo, times(1)).save(candy);
    }

    @Test
    void testUpdateCandy() {
        // Given
        Candy candy = new Candy();
        candy.setId(1);

        //When
        when(candyRepo.existsById(1)).thenReturn(true);
        when(candyRepo.save(candy)).thenReturn(candy);

        candyService.updateCandy(1, candy);

        //Verify
        verify(candyRepo, times(1)).existsById(1);
        verify(candyRepo, times(1)).save(candy);
    }

    @Test
    void testUpdateCandyThrowsException() {
        //Given
        Candy candy = new Candy();

        //When
        when(candyRepo.existsById(1)).thenReturn(false);

        assertThrows(NoSuchElementException.class, () -> candyService.updateCandy(1, candy));
        
        //Verify
        verify(candyRepo, times(1)).existsById(1);
        verify(candyRepo, never()).save(any(Candy.class));
    }

    @Test
    void testDeleteCandy() {
        candyService.deleteCandy(1);

        //Verify
        verify(candyRepo, times(1)).deleteById(1);
    }
}
