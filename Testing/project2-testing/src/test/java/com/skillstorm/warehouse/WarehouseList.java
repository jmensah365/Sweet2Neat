package com.skillstorm.warehouse;

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
    private static final String url = "http://localhost:5173/warehouses";
    private static final String homeUrl = "http://localhost:5173/";
    //private static final String editIconClassName = "MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-78trlr-MuiButtonBase-root-MuiIconButton-root";

    /*
     * Finding location, capacity, and stock web elements on the page
     */
    @FindBy(name = "location")
    private WebElement locationField;

    @FindBy(name = "capacity")
    private WebElement capacityField;

    //Finding edit icon button
    @FindBy(name = "editIcon")
    private WebElement editBtn;

    //Finding delete icon button
    @FindBy(name = "deleteIcon")
    private WebElement deleteBtn;

    //Finding update warehouse button
    @FindBy(name = "warehouseBtn")
    private WebElement warehouseBtn;

    //Finding table of warehouses
    @FindBy(name = "warehouseTable")
    private WebElement warehouseTable;

    //Finding the location in the last row in the warehouse list
    @FindBy(xpath = "//table[@name='warehouseTable']//tr[last()]/td[2]")
    private WebElement warehouseRowLocation;

    //Finding the location in the first row in the warehouse list
    @FindBy(xpath = "//table[@name='warehouseTable']//tr[1]/td[2]")
    private WebElement warehouseFirstRowLocation;

    //Find the title "Warehouse List"
    @FindBy(className = "MuiTypography-root")
    private WebElement warehouseListTitle;

    //Find the "ADD WAREHOUSE" button
    @FindBy(name = "warehouseBtn")
    private WebElement addWarehouseButton;

    //Find last (the latest table row added) warehouse location in warehouse list
    @FindBy(xpath = "//table[@name='warehouseTable']//tr[last()]/td[2]")
    private WebElement warehouseLocation;

    //Find the warehouse menu option
    @FindBy(name = "warehouses")
    private WebElement warehousesMenu;

    //Find the warehouses list option in menu
    @FindBy(name = "warehousesRoute")
    private WebElement warehouseListOption;

    //Find the warehouse table body
    @FindBy(name = "warehouseTBody")
    private WebElement warehouseTableBody;

    public WarehouseList(WebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }


    public void getUrl() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.driver.get(url);
    }

    //=====================UPDATE======================//
    public void clickEditButton() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        editBtn.click();
    }

    public void setLocation(String location){
        try {
            Thread.sleep(3000);
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
            Thread.sleep(3000);
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
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        warehouseBtn.click();
    }

    public void confirmWarehouseUpdation(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("New warehouse location: " + warehouseRowLocation.getText());
    }

     //TODO: check to see if success message pops up

    //=====================DELETE======================//
    public void clickDeleteIcon(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String warehouseName = warehouseFirstRowLocation.getText();
        deleteBtn.click();
        String warehouseList = warehouseTable.getText();
        boolean isWarehousePresent = warehouseList.contains(warehouseName);
        System.out.println(warehouseName);
        System.out.println(warehouseList);
        System.out.println(!isWarehousePresent);
    }

    //======================= CREATE =========================
    public String checkPageTitle() {
        return warehouseListTitle.getText();
    }

    public void addLocation(String location) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        locationField.sendKeys(location);
    }

    public void addCapacity(String capacity) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        capacityField.sendKeys(capacity);
    }
    
    public void clickAddWarehouseButton() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addWarehouseButton.click();
    }

    public void getWarehouseLocation() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(warehouseLocation.getText());
    }

    //======================= READ =========================
    public void getHomeURL() {
        this.driver.get(homeUrl);
    }

    public void clickWarehouseListOption() {
        warehousesMenu.click();        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        warehouseListOption.click();
    }

    public void getWarehouseTable() {
        warehouseTable.getText();
    }

    public void getWarehouseTableContents() {
        List<WebElement> tableRows = warehouseTableBody.findElements(By.xpath(".//tr"));

        for(WebElement tr : tableRows) {
            System.out.println(tr.getText());
        }
    }
}