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
    private WebDriver driver;
    private static final String url = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/warehouses";
    private static final String homeUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/";

    /*
     * Finding location, capacity, and stock web elements on the page
     */
    @FindBy(name = "location")
    private WebElement locationField;

    @FindBy(name = "capacity")
    private WebElement capacityField;

    @FindBy(name = "currentStock")
    private WebElement stockField;

    //Finding edit icon button
    @FindBy(name = "editIcon")
    private WebElement editBtn;

    //Finding delete icon button
    @FindBy(name = "deleteIcon")
    private WebElement deleteBtn;

    //Find the warehouse menu option
    @FindBy(name = "warehouses")
    private WebElement warehousesMenu;

    //Finding update warehouse button
    @FindBy(name = "warehouseBtn")
    private WebElement warehouseBtn;

    //Find the "ADD WAREHOUSE" button
    @FindBy(name = "warehouseBtn")
    private WebElement addWarehouseButton;

    //Find the title "Warehouse List"
    @FindBy(className = "MuiTypography-root")
    private WebElement warehouseListTitle;

    //Finding table of warehouses
    @FindBy(name = "warehouseTable")
    private WebElement warehouseTable;

    //Find the warehouses list option in menu
    @FindBy(name = "warehousesRoute")
    private WebElement warehouseListOption;

    //Finding the location in the last row in the warehouse list
    @FindBy(xpath = "//table[@name='warehouseTable']//tr[last()]/td[2]")
    private WebElement warehouseRowLocation;

    //Finding the location in the first row in the warehouse list
    @FindBy(xpath = "//table[@name='warehouseTable']//tr[1]/td[1]")
    private WebElement warehouseFirstRowLocation;

    String warehouseId = "";

    public WarehouseList(WebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    public void close() { // warehouseStepDefintion calls this to close all open browsers when tests finish
        this.driver.quit();
    }

    public void getUrl() {
        RunCucumberTest.sleepThread();
        this.driver.get(url);
    }

    //=====================UPDATE======================//
    public void clickEditButton() {
        RunCucumberTest.sleepThread();
        editBtn.click();
    }

    public void setLocation(String location){
        RunCucumberTest.sleepThread();
        String prevValue = locationField.getAttribute("value");
        for (int i = 0; i < prevValue.length(); i++){
            locationField.sendKeys(Keys.BACK_SPACE);
        }
        locationField.sendKeys(location);
    }

    public void setCapacity(String capacity){
        RunCucumberTest.sleepThread();
        String prevValue = capacityField.getAttribute("value");
        for (int i = 0; i < prevValue.length(); i++){
            capacityField.sendKeys(Keys.BACK_SPACE);
        }
        capacityField.sendKeys(capacity);
    }

    public void clickUpdateWarehouse(){
        RunCucumberTest.sleepThread();
        warehouseBtn.click();
    }

    public void confirmWarehouseUpdation(){
        RunCucumberTest.sleepThread();
        System.out.println("New warehouse location: " + warehouseRowLocation.getText());
    }
    //=====================UPDATE======================//

    //=====================DELETE======================//
    public void clickDeleteIcon(){
        RunCucumberTest.sleepThread();
        
        warehouseId = warehouseFirstRowLocation.getText();
        deleteBtn.click();
    }
    
    public void confirmDeletion(){
        RunCucumberTest.sleepThread();
        List<WebElement> tableRows = warehouseTable.findElements(By.xpath(".//tr/td[1]"));
        for(WebElement tr : tableRows) {
            if(tr.getText().contains(warehouseId)){
                System.out.println("True");
            } else{
                System.err.println("False");
            }
        }
    }

    //=====================DELETE======================//

        //======================= CREATE =========================
        public String checkPageTitle() {
        RunCucumberTest.sleepThread();
            return warehouseListTitle.getText();
        }
    
        public void addLocation(String location) {
        RunCucumberTest.sleepThread();
            // if(!location.equals("empty")) {
            //     locationField.sendKeys(location);
            // } else {
            //     action.sendKeys(Keys.ESCAPE).perform();
            // }
        }
    
        public void addCapacity(String capacity) {
        RunCucumberTest.sleepThread();
            capacityField.sendKeys(capacity);
        }
        
        public void setCurrentStock(String currentStock) {
        RunCucumberTest.sleepThread();
            stockField.sendKeys(currentStock);
        }
    
        public void clickAddWarehouseButton() {
        RunCucumberTest.sleepThread();
            addWarehouseButton.click();
        }
    
        public void getWarehouseLocation() {
        RunCucumberTest.sleepThread();
            System.out.println(warehouseRowLocation.getText());
        }

    //======================= READ =========================
    public void getHomeURL() {
        RunCucumberTest.sleepThread();
        this.driver.get(homeUrl);
    }

    public void clickWarehouseListOption() {
        RunCucumberTest.sleepThread();
        warehousesMenu.click();        
        RunCucumberTest.sleepThread();
        warehouseListOption.click();
    }

    public void getWarehouseTable() {
        RunCucumberTest.sleepThread();
        warehouseTable.getText();
    }

    public void getWarehouseTableContents() {
        RunCucumberTest.sleepThread();
        List<WebElement> tableRows = warehouseTable.findElements(By.xpath(".//tr"));

        for(WebElement tr : tableRows) {
            System.out.println(tr.getText());
        }
    }
    
}