package com.skillstorm.cucumber;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import com.skillstorm.Selenium.WarehouseList;

public class WarehouseStepDefinitions {

    private WarehouseList warehouseList;
    private String[] warehouse;


    @Before("@Warehouse")
    public void before(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless","--no-sandbox", "--disable-gpu", "--window-size=1920,1080", "--disable-dev-shm-usage");
        WebDriver driver = new ChromeDriver(options);
        this.warehouseList = new WarehouseList(driver);
        warehouse = new String[2];
    }

    @After("@Warehouse") // added for closing all browsers down after testing
    public void tearDown() {
        this.warehouseList.close();
    }

    @Given("I am on the Warehouse List page")
    public void iAmOnTheWarehouseListPage() {
        this.warehouseList.getUrl();
        String expectedHeader = "Warehouse List";
        String actualHeader = warehouseList.checkPageTitle();
        Assert.assertEquals(actualHeader, expectedHeader);
    }

    @When("I click on the edit icon for the Warehouse I want to edit")
    public void iClickOnTheEditIconForTheWarehouseIWantToEdit() {
        this.warehouseList.clickEditButton();
        Assert.assertTrue(this.warehouseList.findEditCancelBtn());
    }

    @When("I click on the delete icon for the Warehouse I want to delete")
    public void iClickOnTheDeleteIconForTheWarehouseIWantToDelete(){
        this.warehouseList.clickDeleteIcon();
    }

    @And("I change the {string} and\\/or {string}")
    public void iChangeWithValidCredentials(String location, String capacity) {
        warehouse[0] = this.warehouseList.setLocation(location);
        warehouse[1] = this.warehouseList.setCapacity(capacity);
    }

    @And("I change the {string} and\\/or {string} to invalid fields")
    public void iChangeWithInvalidCredentials(String location, String capacity) {
        warehouse[0] = this.warehouseList.setLocation(location);
        warehouse[1] = this.warehouseList.setCapacity(capacity);
    }

    @And("I click on the UPDATE WAREHOUSE button")
    public void iClickOnTheUpdateWarehouseButton(){
        warehouseList.clickUpdateWarehouse();
        String expectedMsg = "Warehouse updated successfully!";
        String actualMsg = this.warehouseList.alertMsgText();
        Assert.assertEquals(actualMsg, expectedMsg);
    }
    
    @And("I click the UPDATE WAREHOUSE button")
    public void iClickTheUpdateWarehouseButton(){
        warehouseList.clickUpdateWarehouse();
    }

    @And("I should see the updated details for the Warehouse I edited in the list of Warehouses")
    public void iShouldSeeTheUpdatedDetailsForTheWarehouseIEditedInTheListOfWarehouses(){
        String actualString = this.warehouseList.confirmWarehouseUpdation();
        System.out.println("Actual " + actualString);
        for(String s : warehouse) {
            System.out.println("s " + s);
            Assert.assertTrue(actualString.contains(s));
        }
    }

    @And("the warehouse should not be visible in the table")
    public void theWarehouseShouldNotBeVisibleInTheTable(){
        Assert.assertFalse(this.warehouseList.confirmDeletion());
    }

     //======================= CREATE =========================
    @When("I fill in the {string} and {string} fields")
    public void fillInTheFields(String location, String capacity) {
        warehouse[0] = this.warehouseList.setLocation(location);
        warehouse[1] = this.warehouseList.setCapacity(capacity);
    }

    @And("I click the ADD WAREHOUSE button")
    public void clickTheAddWarehouseButton() {
        this.warehouseList.clickAddWarehouseButton();
        String expectedMsg = "Warehouse added successfully!";
        String actualMsg = this.warehouseList.alertMsgText();
        Assert.assertEquals(actualMsg, expectedMsg);
    }

    @And("I click ADD WAREHOUSE button")
    public void clickAddWarehouseButton() {
        this.warehouseList.clickAddWarehouseButton();
    }

    @Then("I should see an alert message {string}")
    public void seeAlertErrorMessage(String msg) {
        String expectedMsg = msg;
        String actualMsg = this.warehouseList.alertMsgText();
        Assert.assertEquals(actualMsg, expectedMsg);
    }

    @Then("I should see the newly created Warehouse in the list of Warehouses")
    public void iShouldSeeNewlyCreatedWarehouse() {
        String actualString = this.warehouseList.confirmWarehouseCreation();
        for(String s : warehouse) {
            Assert.assertTrue(actualString.contains(s));
        }
    }

    //======================= READ =========================
    @Given("I am on the home page")
    public void givenIAmOnTheHomePage() {
        this.warehouseList.getHomeURL();
        String expectedUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/";
        String actualUrl = this.warehouseList.checkCurrentPageUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @When("I click on the Warehouse List option")
    public void clickOnWarehouseListOption() {
        this.warehouseList.clickWarehouseListOption();
        String expectedUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/warehouses";
        String actualUrl = this.warehouseList.checkCurrentPageUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }
    
    @Then("I should be navigated to the Warehouse List page")
    public void iAmNavigatedToWarehouseListPage() {
        this.warehouseList.getUrl();
        String expectedUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/warehouses";
        String actualUrl = this.warehouseList.checkCurrentPageUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @And("I should see a list of all warehouses available")
    public void iSeeListOfWarehouses() {
        String expectedString = "Warehouse Id Location Stock/Capacity Actions";
        String actualUrl = this.warehouseList.getWarehouseTableContents();
        Assert.assertEquals(actualUrl, expectedString);
    }

}