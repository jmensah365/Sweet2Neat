package com.skillstorm.Selenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.skillstorm.RunCucumberTest;

public class WarehouseStock {

    private WebDriver driver;
    private final String warehouseStockUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/stocks";
    private final String homeUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/";
    private Actions action;

    private String stockId = "";

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

    @FindBy(name = "editIcon")
    private WebElement editIcon;

    @FindBy(name = "warehouseStockBtn")
    private WebElement updateButton;

    @FindBy(name = "tableBody")
    private WebElement tableBody;

    @FindBy(name = "warehouses")
    private WebElement warehousesMenuElement;

    @FindBy(name = "warehousesStockRoute")
    private WebElement warehousesStockMenuElement;

    @FindBy(name = "deleteIcon")
    private WebElement deleteIcon;

    public WarehouseStock(WebDriver driver){
        this.driver = driver;
        this.action = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    public void close() {
        driver.quit();
    }

    public void getWarehoueStockUrl() {
        RunCucumberTest.sleepThread();
        this.driver.get(warehouseStockUrl);
    }

    public void selectCandy(String candyName) {
        RunCucumberTest.sleepThread();
        candySelect.click();
        if(!candyName.equals("empty")) {
            this.driver.findElement(By.xpath("//li[text()='" + candyName + "']")).click();
        } else {
            action.sendKeys(Keys.ESCAPE).perform();
        }
    
    }

    public void selectLocation(String location) {
        RunCucumberTest.sleepThread();
        warehouseSelect.click();
        if(!location.equals("empty")) {
            this.driver.findElement(By.xpath("//li[text()='" + location + "']")).click();
        } else {
            action.sendKeys(Keys.ESCAPE).perform();
        }
    }
    
    public void inputQuantity(String quantity) {
        RunCucumberTest.sleepThread();
        if(!quantity.equals("empty")) {
            this.quantity.sendKeys(quantity); 
        }
    }

    public void addStockBtn() {
        RunCucumberTest.sleepThread();
        addWarehouseButton.click();
    }

    public boolean checkNewWarehouseStock(String candy, String location, String quantity) {
        RunCucumberTest.sleepThread();
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

    public void clickEditIcon() {
        RunCucumberTest.sleepThread();
        editIcon.click();
    }

    public void clickUpdateStockButton() {
        RunCucumberTest.sleepThread();
        updateButton.click();
    }

    public void clearQuantityField() {
        RunCucumberTest.sleepThread();
        quantity.sendKeys(Keys.COMMAND + "a");
        quantity.sendKeys(Keys.DELETE);
    }

    public void getHomePageUrl() {
        RunCucumberTest.sleepThread();
        driver.get(homeUrl);
    }

    public void clickOnWarehouseStockOption() {
        RunCucumberTest.sleepThread();
        warehousesMenuElement.click();
        RunCucumberTest.sleepThread();
        warehousesStockMenuElement.click();
    }

    public String getWarehouseStockUrl() {
        RunCucumberTest.sleepThread();
        return this.driver.getCurrentUrl();
    }

    public void getWarehouseTableContents() {
        RunCucumberTest.sleepThread();
        List<WebElement> tableRows = tableBody.findElements(By.xpath(".//tr"));
        for(WebElement tr : tableRows) {
            System.out.println(tr.getText());
        }
    }

    public void clickDeleteIcon() {
        RunCucumberTest.sleepThread();
        stockId = tableBody.findElement(By.xpath(".//tr[1]")).getAttribute("id");
        System.out.println("Deleted stock Id: " + stockId);
        deleteIcon.click();
    }

    public void confirmDeletion(){
        RunCucumberTest.sleepThread();

        List<WebElement> tableRows = tableBody.findElements(By.xpath(".//tr"));
        for(WebElement tr : tableRows) {
            if(tr.getText().equals(stockId)){
                throw new AssertionError(tableRows + " was found in the table");
            } else{
                System.err.println("False");
            }
        }
    }

}