package com.skillstorm.Selenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.cucumber.java.en_old.Ac;

public class WarehouseStock {

    private WebDriver driver;
    private final String warehouseStockUrl = "http://localhost:5173/stocks";
    private Actions action;

    @FindBy(xpath = "//div[@id='candyIdSelect']")
    private WebElement candySelect;

    @FindBy(xpath = "//div[@id='warehouseIdSelect']")
    private WebElement warehouseSelect;

    @FindBy(name = "quantity")
    private WebElement quantity;

    @FindBy(name = "warehouseStockBtn")
    private WebElement addWarehouseButton;

    @FindBy(name = "tableBody")
    private WebElement warehouseStockTBody;

    public WarehouseStock(WebDriver driver){
        this.driver = driver;
        this.action = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    public void close() {
        driver.close();
    }

    public void getWarehoueStockUrl() {
        this.driver.get(warehouseStockUrl);
    }

    public void selectCandy(String candyName) {
        candySelect.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(!candyName.equals("empty")) {
            this.driver.findElement(By.xpath("//li[text()='" + candyName + "']")).click();
        } else {
            action.sendKeys(Keys.ESCAPE).perform();
        }
    
    }

    public void selectLocation(String location) {
        warehouseSelect.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(!location.equals("empty")) {
            this.driver.findElement(By.xpath("//li[text()='" + location + "']")).click();
        } else {
            action.sendKeys(Keys.ESCAPE).perform();
        }
    }
    
    public void inputQuantity(String quantity) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(!quantity.equals("empty")) {
            this.quantity.sendKeys(quantity); 
        }
    }

    public void addStockBtn() {
        addWarehouseButton.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean checkNewWarehouseStock(String candy, String location, String quantity) {
        WebElement lastTableRow = warehouseStockTBody.findElement(By.xpath(".//tr[last()]"));

        if(!candy.equals("empty")) {
            candy = "";
        }
        if(!location.equals("empty")) {
            location = "";
        } 
        if(!quantity.equals("empty")) {
            quantity = "";
        }

        String warehouseStockText = location + " " + candy + " " + quantity;

        if(lastTableRow.getText().equals(warehouseStockText)) {
            return true;
        }
        return false;
    }

}
