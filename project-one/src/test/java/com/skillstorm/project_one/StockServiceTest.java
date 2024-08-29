package com.skillstorm.project_one;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.skillstorm.project_one.DTOs.StockDTO;
import com.skillstorm.project_one.Models.Stock;
import com.skillstorm.project_one.Repositories.StockRepo;
import com.skillstorm.project_one.Services.StockService;

public class StockServiceTest {
    
    @Mock
    private StockRepo stockRepo;  

    @InjectMocks
    private StockService stockService;  
    private AutoCloseable closeable;

    @BeforeMethod
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);  
    }

    @AfterMethod
    public void teardown() throws Exception {
        closeable.close();
    }

    @Test
    public void testGetAllStockDTO() {
        List<Stock> stocks = Arrays.asList(new Stock(), new Stock(), new Stock(), new Stock());
        List<StockDTO> dtos = Arrays.asList(new StockDTO(), new StockDTO(), new StockDTO(), new StockDTO());

        when(stockRepo.findAll())
        .thenReturn(stocks);
    }
}
