package com.skillstorm.Selenium;

import java.time.Duration;

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

    public OrderItems(WebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    public void close() {
        this.driver.close();
    }

    public void getOrderItemsPage() {
        this.driver.get(url);
    }

    public void selectCustomerName(String customerName) {
        this.customerName.click();
        try {
            Thread.sleep(1000);
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
            Thread.sleep(1000);
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
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(!price.equals("empty")) {
            this.price.sendKeys(price); 
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

    public void addOrderItemButton() {
        addOrderItemButton.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
