package com.skillstorm.project_one;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.skillstorm.project_one.DTOs.StockDTO;
import com.skillstorm.project_one.Models.Candy;
import com.skillstorm.project_one.Models.Stock;
import com.skillstorm.project_one.Models.Warehouse;
import com.skillstorm.project_one.Repositories.CandyRepo;
import com.skillstorm.project_one.Repositories.StockRepo;
import com.skillstorm.project_one.Repositories.WarehouseRepo;
import com.skillstorm.project_one.Services.StockService;

public class StockServiceTest {
    
    @Mock
    private StockRepo stockRepo;  
    
    @Mock
    private CandyRepo candyRepo;

    @Mock
    private WarehouseRepo warehouseRepo;

    @InjectMocks
    private StockService stockService;  
    private AutoCloseable closeable;

    private Candy candy1;
    private Candy candy2;
    private Warehouse warehouse1;
    private Warehouse warehouse2;
    private Stock stock1;
    private Stock stock2;
    private StockDTO dto;

    @BeforeMethod
    void setUp() {
        candy1 = new Candy();
        candy1.setId(1);
        warehouse1 = new Warehouse();
        warehouse1.setId(1);
        warehouse1.setCurrentStock(1000);

        stock1 = new Stock();
        stock1.setId(1);
        stock1.setQuantity(150);
        stock1.setCandy(candy1);  
        stock1.setWarehouse(warehouse1);

        candy2 = new Candy();
        candy2.setId(2);
        warehouse2 = new Warehouse();
        warehouse2.setId(2);
        warehouse2.setCurrentStock(2000);

        stock2 = new Stock();
        stock2.setId(2);
        stock2.setQuantity(250);
        stock2.setCandy(candy2);  
        stock2.setWarehouse(warehouse2);

        dto = new StockDTO(1, 2, 3, 4000);
        closeable = MockitoAnnotations.openMocks(this);  
    }

    @AfterMethod
    public void teardown() throws Exception {
        closeable.close();
    }

    @Test
    public void testGetAllStockDTO() {
        // when converting stocks to stockdtos it will try to grab candyids and warehouseids by accessing corresponding candy and warehouse objs
        // need to create new instances and add it to the stock before implementing stubb to preven nullpointer exception
        List<Stock> stocks = Arrays.asList(stock1, stock2);

        // have it return the stocks we just created
        when(stockRepo.findAll()).thenReturn(stocks);

        // create a mock "spy" to apply when().thenreturn() method to stockServiceSpy.convertToDTO()
        StockService stockServiceSpy = spy(stockService);
        StockDTO dto1 = new StockDTO(1, 1, 1, 1000);
        StockDTO dto2 = new StockDTO(2, 2, 2, 2000);

        // stubb the spy methods
        when(stockServiceSpy.convertToDTO(stock1)).thenReturn(dto1);
        when(stockServiceSpy.convertToDTO(stock2)).thenReturn(dto2);

        List<StockDTO> dtos = stockServiceSpy.getAllStocksDTO();

        // ensure dto list size matches and the contents too
        Assert.assertEquals(dtos.size(), 2);
        Assert.assertEquals(dtos.get(0), dto1);
        Assert.assertEquals(dtos.get(1), dto2);
    }

    // needs warehouseid, list of dtos, convertotdo spy, stockrepo.findbywarehouseid
    @Test
    public void testGetStocksByWarehouse() {
        // set the warehouse2 bound to stock2 to 1 since we need a list of stocks with same warehouseid
        int warehouseId = 1;
        warehouse2.setId(warehouseId);
        List<Stock> stocks = Arrays.asList(stock1, stock2);

        when(stockRepo.findByWarehouseId(warehouseId)).thenReturn(stocks);

        StockService stockServiceSpy = spy(stockService);
        StockDTO dto1 = new StockDTO(1, 1, 1, 1000);
        StockDTO dto2 = new StockDTO(2, 2, 1, 2000);

        // stubb the spy methods
        when(stockServiceSpy.convertToDTO(stock1)).thenReturn(dto1);
        when(stockServiceSpy.convertToDTO(stock2)).thenReturn(dto2);

        List<StockDTO> dtos = stockServiceSpy.getStocksByWarehouse(warehouse1.getId());

        // ensure sizes, and each dto matches
        Assert.assertEquals(dtos.size(), 2);
        Assert.assertEquals(dtos.get(0), dto1);
        Assert.assertEquals(dtos.get(1), dto2);
    }

    @Test
    public void testCreateStock() {
        // create a new dto to get candy id and warehouseid from
        Stock expectedStock = new Stock();
        expectedStock.setCandy(candy1);
        expectedStock.setWarehouse(warehouse1);
        expectedStock.setQuantity(dto.getQuantity());

        when(candyRepo.findById(dto.getCandyId())).thenReturn(Optional.ofNullable(candy1));
        when(warehouseRepo.findById(dto.getWarehouseId())).thenReturn(Optional.ofNullable(warehouse1));
        when(stockRepo.save(any(Stock.class))).thenReturn(expectedStock);

        Stock stock = stockService.createStock(dto);

        Assert.assertEquals(stock, expectedStock);
    }

    // specify that this test expects a this exception
    @Test(expectedExceptions = NoSuchElementException.class)
    public void testCreateStockCandyNotFound() {
        // stub so func throws an exception
        when(candyRepo.findById(dto.getCandyId())).thenThrow(new NoSuchElementException("Candy not found"));
        when(candyRepo.findById(dto.getCandyId())).thenReturn(Optional.empty());

        // grab the expection thrown
        Stock stock = stockService.createStock(dto);

        // ensure the exception for candy is thrown
        Assert.assertEquals(stock, new NoSuchElementException("Candy not found"));
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void testCreateStockCandyEmpty() {
        // stub so func throws an exception
        when(candyRepo.findById(dto.getCandyId())).thenReturn(Optional.empty());

        // grab the expection thrown
        Stock stock = stockService.createStock(dto);

        // ensure the exception for candy is thrown
        Assert.assertEquals(stock, new NoSuchElementException("Candy not found"));
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void testCreateStockWarehouseNotFound() {
        // first stub the candyrepo func so it doesn't throw exception for no candy
        when(candyRepo.findById(dto.getCandyId())).thenReturn(Optional.ofNullable(candy1));

        // then stub warehouserepo func to get the correct exception
        when(warehouseRepo.findById(dto.getWarehouseId())).thenThrow(new NoSuchElementException("Warehouse not found"));

        Stock stock = stockService.createStock(dto);

        // ensure the exception for warehouse is thrown
        Assert.assertEquals(stock, new NoSuchElementException("Warehouse not found"));
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void testCreateStockWarehouseEmpty() {
        when(candyRepo.findById(dto.getCandyId())).thenReturn(Optional.ofNullable(candy1));
        // stub so func throws an exception
        when(warehouseRepo.findById(dto.getWarehouseId())).thenReturn(Optional.empty());

        // grab the expection thrown
        Stock stock = stockService.createStock(dto);

        // ensure the exception for candy is thrown
        Assert.assertEquals(stock, new NoSuchElementException("Warehouse not found"));
    }

    @Test
    public void testUpdateStock() {
        // stub this with existing stock, candy and warehouse 
        when(stockRepo.existsById(stock1.getId())).thenReturn(true);
        when(stockRepo.findById(stock1.getId())).thenReturn(Optional.ofNullable(stock1));
        when(candyRepo.findById(dto.getCandyId())).thenReturn(Optional.ofNullable(candy2));
        when(warehouseRepo.findById(dto.getWarehouseId())).thenReturn(Optional.ofNullable(warehouse1));

        // update stock1 with predefined dto
        stockService.updateStock(stock1.getId(), dto);

        // ensure quantity in stock and warehouse is updated correctly
        Assert.assertEquals(stock1.getQuantity(), 4000);
        Assert.assertEquals(warehouse1.getCurrentStock(), 4850);
    }

    // testing the existsbyid in update stock to throw exception
    @Test(expectedExceptions = NoSuchElementException.class)
    public void testUpdateStockStockNotFound() {
        int stockId = 10;
        when(stockRepo.existsById(stockId)).thenThrow(new NoSuchElementException("Stock with id " + stockId + " does not exist"));

        stockService.updateStock(0, dto);
    }

    // @Test(expectedExceptions = NoSuchElementException.class)
    // public void testUpdateStockNotFound() {
    //     int stockId = 2;
    //     // stub so func throws an exception
    //     when(stockRepo.findById(stockId)).thenReturn(Optional.empty());

    //     // grab the expection thrown
    //     stockService.updateStock(stockId, dto);

    // }

    // @Test(expectedExceptions = NoSuchElementException.class)
    // public void testUpdateStockCandyEmpty() {
    //     int stockId = 17;
    //     // stub so func throws an exception
    //     when(candyRepo.findById(dto.getCandyId())).thenReturn(Optional.empty());

    //     // grab the expection thrown
    //     stockService.updateStock(stockId, dto);
    // }

    // @Test(expectedExceptions = NoSuchElementException.class)
    // public void testUpdateStockWarehouseEmpty() {
    //     int stockId = 17;
    //     when(candyRepo.findById(dto.getCandyId())).thenReturn(Optional.ofNullable(candy1));
    //     // stub so func throws an exception
    //     when(warehouseRepo.findById(dto.getWarehouseId())).thenReturn(Optional.empty());

    //     // grab the expection thrown
    //     stockService.updateStock(stockId, dto);
    // }
    
    @Test
    public void testDeleteStock() {
        when(stockRepo.findById(1)).thenReturn(Optional.of(stock1));

        stockService.deleteStock(1);

        Assert.assertEquals(warehouse1.getCurrentStock(), 850);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void testDeleteStockNotFound() {
        int stockId = 17;
        // stub so func throws an exception
        when(stockRepo.findById(stockId)).thenThrow(new NoSuchElementException("Stock could not be found"));

        // grab the expection thrown
        stockService.deleteStock(stockId);

        // ensure the exception for candy is thrown
        // Assert.assertEquals(, new NoSuchElementException("Candy not found"));
    }
}
