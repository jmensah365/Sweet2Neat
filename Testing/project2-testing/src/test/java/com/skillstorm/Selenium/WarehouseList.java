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
    
    // WebDriver instance to control the browser
    private WebDriver driver;

    // URLs for the warehouse list page and the home page
    // private static final String url = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/warehouses";
    // private static final String homeUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/";

    private static final String url = "http://localhost:5173/warehouses";
    private static final String homeUrl = "http://localhost:5173/";

    /*
     * Locators for the form fields on the warehouse page
     */
    @FindBy(name = "location")
    private WebElement locationField;

    @FindBy(name = "capacity")
    private WebElement capacityField;

    @FindBy(name = "currentStock")
    private WebElement stockField;

    // Locator for the edit icon button
    @FindBy(name = "editIcon")
    private WebElement editBtn;

    // Locator for the delete icon button
    @FindBy(name = "deleteIcon")
    private WebElement deleteBtn;

    // Locator for the warehouses menu option
    @FindBy(name = "warehouses")
    private WebElement warehousesMenu;

    // Locator for the update warehouse button
    @FindBy(name = "warehouseBtn")
    private WebElement warehouseBtn;

    // Locator for the "ADD WAREHOUSE" button
    @FindBy(name = "warehouseBtn")
    private WebElement addWarehouseButton;

    // Locator for the title "Warehouse List"
    @FindBy(className = "MuiTypography-root")
    private WebElement warehouseListTitle;

    // Locator for the table of warehouses
    @FindBy(name = "warehouseTable")
    private WebElement warehouseTable;

    // Locator for the warehouses list option in the menu
    @FindBy(name = "warehousesRoute")
    private WebElement warehouseListOption;

    // Locator for the location in the last row in the warehouse list table
    @FindBy(xpath = "//table[@name='warehouseTable']//tr[last()]/td[2]")
    private WebElement warehouseRowLocation;

    // Locator for the location in the first row in the warehouse list table
    @FindBy(xpath = "//table[@name='warehouseTable']//tr[1]/td[1]")
    private WebElement warehouseFirstRowLocation;

    // Locator for the warehouse list snackbar error message
    @FindBy(name = "warehouseListSnackbarError")
    private WebElement warehouseListSnackbarErrorMessage;

    // Variable to store the warehouse ID
    String warehouseId = "";

    // Constructor to initialize the WebDriver and PageFactory elements
    public WarehouseList(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    // Method to close the WebDriver and browser
    public void close() {
        this.driver.close();
    }

    // Method to navigate to the warehouse list page URL
    public void getUrl() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.driver.get(url);
    }

    //=====================UPDATE======================//
    
    // Method to click the edit button for a warehouse
    public void clickEditButton() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        editBtn.click();
    }

    // Method to update the location field
    public void setLocation(String location) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Clear the existing value
        String prevValue = locationField.getAttribute("value");
        for (int i = 0; i < prevValue.length(); i++) {
            locationField.sendKeys(Keys.BACK_SPACE);
        }
        // Enter the new value
        locationField.sendKeys(location);
    }

    // Method to update the capacity field
    public void setCapacity(String capacity) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Clear the existing value
        String prevValue = capacityField.getAttribute("value");
        for (int i = 0; i < prevValue.length(); i++) {
            capacityField.sendKeys(Keys.BACK_SPACE);
        }
        // Enter the new value
        capacityField.sendKeys(capacity);
    }

    // Method to click the "Update Warehouse" button
    public void clickUpdateWarehouse() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        warehouseBtn.click();
    }

    // Method to confirm the warehouse update by printing the last row's location
    public void confirmWarehouseUpdation() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("New warehouse location: " + warehouseRowLocation.getText());
    }

    //=====================DELETE======================//
    
    // Method to click the delete icon for the first row in the warehouse list table
    public void clickDeleteIcon() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Get the warehouse ID from the first row
        warehouseId = warehouseFirstRowLocation.getText();
        deleteBtn.click();
    }

    // Method to confirm deletion by checking if the warehouse ID is no longer in the table
    public void confirmDeletion() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> tableRows = warehouseTable.findElements(By.xpath(".//tr/td[1]"));
        for (WebElement tr : tableRows) {
            if (tr.getText().contains(warehouseId)) {
                System.out.println("True");
            } else {
                System.err.println("False");
            }
        }
    }

    //======================= CREATE =========================
    
    // Method to check the page title
    public String checkPageTitle() {
        return warehouseListTitle.getText();
    }

    // Method to add a location for a new warehouse
    public void addLocation(String location) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        locationField.sendKeys(location);
    }

    // Method to add capacity for a new warehouse
    public void addCapacity(String capacity) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        capacityField.sendKeys(capacity);
    }

    // Method to set the current stock for a new warehouse
    public void setCurrentStock(String currentStock) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stockField.sendKeys(currentStock);
    }

    // Method to click the "ADD WAREHOUSE" button
    public void clickAddWarehouseButton() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addWarehouseButton.click();
    }

    // Method to get the location of the last row in the warehouse list
    public void getWarehouseLocation() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(warehouseRowLocation.getText());
    }

    public void confirmErrorMessage(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(warehouseListSnackbarErrorMessage.isDisplayed());
    }

    //======================= READ =========================
    
    // Method to navigate to the home page URL
    public void getHomeURL() {
        this.driver.get(homeUrl);
    }

    // Method to navigate to the warehouse list option
    public void clickWarehouseListOption() {
        warehousesMenu.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        warehouseListOption.click();
    }

    // Method to get the text of the warehouse table
    public void getWarehouseTable() {
        warehouseTable.getText();
    }

    // Method to print the contents of the warehouse table
    public void getWarehouseTableContents() {
        List<WebElement> tableRows = warehouseTable.findElements(By.xpath(".//tr"));

        for (WebElement tr : tableRows) {
            System.out.println(tr.getText());
        }
    }

}