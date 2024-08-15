package com.skillstorm.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WarehouseListPage {

    private WebDriver driver;

    private static final String url = "http://localhost:8080";
    
    @FindBy(tagName = "h4")
    private WebElement warehouseListTitle;

    @FindBy(name = "location")
    private WebElement locationInput;

    @FindBy(name = "capacity")
    private WebElement capacityInput;

    @FindBy(name = "currentStock")
    private WebElement currentStockInput;

    @FindBy(className = "MuiButtonBase-root")
    private WebElement addWarehouseButton;

    public WarehouseListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void getPage() {
        this.driver.get(url);
    }

    public String checkPageTitle() {
        return warehouseListTitle.getText();
    }

    public void setLocation(String location) {
        locationInput.sendKeys(location);
    }

    public void setCapacity(String capacity) {
        capacityInput.sendKeys(capacity);
    }
    
    public void setCurrentStock(String currentStock) {
        currentStockInput.sendKeys(currentStock);
    }

    public void clickAddWarehouseButton() {
        addWarehouseButton.click();
    }
    
}
