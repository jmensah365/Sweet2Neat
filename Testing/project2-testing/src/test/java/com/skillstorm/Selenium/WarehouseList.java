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

    @FindBy(className = "MuiAlert-message")
    private WebElement alertErrMsg;

    @FindBy(name = "cancelEditBtn")
    private WebElement cancelBtn;

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

    public String checkCurrentPageUrl() {
        return this.driver.getCurrentUrl();
    }

    //=====================UPDATE======================//
    public void clickEditButton() {
        RunCucumberTest.sleepThread();
        editBtn.click();
    }

    public String setLocation(String location){
        RunCucumberTest.sleepThread();
        String prevValue = locationField.getAttribute("value");
        for (int i = 0; i < prevValue.length(); i++){
            locationField.sendKeys(Keys.BACK_SPACE);
        }
        if(!location.isEmpty()) {
            locationField.sendKeys(location);
        }
        return warehouseFirstRowLocation.getText();
    }

    public String setCapacity(String capacity){
        RunCucumberTest.sleepThread();
        String prevValue = capacityField.getAttribute("value");
        for (int i = 0; i < prevValue.length(); i++){
            capacityField.sendKeys(Keys.BACK_SPACE);
        }
        if(!capacity.isEmpty()) {
            capacityField.sendKeys(capacity);
        }
        return warehouseFirstRowLocation.getText();
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
    
    public boolean confirmDeletion(){
        RunCucumberTest.sleepThread();
        List<WebElement> tableRows = warehouseTable.findElements(By.xpath(".//tr/td[1]"));
        for(WebElement tr : tableRows) {
            if(tr.getText().contains(warehouseId)){
                return true;
            }
        }
        return false;
    }

    //=====================DELETE======================//

    //======================= CREATE =========================
    public String checkPageTitle() {
    RunCucumberTest.sleepThread();
        return warehouseListTitle.getText();
    }

    public void clickAddWarehouseButton() {
    RunCucumberTest.sleepThread();
        addWarehouseButton.click();
    }

    public String getWarehouseLocation() {
    RunCucumberTest.sleepThread();
        return warehouseRowLocation.getText();
    }

    public String alertMsgText() {
    RunCucumberTest.sleepThread();
        return alertErrMsg.getText();
    }

    //======================= READ =========================
    public void getHomeURL() {
        RunCucumberTest.sleepThread();
        this.driver.get(homeUrl);
    }

    public void clickWarehouseListOption() {
        warehousesMenu.click();        
        RunCucumberTest.sleepThread();
        warehouseListOption.click();
    }

    public void getWarehouseTable() {
        RunCucumberTest.sleepThread();
        warehouseTable.getText();
    }

    public String getWarehouseTableContents() {
        RunCucumberTest.sleepThread();
        return warehouseTable.findElement(By.xpath(".//tr")).getText();

    }

    public boolean findEditCancelBtn() {
        if(cancelBtn != null) {
            return true;
        }
        return false;
    }
}