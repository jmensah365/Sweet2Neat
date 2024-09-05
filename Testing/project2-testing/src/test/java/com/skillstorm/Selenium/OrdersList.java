package com.skillstorm.Selenium;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;


import com.skillstorm.RunCucumberTest;


public class OrdersList {

    // WebDriver instance to control the browser
    private WebDriver driver;

    // URLs for the order list page and the home page
    private static final String url = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/orders";
    private static final String homeUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/";
    private Actions actions;
    WebDriverWait wait;
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

    @FindBy(name = "orderListSnackbarError")
    private WebElement orderListErrorMessage;


    // Variable to store the order ID
    String orderId = "";

    // Constructor to initialize the WebDriver and PageFactory elements
    public OrdersList(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    public void quit(){
        this.driver.quit();
    }

    //======================CREATE=====================//

    // Method to navigate to the order list page URL
    public void getOrderListPageUrl(){
        RunCucumberTest.sleepThread();

        this.driver.get(url);
    }

    // Method to enter customer name
    public void getCustomerName(String customerName){
        RunCucumberTest.sleepThread();
        customerNameField.sendKeys(customerName);
    }


    // public void getOrderDate(String orderDate) {
    //     this.actions = new Actions(this.driver);
    //     actions.moveToElement(orderDateField).click().sendKeys(orderDate).build().perform();
    //     actions.sendKeys(Keys.ESCAPE);
        
    //     // orderDateField.click();
    //     // wait.until(ExpectedConditions.elementToBeClickable(orderDateField)).sendKeys(orderDate);
    //     TakesScreenshot screenshot = ((TakesScreenshot) driver);
    //     File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
    //     File destFile = new File("orderdatescreenshot.png");
    //     try {
    //         FileUtils.copyFile(srcFile, destFile);
    //         System.out.println("Screenshot saved at: " + destFile.getAbsolutePath());
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }
    public void getStatus(String status){
        // actions.moveToElement(statusSelect).click().sendKeys(status).build().perform();;
        statusSelect.click();
        // actions.moveToElement(statusSelect).click().perform();
        RunCucumberTest.sleepThread();
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destFile = new File("screenshot.png");
        try {
            FileUtils.copyFile(srcFile, destFile);
            System.out.println("Screenshot saved at: " + destFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getCustomerAddress(String customerAddress){
        // RunCucumberTest.sleepThread();
        customerAddressField.sendKeys(customerAddress);
    }

    // Method to click the ADD ORDER or UPDATE ORDER button
    public void clickAddOrderBtn(){
        RunCucumberTest.sleepThread();
        orderListBtn.click();
    }

    // Method to confirm the creation of an order by printing the last row's order ID
    public void confirmOrderCreation(){
        RunCucumberTest.sleepThread();
        System.out.println(lastRowOrderId.getText());
    }

    public void displayErrorMessage(){
        RunCucumberTest.sleepThread();
        System.out.println(orderListErrorMessage.isDisplayed());
    }

    //======================CREATE=====================//


    //======================READ=====================//

    // Method to navigate to the home page URL
    public void getHomeUrl(){
        RunCucumberTest.sleepThread();
        this.driver.get(homeUrl);
    }

    // Method to navigate to the list of orders page
    public void clickListOfOrdersBtn(){
        RunCucumberTest.sleepThread();
        ordersMenu.click();
        RunCucumberTest.sleepThread();
        listOfOrdersMenu.click();
    }

    // Method to display all rows in the order list table
    public void displayOrderListTable(){
        RunCucumberTest.sleepThread();
        List<WebElement> tableRows = orderListTable.findElements(By.xpath(".//tr"));
        for(WebElement tr : tableRows) {
            System.out.println(tr.getText());
        }
    }
    //======================READ=====================//


    //======================UPDATE=====================//

    // Method to click the edit button for an order
    public void clickEditButton(){
        RunCucumberTest.sleepThread();
        editBtn.click();
    }

    // Method to update the customer name field
    public void setCustomerName(String customerName){
        RunCucumberTest.sleepThread();
        
        String prevValue = customerNameField.getAttribute("value");
        for (int i = 0; i < prevValue.length(); i++){
            customerNameField.sendKeys(Keys.BACK_SPACE);
        }
        customerNameField.sendKeys(customerName);
    }

    // Method to update the order date field
    public void setOrderDate(String orderDate){
        orderDateField.click();
        RunCucumberTest.sleepThread();

        orderDateField.sendKeys(Keys.COMMAND + "a");
        orderDateField.sendKeys(Keys.BACK_SPACE);
        orderDateField.sendKeys(orderDate);
    }

    // Method to update the status field
    public void setStatus(String status){

            statusSelect.click();

        RunCucumberTest.sleepThread();

            if(status != null && !status.isEmpty()){
                WebElement option = driver.findElement(By.xpath("//li[@data-value='" + status + "']"));
        RunCucumberTest.sleepThread();
                option.click();
            } else {
                WebElement option = driver.findElement(By.xpath("//li[@data-value='']"));

        RunCucumberTest.sleepThread();
                option.click();
            }
    }

    // Method to update the customer address field
    public void setCustomerAddress(String customerAddress){
        RunCucumberTest.sleepThread();
        String prevValue = customerAddressField.getAttribute("value");
        for (int i = 0; i < prevValue.length(); i++){
            customerAddressField.sendKeys(Keys.BACK_SPACE);
        }
        customerAddressField.sendKeys(customerAddress);
    }

    // Method to confirm the update by printing the first row's order ID
    public void confirmOrderUpdation(){
        RunCucumberTest.sleepThread();

        System.out.println("Updated Candy ID " + firstRowOrderId.getText());
    }
    //======================UPDATE=====================//


    //======================DELETE=====================//

    // Method to click the delete icon for the last row in the order list table
    public void clickDeleteIcon() {
        RunCucumberTest.sleepThread();
        
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
        RunCucumberTest.sleepThread();
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