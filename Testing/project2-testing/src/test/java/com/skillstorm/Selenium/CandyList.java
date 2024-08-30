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

public class CandyList {
    
    private WebDriver driver;
    private static final String url = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/candy";
    private static final String homeUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/";

    // private static final String url = "http://localhost:5173/candy";
    // private static final String homeUrl = "http://localhost:5173/";

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

     //Finding first row in Candy table and grabbing the candy ID
    @FindBy(xpath = "//table[@name='candyTable']//tr[1]/td[1]")
    private WebElement firstRowCandyId;

    @FindBy(name = "deleteIcon")
    private WebElement deleteBtn;

    @FindBy(name = "editIcon")
    private WebElement editBtn;

    @FindBy(id = "candyTypeSelect")
    private WebElement candyTypeSelect;

    @FindBy(name = "candyListSnackbarError")
    private WebElement candyErrorMessage;

    String candyId = "";

    public CandyList(WebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    public void quit() {
        this.driver.quit();
    }

    public void getCandyPageUrl(){
        RunCucumberTest.sleepThread();
        this.driver.get(url);
    }

    //========================CREATE==========================//
    public void addCandyName(String candyName){
        RunCucumberTest.sleepThread();
        nameField.sendKeys(candyName);
    }

    public void addCandyType(String candyType){
        RunCucumberTest.sleepThread();
        if (candyType != null && !candyType.isEmpty()){
            candyTypeSelect.click();
        RunCucumberTest.sleepThread();
            WebElement option = driver.findElement(By.xpath("//li[@data-value='" + candyType + "']"));
            option.click();
        } else{
            candyTypeSelect.click();
            WebElement option = driver.findElement(By.xpath("//li[@data-value='']"));
            option.click();
        }
    }

    public void addCandyFlavor(String candyFlavor){
        RunCucumberTest.sleepThread();
        flavorField.sendKeys(candyFlavor);
    }

    public void addPrice(String price){
        RunCucumberTest.sleepThread();
        priceFeild.sendKeys(price);
    }
    public void addWeight(String weight){
        RunCucumberTest.sleepThread();
        weightField.sendKeys(weight);
    }

    public void clickCandyBtn(){
        RunCucumberTest.sleepThread();
        candyBtn.click();
    }

    public void confirmCreation(){
        RunCucumberTest.sleepThread();
        
        System.out.println(lastRowCandyId.getText());
    }
    public void errorMessageDisplayed(){
        RunCucumberTest.sleepThread();
        System.out.println(candyErrorMessage.isDisplayed());
    }
    //========================CREATE==========================//

    //========================READ==========================//
    public void getHomeUrl(){
        RunCucumberTest.sleepThread();
        this.driver.get(homeUrl);
    }

    public void clickCandyInventory(){
        RunCucumberTest.sleepThread();
        candyNavBtn.click();
        RunCucumberTest.sleepThread();
        candyInventoryBtn.click();
    }

    public void displayCandyTable(){
        RunCucumberTest.sleepThread();
        List<WebElement> tableRows = candyTable.findElements(By.xpath(".//tr"));

        for(WebElement tr : tableRows) {
            System.out.println(tr.getText());
        }
    }
    //========================READ==========================//

    //========================UPDATE==========================//
    public void clickEditButton(){
        RunCucumberTest.sleepThread();

        editBtn.click();
    }

    public void setNameField(String name){
        RunCucumberTest.sleepThread();

        String prevValue = nameField.getAttribute("value");
        for (int i = 0; i < prevValue.length(); i ++){
            nameField.sendKeys(Keys.BACK_SPACE);
        }
        nameField.sendKeys(name);
    }


    public void setTypeField(String type){
        RunCucumberTest.sleepThread();
            candyTypeSelect.click();


        RunCucumberTest.sleepThread();
            if (type != null && !type.isEmpty()){
                WebElement option = driver.findElement(By.xpath("//li[@data-value='" + type + "']"));
                option.click();
            } else{
                WebElement option = driver.findElement(By.xpath("//li[@data-value='']"));
                option.click();
            }
    }


    public void setFlavorField(String flavor){
        RunCucumberTest.sleepThread();
        String prevValue = flavorField.getAttribute("value");
        for (int i = 0; i < prevValue.length(); i ++){
            flavorField.sendKeys(Keys.BACK_SPACE);
        }
        flavorField.sendKeys(flavor);
    }


    public void setPriceField(String price){
        RunCucumberTest.sleepThread();

        String prevValue = priceFeild.getAttribute("value");
        for (int i = 0; i < prevValue.length(); i ++){
            priceFeild.sendKeys(Keys.BACK_SPACE);
        }
        priceFeild.sendKeys(price);
    }


    public void setWeightField(String weight){
        RunCucumberTest.sleepThread();

        String prevValue = weightField.getAttribute("value");
        for (int i = 0; i < prevValue.length(); i ++){
            weightField.sendKeys(Keys.BACK_SPACE);
        }
        weightField.sendKeys(weight);
    }

    public void confirmUpdation(){
        RunCucumberTest.sleepThread();

        System.out.println("Updated Candy ID " + firstRowCandyId.getText());
    }

    //========================UPDATE==========================//




    //========================DELETE==========================//
    public void clickDeleteIcon() {
        RunCucumberTest.sleepThread();
        // Update to get the candy ID from the last row
        candyId = lastRowCandyId.getText();
        
        // Find the delete button for the last row
        WebElement lastRowDeleteBtn = driver.findElement(By.xpath("//table[@name='candyTable']//tbody/tr[last()]/td[last()]/button[@name='deleteIcon']\n" + //
                ""));
        
        // Click the delete button for the last row
        lastRowDeleteBtn.click();
    }

    public void confirmDeletion(){
        RunCucumberTest.sleepThread();
        List<WebElement> tableRows = candyTable.findElements(By.xpath(".//tr/td[1]"));
        for(WebElement tr : tableRows) {
            if(tr.getText().contains(candyId)){
                throw new AssertionError("Candy with ID " + candyId + " was found in the table");
            } else{
                System.err.println("False");
            }
        }
    }
    //========================DELETE==========================//
}