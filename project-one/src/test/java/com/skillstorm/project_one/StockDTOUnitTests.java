// package com.skillstorm.project_one;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import com.skillstorm.project_one.DTOs.StockDTO;

// public class StockDTOUnitTests {
//     private StockDTO stockDTO;

//     @BeforeEach
//     public void init(){
//         stockDTO = new StockDTO();
//     }

//     @Test
//     public void testGettersAndSetters(){
//         stockDTO.setId(1);
//         stockDTO.setCandyId(2);
//         stockDTO.setWarehouseId(3);
//         stockDTO.setQuantity(30);

//         assertEquals(1, stockDTO.getId());
//         assertEquals(2, stockDTO.getCandyId());
//         assertEquals(3, stockDTO.getWarehouseId());
//         assertEquals(30, stockDTO.getQuantity());
//     }

//     @Test
//     public void testConstructor(){
//         StockDTO dto = new StockDTO(5,10,15,50);
//         assertEquals(5, dto.getId());
//         assertEquals(10, dto.getCandyId());
//         assertEquals(15, dto.getWarehouseId());
//         assertEquals(50, dto.getQuantity());
//     }

//     @Test
//     public void testToString(){
//         stockDTO.setId(2);
//         stockDTO.setCandyId(4);
//         stockDTO.setWarehouseId(6);
//         stockDTO.setQuantity(100);
//         String expectedString = "StockDTO [id=2, candyId=4, warehouseId=6, quantity=100]";
//         assertEquals(expectedString, stockDTO.toString());
//     }
// }
