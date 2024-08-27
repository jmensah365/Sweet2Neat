// package com.skillstorm.project_one;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// import java.text.ParseException;
// import java.text.SimpleDateFormat;
// import java.util.Date;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import com.skillstorm.project_one.Models.Orders;

// public class OrderUnitTests {
//     private Orders orders;

    @BeforeEach
    public void init() {
        orders = new Orders();
        orders.setId(10);
        orders.setCustomerName("Jeremiah Mensah");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //TimeZone.setDefault(TimeZone.getTimeZone("EST"));
        try {
            Date date = sdf.parse("2023-01-01");
            orders.setOrderDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        orders.setStatus("pending");
        orders.setCustomerAddress("Apt. 404 609 Cruz Walks, Port Meghanmouth, CO 64357-4203");
    }

//     @Test
//     public void testGetters() {
//         assertEquals(10, orders.getId());
//         assertEquals("Jeremiah Mensah", orders.getCustomerName());
//         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//         try {
//             Date date = sdf.parse("2023-01-01");
//             assertEquals(date, orders.getOrderDate());
//         } catch (ParseException e) {
//             e.printStackTrace();
//         }
//         assertEquals("pending", orders.getStatus());
//         assertEquals("Apt. 404 609 Cruz Walks, Port Meghanmouth, CO 64357-4203", orders.getCustomerAddress());
//     }

//     @Test
//     public void testSetters() {
//         orders.setId(2);
//         orders.setCustomerName("DJ Kim");
//         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//         try {
//             Date date = sdf.parse("2013-08-05");
//             orders.setOrderDate(date);
//         } catch (ParseException e) {
//             e.printStackTrace();
//         }
//         orders.setStatus("completed");
//         orders.setCustomerAddress("Apt. 131 838 Walter Freeway, Geraldoburgh, PA 20991-9051");

//         assertEquals(2, orders.getId());
//         assertEquals("DJ Kim", orders.getCustomerName());
//         try {
//             Date date = sdf.parse("2013-08-05");
//             assertEquals(date, orders.getOrderDate());
//         } catch (ParseException e) {
//             e.printStackTrace();
//         }
//         assertEquals("completed", orders.getStatus());
//         assertEquals("Apt. 131 838 Walter Freeway, Geraldoburgh, PA 20991-9051", orders.getCustomerAddress());
//     }

    // @Test
    // public void testToString() {
    //     String expectedString = "Orders [id=10, customerName=Jeremiah Mensah, orderDate=Sun Jan 01 00:00:00 EST 2023, status=pending, customerAddress=Apt. 404 609 Cruz Walks, Port Meghanmouth, CO 64357-4203]";
    //     System.out.println(expectedString);
    //     System.out.println(orders.toString());
    //     assertEquals(expectedString, orders.toString());
    // }
}
