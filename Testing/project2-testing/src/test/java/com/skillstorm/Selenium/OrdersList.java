package com.skillstorm.Selenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class OrdersList {
    private WebDriver driver;
    private static final String url = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/orders";
    private static final String homeUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/";

    @FindBy(name = "customerName")
    private WebElement customerNameField;

    @FindBy(name = "orderDatePicker")
    private WebElement orderDateField;

    @FindBy(name = "status")
    private WebElement statusField;

    @FindBy(name = "customerAddress")
    private WebElement customerAddressField;

    @FindBy(name = "orderListBtn")
    private WebElement orderListBtn;

    //Finding last row in order list table and grabbing the order ID
    @FindBy(xpath = "//table[@name='orderListTable']//tr[last()]/td[1]")
    private WebElement lastRowOrderId;

    @FindBy(name = "orders")
    private WebElement ordersMenu;

    @FindBy(name = "orderListRoute")
    private WebElement listOfOrdersMenu;

    @FindBy(name = "orderListTable")
    private WebElement orderListTable;

    @FindBy(id = "statusSelect")
    private WebElement statusSelect;


    String orderId = "";

    public OrdersList(WebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    //======================CREATE=====================//

    public void getOrderListPageUrl(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.driver.get(url);
    }

    public void getCustomerName(String customerName){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        customerNameField.sendKeys(customerName);
    }


    public void getOrderDate(String orderDate) {
        try {
            // Wait for the date picker to be ready
            Thread.sleep(2000);

            orderDateField.click();

            Thread.sleep(2000);
            
            orderDateField.sendKeys(orderDate);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }

    public void getStatus(String status){
        try {
            Thread.sleep(3000);

            statusSelect.click();


            Thread.sleep(1000);

            WebElement option = driver.findElement(By.xpath("//li[@data-value='" + status + "']"));

            option.click();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getCustomerAddress(String customerAddress){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        customerAddressField.sendKeys(customerAddress);
    }

    public void clickAddOrderBtn(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        orderListBtn.click();
    }

    public void confirmOrderCreation(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(lastRowOrderId.getText());
    }

     //======================CREATE=====================//


     //======================READ=====================//
    public void getHomeUrl(){
        this.driver.get(homeUrl);
    }

    public void clickListOfOrdersBtn(){
        ordersMenu.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        listOfOrdersMenu.click();
    }

    public void displayOrderListTable(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> tableRows = orderListTable.findElements(By.xpath(".//tr"));

        for(WebElement tr : tableRows) {
            System.out.println(tr.getText());
        }
    }
     //======================READ=====================//

     //======================DELETE=====================//
    public void clickDeleteIcon() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Update to get the candy ID from the last row
        orderId = lastRowOrderId.getText();
        
        // Find the delete button for the last row
        WebElement lastRowDeleteBtn = driver.findElement(By.xpath("//table[@name='orderListTable']//tbody/tr[last()]/td[last()]/button[@name='deleteIcon']\n" + //
                ""));
        
        // Click the delete button for the last row
        lastRowDeleteBtn.click();
    }

    public void confirmDeletion(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> tableRows = orderListTable.findElements(By.xpath(".//tr/td[1]"));
        for(WebElement tr : tableRows) {
            if(tr.getText().contains(orderId)){
                throw new AssertionError("Candy with ID " + orderId + " was found in the table");
            } else{
                System.err.println("False");
            }
        }
    }
     //======================DELETE=====================//

}