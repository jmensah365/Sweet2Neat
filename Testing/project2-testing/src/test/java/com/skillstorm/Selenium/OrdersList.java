package com.skillstorm.Selenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class OrdersList {

    // WebDriver instance to control the browser
    private WebDriver driver;

    // URLs for the order list page and the home page
    private static final String url = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/orders";
    private static final String homeUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/";

    // Locators for the form fields on the order list page
    @FindBy(name = "customerName")
    private WebElement customerNameField;

    @FindBy(name = "orderDatePickerBtn")
    private WebElement orderDateBtn;

    @FindBy(name = "orderDatePicker")
    private WebElement orderDateField;

    @FindBy(name = "status")
    private WebElement statusField;

    @FindBy(name = "customerAddress")
    private WebElement customerAddressField;

    // Locator for the ADD ORDER or UPDATE ORDER button
    @FindBy(name = "orderListBtn")
    private WebElement orderListBtn;

    // Locators to grab the order ID from the first and last rows in the order list table
    @FindBy(xpath = "//table[@name='orderListTable']//tr[last()]/td[1]")
    private WebElement lastRowOrderId;

    @FindBy(xpath = "//table[@name='orderListTable']//tr[1]/td[1]")
    private WebElement firstRowOrderId;

    // Locators for the orders menu and the list of orders button on the home page
    @FindBy(name = "orders")
    private WebElement ordersMenu;

    @FindBy(name = "orderListRoute")
    private WebElement listOfOrdersMenu;

    // Locator for the order list table
    @FindBy(name = "orderListTable")
    private WebElement orderListTable;

    // Locator for the status dropdown select field
    @FindBy(id = "statusSelect")
    private WebElement statusSelect;

    // Locator for the edit icon button
    @FindBy(name = "editIcon")
    private WebElement editBtn;

    // Locator for the order list snackbar error message
    @FindBy(name = "orderListSnackbarError")
    private WebElement orderListErrorMessage;

    // Variable to store the order ID
    String orderId = "";

    // Constructor to initialize the WebDriver and PageFactory elements
    public OrdersList(WebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    // Method to quit the WebDriver and close the browser
    public void quit(){
        this.driver.quit();
    }

    //======================CREATE=====================//

    // Method to navigate to the order list page URL
    public void getOrderListPageUrl(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.driver.get(url);
    }

    // Method to enter customer name
    public void getCustomerName(String customerName){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        customerNameField.sendKeys(customerName);
    }

    // Method to enter order date
    public void getOrderDate(String orderDate) {
        try {
            // Wait for the date picker to be ready
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Have to click into the order date field first
        orderDateField.click();
        orderDateField.sendKeys(orderDate);
    }

    // Method to select status from a dropdown
    public void getStatus(String status){
        try {
            Thread.sleep(3000);
            statusSelect.click();
            Thread.sleep(1000);

             //Pass in status into select drop down to pick desired status if status is not null or empty
            if(status != null && !status.isEmpty()){
                WebElement option = driver.findElement(By.xpath("//li[@data-value='" + status + "']"));
                option.click();
            } else {
                //if status is null look for the empty value
                WebElement option = driver.findElement(By.xpath("//li[@data-value='']"));
                option.click();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Method to enter customer address
    public void getCustomerAddress(String customerAddress){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        customerAddressField.sendKeys(customerAddress);
    }

    // Method to click the ADD ORDER or UPDATE ORDER button
    public void clickAddOrderBtn(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        orderListBtn.click();
    }

    // Method to confirm the creation of an order by printing the last row's order ID
    public void confirmOrderCreation(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(lastRowOrderId.getText());
    }

    // Method to display an error message if present
    public void displayErrorMessage(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(orderListErrorMessage.isDisplayed());
    }

    //======================CREATE=====================//


    //======================READ=====================//

    // Method to navigate to the home page URL
    public void getHomeUrl(){
        this.driver.get(homeUrl);
    }

    // Method to navigate to the list of orders page
    public void clickListOfOrdersBtn(){
        ordersMenu.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        listOfOrdersMenu.click();
    }

    // Method to display all rows in the order list table
    public void displayOrderListTable(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Iterate through the table and print each row
        List<WebElement> tableRows = orderListTable.findElements(By.xpath(".//tr"));
        for(WebElement tr : tableRows) {
            System.out.println(tr.getText());
        }
    }
    //======================READ=====================//


    //======================UPDATE=====================//

    // Method to click the edit button for an order
    public void clickEditButton(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        editBtn.click();
    }

    // Method to update the customer name field
    public void setCustomerName(String customerName){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Find the previous value in the text field and backspace the whole value to ensure it does not pop up again
        String prevValue = customerNameField.getAttribute("value");
        for (int i = 0; i < prevValue.length(); i++){
            customerNameField.sendKeys(Keys.BACK_SPACE);
        }
        customerNameField.sendKeys(customerName);
    }

    // Method to update the order date field
    public void setOrderDate(String orderDate){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        orderDateField.click();
        //Highlighting all the text in the date field and deleting it
        orderDateField.sendKeys(Keys.COMMAND + "a");
        orderDateField.sendKeys(Keys.BACK_SPACE);
        orderDateField.sendKeys(orderDate);
    }

    // Method to update the status field
    public void setStatus(String status){
        try {
            Thread.sleep(2000);
            statusSelect.click();
            Thread.sleep(1000);

            //Pass in status into select drop down to pick desired status if status is not null or empty
            if(status != null && !status.isEmpty()){
                WebElement option = driver.findElement(By.xpath("//li[@data-value='" + status + "']"));
                option.click();
            } else {
                //if status is null look for the empty value
                WebElement option = driver.findElement(By.xpath("//li[@data-value='']"));
                option.click();
            }
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Method to update the customer address field
    public void setCustomerAddress(String customerAddress){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Find the previous value in the text field and backspace the whole value to ensure it does not pop up again
        String prevValue = customerAddressField.getAttribute("value");
        for (int i = 0; i < prevValue.length(); i++){
            customerAddressField.sendKeys(Keys.BACK_SPACE);
        }
        customerAddressField.sendKeys(customerAddress);
    }

    // Method to confirm the update by printing the first row's order ID
    public void confirmOrderUpdation(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Updated Order ID " + firstRowOrderId.getText());
    }
    //======================UPDATE=====================//


    //======================DELETE=====================//

    // Method to click the delete icon for the last row in the order list table
    public void clickDeleteIcon() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Get the order ID from the last row
        orderId = lastRowOrderId.getText();
        
        // Find the delete button for the last row
        WebElement lastRowDeleteBtn = driver.findElement(By.xpath("//table[@name='orderListTable']//tbody/tr[last()]/td[last()]/button[@name='deleteIcon']\n" + //
                ""));
        
        // Click the delete button for the last row
        lastRowDeleteBtn.click();
    }

    // Method to confirm deletion by checking if the order ID is no longer in the table
    public void confirmDeletion(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Iterating through the order list table and checking to see if the deleted ID is still in the table
        //If not return False, if it is throw an Assertion
        List<WebElement> tableRows = orderListTable.findElements(By.xpath(".//tr/td[1]"));
        for(WebElement tr : tableRows) {
            if(tr.getText().contains(orderId)){
                throw new AssertionError("Order with ID " + orderId + " was found in the table");
            } else{
                System.err.println("False");
            }
        }
    }
    //======================DELETE=====================//

}