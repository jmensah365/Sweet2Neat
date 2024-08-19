package com.skillstorm.Selenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CandyList {
    
    private WebDriver driver;
    private static final String url = "http://localhost:5173/candy";
    private static final String homeUrl = "http://localhost:5173/";

    /*
     * Finding name, type, flavor, price, and weight text fields
     */

    @FindBy(name = "name")
    private WebElement nameField;

    @FindBy(name = "type")
    private WebElement typeField;

    @FindBy(name = "flavor")
    private WebElement flavorField;

    @FindBy(name = "price")
    private WebElement priceFeild;
    
    @FindBy(name = "weight")
    private WebElement weightField;

    //Finding ADD CANDY button
    @FindBy(name = "candyBtn")
    private WebElement candyBtn;

    //Finding candy button on nav bar
    @FindBy(name = "candy")
    private WebElement candyNavBtn;

    //Finding candy inventory button
    @FindBy(name = "candyRoute")
    private WebElement candyInventoryBtn;

    //Finding candy table
    @FindBy(name = "candyTable")
    private WebElement candyTable;

    //Finding last row in Candy table and grabbing the candy ID
    @FindBy(xpath = "//table[@name='candyTable']//tr[last()]/td[1]")
    private WebElement lastRowCandyId;

    public CandyList(WebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    public void getCandyPageUrl(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.driver.get(url);
    }

    //========================CREATE==========================//
    public void addCandyName(String candyName){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        nameField.sendKeys(candyName);
    }

    public void addCandyType(String candyType){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        typeField.sendKeys(candyType);
    }

    public void addCandyFlavor(String candyFlavor){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        flavorField.sendKeys(candyFlavor);
    }

    public void addPrice(String price){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        priceFeild.sendKeys(price);
    }
    public void addWeight(String weight){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        weightField.sendKeys(weight);
    }

    public void clickCandyBtn(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        candyBtn.click();
    }

    public void confirmCreation(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(lastRowCandyId.getText());
    }
    //========================CREATE==========================//

    //========================READ==========================//
    public void getHomeUrl(){
        this.driver.get(homeUrl);
    }

    public void clickCandyInventory(){
        candyNavBtn.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        candyInventoryBtn.click();
    }

    public void displayCandyTable(){
        List<WebElement> tableRows = candyTable.findElements(By.xpath(".//tr"));

        for(WebElement tr : tableRows) {
            System.out.println(tr.getText());
        }
    }
    //========================READ==========================//
}
