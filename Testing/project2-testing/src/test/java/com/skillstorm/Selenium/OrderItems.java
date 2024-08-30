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

public class OrderItems {

    private WebDriver driver;
    private static final String url = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/orderInfo";
    private static final String homeUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/";
    private String orderItemId = "";

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
        RunCucumberTest.sleepThread();
        this.driver.get(url);
    }

    public void selectCustomerName(String customerName) {
        RunCucumberTest.sleepThread();
        this.customerName.click();
        RunCucumberTest.sleepThread();
        if(!customerName.equals("empty")) {
            this.driver.findElement(By.xpath("//li[text()='" + customerName + "']")).click();
        } else {
            this.driver.findElement(By.xpath("//li[text()='Clear Field']")).click();
        }
    }

    public void selectCandy(String candyName) {
        RunCucumberTest.sleepThread();
        this.candyName.click();
        RunCucumberTest.sleepThread();
        if(!candyName.equals("empty")) {
            this.driver.findElement(By.xpath("//li[text()='" + candyName + "']")).click();
        } else {
            this.driver.findElement(By.xpath("//li[text()='Clear Field']")).click();
        }
    }
    public void inputPrice(String price) {
        RunCucumberTest.sleepThread();
        if(!price.equals("empty")) {
            this.price.sendKeys(price); 
        }
    }

    public void inputQuantity(String quantity) {
        RunCucumberTest.sleepThread();
        if(!quantity.equals("empty")) {
            this.quantity.sendKeys(quantity); 
        }
    }

    public void addOrderItemButton() {
        RunCucumberTest.sleepThread();
        addOrderItemButton.click();
    }

    public void clearQuantityField() {
        RunCucumberTest.sleepThread();
        quantity.sendKeys(Keys.COMMAND + "a");
        quantity.sendKeys(Keys.DELETE);
    }

    public void clearPriceField() {
        RunCucumberTest.sleepThread();
        price.sendKeys(Keys.COMMAND + "a");
        price.sendKeys(Keys.DELETE);
    }

    public void clickEditIcon() {
        RunCucumberTest.sleepThread();
        editIcon.click();
    }

    public void getOrderItemsContents() {
        RunCucumberTest.sleepThread();
        List<WebElement> tableRows = tableBody.findElements(By.xpath(".//tr"));

        for(WebElement tr : tableRows) {
            System.out.println(tr.getText());
        }
    }

    public void clickOnDeleteIcon() {
        RunCucumberTest.sleepThread();
        orderItemId = tableBody.findElement(By.xpath(".//tr[1]")).getAttribute("id");
        System.out.println("Delete OrderItem Id:" + orderItemId);
        deleteIcon.click();
    }

    public void confirmDeletion(){
        RunCucumberTest.sleepThread();
        List<WebElement> tableRows = tableBody.findElements(By.xpath(".//tr"));
        for(WebElement tr : tableRows) {
            if(tr.getText().equals(orderItemId)){
                throw new AssertionError(tableRows + " was found in the table");
            } else{
                System.err.println("False");
            }
        }
    }
}
