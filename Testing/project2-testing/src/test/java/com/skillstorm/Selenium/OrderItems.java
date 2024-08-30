package com.skillstorm.Selenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderItems {

    private WebDriver driver;
    private static final String url = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/orderInfo";
    private static final String homeUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/";
    private String firstRow = "";

    @FindBy(xpath = "//div[@id='orderIdSelect']")
    private WebElement customerName;

    @FindBy(xpath = "//div[@id='candySelect']")
    private WebElement candyName;

    @FindBy(name = "price")
    private WebElement price;

    @FindBy(name = "quantity")
    private WebElement quantity;

    @FindBy(name = "orderInfoBtn")
    private WebElement addOrderItemButton;

    @FindBy(name = "editIcon")
    private WebElement editIcon;

    @FindBy(name = "tableBody")
    private WebElement tableBody;

    @FindBy(name = "deleteIcon")
    private WebElement deleteIcon;

    public OrderItems(WebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    public void close() {
        this.driver.quit();
    }

    public void getOrderItemsPage() {
        this.driver.get(url);
    }

    public void selectCustomerName(String customerName) {
        this.customerName.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(!customerName.equals("empty")) {
            this.driver.findElement(By.xpath("//li[text()='" + customerName + "']")).click();
        } else {
            this.driver.findElement(By.xpath("//li[text()='Clear Field']")).click();
        }
    }

    public void selectCandy(String candyName) {
        this.candyName.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(!candyName.equals("empty")) {
            this.driver.findElement(By.xpath("//li[text()='" + candyName + "']")).click();
        } else {
            this.driver.findElement(By.xpath("//li[text()='Clear Field']")).click();
        }
    }
    public void inputPrice(String price) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(!price.equals("empty")) {
            this.price.sendKeys(price); 
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

    public void addOrderItemButton() {
        addOrderItemButton.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clearQuantityField() {
        quantity.sendKeys(Keys.COMMAND + "a");
        quantity.sendKeys(Keys.DELETE);
    }

    public void clearPriceField() {
        price.sendKeys(Keys.COMMAND + "a");
        price.sendKeys(Keys.DELETE);
    }

    public void clickEditIcon() {
        editIcon.click();
    }

    public void getOrderItemsContents() {
        List<WebElement> tableRows = tableBody.findElements(By.xpath(".//tr"));

        for(WebElement tr : tableRows) {
            System.out.println(tr.getText());
        }
    }

    public void clickOnDeleteIcon() {
        firstRow = tableBody.findElement(By.xpath(".//tr[1]")).getText();
        System.out.println("Delete row:" + firstRow);
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
