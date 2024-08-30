package com.skillstorm.Selenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.driver.get(url);
    }

    //=====================UPDATE======================//
    public void clickEditButton() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        editBtn.click();
    }

    public void setLocation(String location){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String prevValue = locationField.getAttribute("value");
        for (int i = 0; i < prevValue.length(); i++){
            locationField.sendKeys(Keys.BACK_SPACE);
        }
        locationField.sendKeys(location);
    }

    public void setCapacity(String capacity){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String prevValue = capacityField.getAttribute("value");
        for (int i = 0; i < prevValue.length(); i++){
            capacityField.sendKeys(Keys.BACK_SPACE);
        }
        capacityField.sendKeys(capacity);
    }

    public void clickUpdateWarehouse(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        warehouseBtn.click();
    }

    public void confirmWarehouseUpdation(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("New warehouse location: " + warehouseRowLocation.getText());

        // this.driver.close();
    }

     //TODO: check to see if success message pops up
    //=====================UPDATE======================//

    //=====================DELETE======================//
    public void clickDeleteIcon(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        warehouseId = warehouseFirstRowLocation.getText();
        deleteBtn.click();
    }
    
    public void confirmDeletion(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> tableRows = warehouseTable.findElements(By.xpath(".//tr/td[1]"));
        for(WebElement tr : tableRows) {
            if(tr.getText().contains(warehouseId)){
                System.out.println("True");
            } else{
                System.err.println("False");
            }
        }
            // this.driver.close();


    }

    //=====================DELETE======================//

        //======================= CREATE =========================
        public String checkPageTitle() {
            return warehouseListTitle.getText();
        }
    
        public void addLocation(String location) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // if(!location.equals("empty")) {
            //     locationField.sendKeys(location);
            // } else {
            //     action.sendKeys(Keys.ESCAPE).perform();
            // }
        }
    
        public void addCapacity(String capacity) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            capacityField.sendKeys(capacity);
        }
        
        public void setCurrentStock(String currentStock) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stockField.sendKeys(currentStock);
        }
    
        public void clickAddWarehouseButton() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            addWarehouseButton.click();
        }
    
        public void getWarehouseLocation() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(warehouseRowLocation.getText());
        }

    //======================= READ =========================
    public void getHomeURL() {
        this.driver.get(homeUrl);
    }

    public void clickWarehouseListOption() {
        warehousesMenu.click();        
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        warehouseListOption.click();
    }

    public void getWarehouseTable() {
        warehouseTable.getText();
    }

    public void getWarehouseTableContents() {
        List<WebElement> tableRows = warehouseTable.findElements(By.xpath(".//tr"));

        for(WebElement tr : tableRows) {
            System.out.println(tr.getText());
        }
    }
    
}