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

    @FindBy(name = "orderInfoTable")
    private WebElement orderInfoTable;

    @FindBy(name = "deleteIcon")
    private WebElement deleteIcon;

    @FindBy(className = "MuiAlert-message")
    private WebElement alertMsg;

    @FindBy(name = "cancelEditBtn")
    private WebElement cancelBtn;

    public OrderItems(WebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    public void close() {
        this.driver.quit();
    }

    public String checkCurrentUrl() {
        return this.driver.getCurrentUrl();
    }

    public void getOrderItemsPage() {
        RunCucumberTest.sleepThread();
        this.driver.get(url);
    }

    public String selectCustomerName(String customerName) {
        RunCucumberTest.sleepThread();
        this.customerName.click();
        RunCucumberTest.sleepThread();
        if(!customerName.isEmpty()) {
            this.driver.findElement(By.xpath("//li[text()='" + customerName + "']")).click();
        } else {
            this.driver.findElement(By.xpath("//li[text()='Clear Field']")).click();
        }
        return customerName;
    }

    public String selectCandy(String candyName) {
        RunCucumberTest.sleepThread();
        this.candyName.click();
        RunCucumberTest.sleepThread();
        if(!candyName.isEmpty()) {
            this.driver.findElement(By.xpath("//li[text()='" + candyName + "']")).click();
        } else {
            this.driver.findElement(By.xpath("//li[text()='Clear Field']")).click();
        }
        return candyName;
    }

    public String inputPrice(String price) {
        RunCucumberTest.sleepThread();
        if(!price.isEmpty()) {
            this.price.sendKeys(price); 
        }
        return price;
    }

    public String inputQuantity(String quantity) {
        RunCucumberTest.sleepThread();
        if(!quantity.isEmpty()) {
            this.quantity.sendKeys(quantity); 
        }
        return quantity;
    }

    public void addOrderItemButton() {
        RunCucumberTest.sleepThread();
        addOrderItemButton.click();
    }

    public void updateOrderItemButton() {
        addOrderItemButton.click();
        RunCucumberTest.sleepThread();
        this.driver.navigate().refresh();
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

    public String getOrderItemsContents() {
        RunCucumberTest.sleepThread();
        return tableBody.findElement(By.xpath(".//tr[last()]")).getText();
    }

    public void clickOnDeleteIcon() {
        RunCucumberTest.sleepThread();
        orderItemId = tableBody.findElement(By.xpath(".//tr[1]")).getAttribute("id");
        System.out.println("Delete OrderItem Id:" + orderItemId);
        deleteIcon.click();
    }

    public boolean confirmDeletion(){
        RunCucumberTest.sleepThread();
        List<WebElement> tableRows = tableBody.findElements(By.xpath(".//tr"));
        for(WebElement tr : tableRows) {
            if(tr.getText().equals(orderItemId)){
                return true;
            }
        }
        return false;
    }

    public String getAlertMsg() {
        RunCucumberTest.sleepThread();
        return alertMsg.getText();
    }

    public boolean isCancelBtn() {
        if(cancelBtn != null) {
            return true;
        }
        return false;
    }
}
