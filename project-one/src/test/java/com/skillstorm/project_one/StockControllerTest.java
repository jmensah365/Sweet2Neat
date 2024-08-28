package com.skillstorm.project_one;

import java.util.List;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.skillstorm.project_one.Controllers.StockController;
import com.skillstorm.project_one.Services.StockService;
import com.skillstorm.project_one.DTOs.StockDTO;
import com.skillstorm.project_one.Models.Stock;

public class StockControllerTest {

    @Mock
    private StockService stockService;

    @InjectMocks
    private StockController stockController;
    private AutoCloseable closeable;

    @BeforeTest
    public void setup() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterTest
    public void teardown() throws Exception {
        closeable.close();
    }

    @Test
    public void testFindAllStocks() {
        List<StockDTO> outputStockDTOs = Arrays.asList(new StockDTO(), new StockDTO());

        when(stockService.getAllStocksDTO())
        .thenReturn(outputStockDTOs);

        ResponseEntity<List<StockDTO>> response = stockController.findAllStocks();

        Assert.assertEquals(response.getBody(), outputStockDTOs);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody().size(), 2);
    }

    @Test
    public void testGetStocksByWarehouse() {
        int warehouseId = 7;
        List<StockDTO> outputStockDTOs = Arrays.asList(new StockDTO(), new StockDTO(), new StockDTO());

        when(stockService.getStocksByWarehouse(warehouseId))
        .thenReturn(outputStockDTOs);

        ResponseEntity<List<StockDTO>> response = stockController.getStocksByWarehouse(warehouseId);

        Assert.assertEquals(response.getBody(), outputStockDTOs);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody().size(), 3);
    }

    @Test
    public void testCreateStock() {
        Stock stock = new Stock();
        StockDTO stockDTO = new StockDTO();

        when(stockService.createStock(stockDTO))
        .thenReturn(stock);

        ResponseEntity<Stock> response = stockController.createStock(stockDTO);

        Assert.assertEquals(response.getBody(), stock);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testUpdateStock() {
        int stockId = 15;
        StockDTO stockDTO = new StockDTO();

        ResponseEntity<Void> response = stockController.updateStock(stockId, stockDTO);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testDeleteStock() {
        int stockId = 15;

        ResponseEntity<Void> response = stockController.deleteStock(stockId);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

}
