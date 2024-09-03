package com.skillstorm.Selenium;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private WebDriver driver;
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

    @FindBy(name = "orderListBtn")
    private WebElement orderListBtn;

    //Finding last row in order list table and grabbing the order ID
    @FindBy(xpath = "//table[@name='orderListTable']//tr[last()]/td[1]")
    private WebElement lastRowOrderId;

    //Finding last row in order list table and grabbing the order ID
    @FindBy(xpath = "//table[@name='orderListTable']//tr[1]/td[1]")
    private WebElement firstRowOrderId;

    @FindBy(name = "orders")
    private WebElement ordersMenu;

    @FindBy(name = "orderListRoute")
    private WebElement listOfOrdersMenu;

    @FindBy(name = "orderListTable")
    private WebElement orderListTable;

    @FindBy(id = "statusSelect")
    private WebElement statusSelect;

    //Finding edit icon button
    @FindBy(name = "editIcon")
    private WebElement editBtn;

    @FindBy(name = "orderListSnackbarError")
    private WebElement orderListErrorMessage;



    String orderId = "";

    public OrdersList(WebDriver driver){
        this.driver = driver;
        this.actions = new Actions(this.driver);
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    public void quit(){
        this.driver.quit();
    }

    //======================CREATE=====================//

    public void getOrderListPageUrl(){
        RunCucumberTest.sleepThread();

        this.driver.get(url);
    }

    public void getCustomerName(String customerName){
        RunCucumberTest.sleepThread();
        customerNameField.sendKeys(customerName);
    }


   public void getOrderDate(String orderDate) {
        actions.moveToElement(orderDateField).click().perform();
        orderDateField.sendKeys(orderDate);
        // orderDateField.click();
        // wait.until(ExpectedConditions.elementToBeClickable(orderDateField)).sendKeys(orderDate);
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destFile = new File("orderdatescreenshot.png");
        try {
            FileUtils.copyFile(srcFile, destFile);
            System.out.println("Screenshot saved at: " + destFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getStatus(String status){
        // actions.moveToElement(statusSelect).click().sendKeys(status).build().perform();;
        // statusSelect.click();
        actions.moveToElement(statusSelect).click().perform();
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destFile = new File("screenshot.png");
        try {
            FileUtils.copyFile(srcFile, destFile);
            System.out.println("Screenshot saved at: " + destFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(status != null && !status.isEmpty()){
            // wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(status.toLowerCase())));
            WebElement option = driver.findElement(By.xpath("//li[@name='" + status.toLowerCase() + "']"));
            // actions.moveToElement(option).click().build().perform();
            actions.moveToElement(statusSelect).click().moveToElement(option).click().build().perform();
        // RunCucumberTest.sleepThread();
            // option.click();
        } else {
        // actions.moveToElement(statusSelect).click().sendKeys("clear").build().perform();;
            // wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("clear")));
            WebElement option = driver.findElement(By.name("clear"));
            actions.moveToElement(option).click().build().perform();
        }
        // actions.moveToElement(option).click().build().perform();
    }

    public void getCustomerAddress(String customerAddress){
        // RunCucumberTest.sleepThread();
        customerAddressField.sendKeys(customerAddress);
    }

    public void clickAddOrderBtn(){
        RunCucumberTest.sleepThread();
        orderListBtn.click();
    }

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
    public void getHomeUrl(){
        RunCucumberTest.sleepThread();
        this.driver.get(homeUrl);
    }

    public void clickListOfOrdersBtn(){
        RunCucumberTest.sleepThread();
        ordersMenu.click();
        RunCucumberTest.sleepThread();
        listOfOrdersMenu.click();
    }

    public void displayOrderListTable(){
        RunCucumberTest.sleepThread();
        List<WebElement> tableRows = orderListTable.findElements(By.xpath(".//tr"));

        for(WebElement tr : tableRows) {
            System.out.println(tr.getText());
        }
    }
     //======================READ=====================//

     //======================UPDATE=====================//
    public void clickEditButton(){
        RunCucumberTest.sleepThread();
        editBtn.click();
    }

    public void setCustomerName(String customerName){
        RunCucumberTest.sleepThread();
        
        String prevValue = customerNameField.getAttribute("value");
        for (int i = 0; i < prevValue.length(); i++){
            customerNameField.sendKeys(Keys.BACK_SPACE);
        }
        customerNameField.sendKeys(customerName);
    }

    public void setOrderDate(String orderDate){
        orderDateField.click();
        RunCucumberTest.sleepThread();

        orderDateField.sendKeys(Keys.COMMAND + "a");
        orderDateField.sendKeys(Keys.BACK_SPACE);
        orderDateField.sendKeys(orderDate);
    }

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

    public void setCustomerAddress(String customerAddress){
        RunCucumberTest.sleepThread();
        String prevValue = customerAddressField.getAttribute("value");
        for (int i = 0; i < prevValue.length(); i++){
            customerAddressField.sendKeys(Keys.BACK_SPACE);
        }
        customerAddressField.sendKeys(customerAddress);
    }

    public void confirmOrderUpdation(){
        RunCucumberTest.sleepThread();

        System.out.println("Updated Candy ID " + firstRowOrderId.getText());
    }
     //======================UPDATE=====================//

     //======================DELETE=====================//
    public void clickDeleteIcon() {
        RunCucumberTest.sleepThread();
        
        // Update to get the candy ID from the last row
        orderId = lastRowOrderId.getText();
        
        // Find the delete button for the last row
        WebElement lastRowDeleteBtn = driver.findElement(By.xpath("//table[@name='orderListTable']//tbody/tr[last()]/td[last()]/button[@name='deleteIcon']\n" + //
                ""));
        
        // Click the delete button for the last row
        lastRowDeleteBtn.click();
    }

    public void confirmDeletion(){
        RunCucumberTest.sleepThread();
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