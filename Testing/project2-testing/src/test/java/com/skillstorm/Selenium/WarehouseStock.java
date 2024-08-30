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

public class WarehouseStock {

    private WebDriver driver;
    private final String warehouseStockUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/stocks";
    private final String homeUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/";
    private Actions action;

    private String firstRow = "";

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
        this.driver.get(warehouseStockUrl);
    }

    public void selectCandy(String candyName) {
        candySelect.click();
        try {
            Thread.sleep(500);
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
            Thread.sleep(500);
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
            Thread.sleep(500);
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
            Thread.sleep(500);
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

    public void clickEditIcon() {
        editIcon.click();
    }

    public void clickUpdateStockButton() {
        updateButton.click();
    }

    public void clearQuantityField() {
        quantity.sendKeys(Keys.COMMAND + "a");
        quantity.sendKeys(Keys.DELETE);
    }

    public void getHomePageUrl() {
        driver.get(homeUrl);
    }

    public void clickOnWarehouseStockOption() {
        warehousesMenuElement.click();
        warehousesStockMenuElement.click();
    }

    public String getWarehouseStockUrl() {
        return this.driver.getCurrentUrl();
    }

    public void getWarehouseTableContents() {
        List<WebElement> tableRows = tableBody.findElements(By.xpath(".//tr"));

        for(WebElement tr : tableRows) {
            System.out.println(tr.getText());
        }
    }

    public void clickDeleteIcon() {
        firstRow = tableBody.findElement(By.xpath(".//tr[1]")).getText();
        System.out.println("Deleted row: " + firstRow);
        deleteIcon.click();
    }

    public void confirmDeletion(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> tableRows = tableBody.findElements(By.xpath(".//tr"));
        for(WebElement tr : tableRows) {
            if(tr.getText().equals(firstRow)){
                throw new AssertionError(tableRows + " was found in the table");
            } else{
                System.err.println("False");
            }
        }
    }

}