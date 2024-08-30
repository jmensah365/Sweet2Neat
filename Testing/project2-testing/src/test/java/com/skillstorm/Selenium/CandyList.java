package com.skillstorm.Selenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CandyList {
    
    private WebDriver driver;
    // private static final String url = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/candy";
    // private static final String homeUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/";

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
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.driver.get(url);
    }

    //========================CREATE==========================//
    public void addCandyName(String candyName){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        nameField.sendKeys(candyName);
    }

    public void addCandyType(String candyType){
        try {
            Thread.sleep(2000);

            if (candyType != null && !candyType.isEmpty()){
                candyTypeSelect.click();
                Thread.sleep(2000);
                WebElement option = driver.findElement(By.xpath("//li[@data-value='" + candyType + "']"));
                option.click();
            } else{
                candyTypeSelect.click();
                WebElement option = driver.findElement(By.xpath("//li[@data-value='']"));
                option.click();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void addCandyFlavor(String candyFlavor){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        flavorField.sendKeys(candyFlavor);
    }

    public void addPrice(String price){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        priceFeild.sendKeys(price);
    }
    public void addWeight(String weight){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        weightField.sendKeys(weight);
    }

    public void clickCandyBtn(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        candyBtn.click();
    }

    public void confirmCreation(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(lastRowCandyId.getText());
    }
    public void errorMessageDisplayed(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(candyErrorMessage.isDisplayed());
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

    //========================UPDATE==========================//
    public void clickEditButton(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        editBtn.click();
    }

    public void setNameField(String name){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String prevValue = nameField.getAttribute("value");
        for (int i = 0; i < prevValue.length(); i ++){
            nameField.sendKeys(Keys.BACK_SPACE);
        }
        nameField.sendKeys(name);
    }


    public void setTypeField(String type){
        try {
            Thread.sleep(2000);

            candyTypeSelect.click();


            if (type != null && !type.isEmpty()){
                Thread.sleep(2000);
                WebElement option = driver.findElement(By.xpath("//li[@data-value='" + type + "']"));
                option.click();
            } else{
                WebElement option = driver.findElement(By.xpath("//li[@data-value='']"));
                option.click();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void setFlavorField(String flavor){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String prevValue = flavorField.getAttribute("value");
        for (int i = 0; i < prevValue.length(); i ++){
            flavorField.sendKeys(Keys.BACK_SPACE);
        }
        flavorField.sendKeys(flavor);
    }


    public void setPriceField(String price){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String prevValue = priceFeild.getAttribute("value");
        for (int i = 0; i < prevValue.length(); i ++){
            priceFeild.sendKeys(Keys.BACK_SPACE);
        }
        priceFeild.sendKeys(price);
    }


    public void setWeightField(String weight){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String prevValue = weightField.getAttribute("value");
        for (int i = 0; i < prevValue.length(); i ++){
            weightField.sendKeys(Keys.BACK_SPACE);
        }
        weightField.sendKeys(weight);
    }

    public void confirmUpdation(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Updated Candy ID " + firstRowCandyId.getText());
    }

    //========================UPDATE==========================//




    //========================DELETE==========================//
    public void clickDeleteIcon() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Update to get the candy ID from the last row
        candyId = lastRowCandyId.getText();
        
        // Find the delete button for the last row
        WebElement lastRowDeleteBtn = driver.findElement(By.xpath("//table[@name='candyTable']//tbody/tr[last()]/td[last()]/button[@name='deleteIcon']\n" + //
                ""));
        
        // Click the delete button for the last row
        lastRowDeleteBtn.click();
    }

    public void confirmDeletion(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
