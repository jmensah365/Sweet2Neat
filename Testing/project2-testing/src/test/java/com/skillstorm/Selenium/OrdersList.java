package com.skillstorm.Selenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.skillstorm.RunCucumberTest;


public class OrdersList {
    private WebDriver driver;
    private static final String url = "http://localhost:5173/orders";
    private static final String homeUrl = "http://localhost:5173/";

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
    @FindBy(xpath = "//tbody[@name='tableBody']//tr[1]")
    private WebElement firstRowOrder;

    @FindBy(xpath = "//tbody[@name='tableBody']//tr[last()]")
    private WebElement lastRowOrder;

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

    @FindBy(className = "MuiAlert-message")
    private WebElement alertMsg;

    @FindBy(name = "cancelEditBtn")
    private WebElement cancelBtn;

    @FindBy(name = "confirmDelete")
    private WebElement confirmDelete;

    String orderId = "";

    public OrdersList(WebDriver driver){
        this.driver = driver;
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

    public String checkCurrentUrl() {
        return this.driver.getCurrentUrl();
    }

    public String getCustomerName(String customerName){
        RunCucumberTest.sleepThread();
        customerNameField.sendKeys(customerName);
        return customerName;
    }

    public String getStatus(String status){
        statusSelect.click();
        RunCucumberTest.sleepThread();
        if(status != null && !status.isEmpty()){
            driver.findElement(By.xpath("//li[@name='" + status.toLowerCase() + "']")).click();
        } else {
            driver.findElement(By.name("clear")).click();
        }
        return status;
    }

    public String getCustomerAddress(String customerAddress){
        customerAddressField.sendKeys(customerAddress);
        return customerAddress;
    }

    public void clickAddOrderBtn(){
        RunCucumberTest.sleepThread();
        orderListBtn.click();
    }

    public String confirmOrderCreation(){
        RunCucumberTest.sleepThread();
        return lastRowOrder.getText();
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

    public String displayOrderListTable(){
        RunCucumberTest.sleepThread();
        return orderListTable.findElement(By.xpath(".//tr")).getText();
    }
     //======================READ=====================//

     //======================UPDATE=====================//
    public void clickEditButton(){
        RunCucumberTest.sleepThread();
        editBtn.click();
    }

    public String setCustomerName(String customerName){
        RunCucumberTest.sleepThread();
        
        String prevValue = customerNameField.getAttribute("value");
        for (int i = 0; i < prevValue.length(); i++){
            customerNameField.sendKeys(Keys.BACK_SPACE);
        }
        customerNameField.sendKeys(customerName);
        return customerName;
    }

    public String setStatus(String status){
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

        return status;
    }

    public String setCustomerAddress(String customerAddress){
        RunCucumberTest.sleepThread();
        String prevValue = customerAddressField.getAttribute("value");
        for (int i = 0; i < prevValue.length(); i++){
            customerAddressField.sendKeys(Keys.BACK_SPACE);
        }
        customerAddressField.sendKeys(customerAddress);
        return customerAddress;
    }

    public String confirmOrderUpdation(){
        RunCucumberTest.sleepThread();
        return firstRowOrder.getText();
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

    public void clickConfirmDeleteBtn(){
        confirmDelete.click();
    }

    public boolean confirmDeletion(){
        RunCucumberTest.sleepThread();
        List<WebElement> tableRows = orderListTable.findElements(By.xpath(".//tr/td[1]"));
        for(WebElement tr : tableRows) {
            if(tr.getText().contains(orderId)){
                return true;
            }
        }
        return false;
    }
     //======================DELETE=====================//

     public String getAlertMsg() {
        RunCucumberTest.sleepThread();
        return alertMsg.getText();
    }

    public boolean isCancelBtn() {
        if(cancelBtn != null) {
            return true;
        }
        return false;
    }
}