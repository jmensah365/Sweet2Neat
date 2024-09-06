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

public class WarehouseList {
    // WebDriver instance to control the browser
    private WebDriver driver;
    
    // URLs for the warehouses page and the home page
    private static final String url = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/warehouses";
    private static final String homeUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/";

    /*
     * Web elements for the fields related to warehouse management (location, capacity, stock)
     */
    @FindBy(name = "location")
    private WebElement locationField;

    @FindBy(name = "capacity")
    private WebElement capacityField;

    @FindBy(name = "currentStock")
    private WebElement stockField;

    // Web elements for buttons and menu options
    @FindBy(name = "editIcon")
    private WebElement editBtn;

    @FindBy(name = "deleteIcon")
    private WebElement deleteBtn;

    @FindBy(name = "warehouses")
    private WebElement warehousesMenu;

    @FindBy(name = "warehouseBtn")
    private WebElement warehouseBtn;

    @FindBy(name = "warehouseBtn")
    private WebElement addWarehouseButton;

    @FindBy(className = "MuiTypography-root")
    private WebElement warehouseListTitle;

    // Web element representing the table of warehouses
    @FindBy(name = "warehouseTable")
    private WebElement warehouseTable;

    @FindBy(name = "warehousesRoute")
    private WebElement warehouseListOption;

    // Web elements representing the first and last rows of the warehouse table
    @FindBy(xpath = "//table[@name='warehouseTable']//tr[last()]/td[2]")
    private WebElement warehouseRowLocation;

    @FindBy(xpath = "//tbody[@name='warehouseTBody']//tr[1]/td[1]")
    private WebElement warehouseFirstRowLocation;

    @FindBy(xpath = "//tbody[@name='warehouseTBody']//tr[last()]")
    private WebElement lastRowWarehouse;

    @FindBy(xpath = "//tbody[@name='warehouseTBody']//tr[1]")
    private WebElement firstRowWarehouse;

    // Web element for alert message (e.g., errors)
    @FindBy(className = "MuiAlert-message")
    private WebElement alertErrMsg;

    @FindBy(name = "cancelEditBtn")
    private WebElement cancelBtn;

    // Warehouse ID to track a particular warehouse
    String warehouseId = "";

    // Constructor to initialize the driver and set up the page factory
    public WarehouseList(WebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    // Method to close the browser
    public void close() {
        this.driver.quit();
    }

    // Method to navigate to the warehouse page
    public void getUrl() {
        RunCucumberTest.sleepThread();
        this.driver.get(url);
    }

    // Method to get the current page URL
    public String checkCurrentPageUrl() {
        return this.driver.getCurrentUrl();
    }

    // ===================== UPDATE ===================== //
    // Method to click the edit button
    public void clickEditButton() {
        RunCucumberTest.sleepThread();
        editBtn.click();
    }

    // Method to set a new location in the warehouse form
    public String setLocation(String location){
        RunCucumberTest.sleepThread();
        String prevValue = locationField.getAttribute("value");
        // Clear the previous value using backspace
        for (int i = 0; i < prevValue.length(); i++){
            locationField.sendKeys(Keys.BACK_SPACE);
        }
        if(!location.isEmpty()) {
            locationField.sendKeys(location);
        }
        return location;
    }

    // Method to set a new capacity in the warehouse form
    public String setCapacity(String capacity){
        RunCucumberTest.sleepThread();
        String prevValue = capacityField.getAttribute("value");
        // Clear the previous value using backspace
        for (int i = 0; i < prevValue.length(); i++){
            capacityField.sendKeys(Keys.BACK_SPACE);
        }
        if(!capacity.isEmpty()) {
            capacityField.sendKeys(capacity);
        }
        return capacity;
    }

    // Method to click the update button
    public void clickUpdateWarehouse(){
        RunCucumberTest.sleepThread();
        warehouseBtn.click();
    }

    // Method to confirm warehouse creation by checking the last row
    public String confirmWarehouseCreation(){
        RunCucumberTest.sleepThread();
        return lastRowWarehouse.getText();
    }

    // Method to confirm warehouse update by checking the first row
    public String confirmWarehouseUpdation(){
        RunCucumberTest.sleepThread();
        return firstRowWarehouse.getText();
    }
    // ===================== UPDATE ===================== //

    // ===================== DELETE ===================== //
    // Method to click the delete button for the first row
    public void clickDeleteIcon(){
        RunCucumberTest.sleepThread();
        warehouseId = warehouseFirstRowLocation.getText();
        deleteBtn.click();
    }

    // Method to confirm deletion by checking if the warehouse is removed
    public boolean confirmDeletion(){
        RunCucumberTest.sleepThread();
        List<WebElement> tableRows = firstRowWarehouse.findElements(By.xpath(".//tr/td[1]"));
        for(WebElement tr : tableRows) {
            // Check if the warehouse ID is still present in the table
            if(tr.getText().contains(warehouseId)){
                return true;
            }
        }
        return false;
    }
    // ===================== DELETE ===================== //

    // ======================= CREATE ========================= //
    // Method to check the page title
    public String checkPageTitle() {
        RunCucumberTest.sleepThread();
        return warehouseListTitle.getText();
    }

    // Method to click the "Add Warehouse" button
    public void clickAddWarehouseButton() {
        RunCucumberTest.sleepThread();
        addWarehouseButton.click();
    }

    // Method to get the location of the last warehouse in the list
    public String getWarehouseLocation() {
        RunCucumberTest.sleepThread();
        return warehouseRowLocation.getText();
    }

    // Method to get the alert message text (e.g., for validation errors)
    public String alertMsgText() {
        RunCucumberTest.sleepThread();
        return alertErrMsg.getText();
    }

    // ======================== READ ========================= //
    // Method to navigate to the home URL
    public void getHomeURL() {
        RunCucumberTest.sleepThread();
        this.driver.get(homeUrl);
    }

    // Method to click the option to view the warehouse list
    public void clickWarehouseListOption() {
        warehousesMenu.click();        
        RunCucumberTest.sleepThread();
        warehouseListOption.click();
    }

    // Method to get the text of the warehouse table
    public void getWarehouseTable() {
        RunCucumberTest.sleepThread();
        warehouseTable.getText();
    }

    // Method to get the contents of the warehouse table
    public String getWarehouseTableContents() {
        RunCucumberTest.sleepThread();
        return warehouseTable.findElement(By.xpath(".//tr")).getText();
    }

    // Method to check if the edit cancel button is present
    public boolean findEditCancelBtn() {
        return cancelBtn != null;
    }
}
