package com.skillstorm.cucumber;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import com.skillstorm.Selenium.WarehouseStock;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WarehouseStockSteps {

    private WarehouseStock warehouseStock;

    @Before("@WarehouseStock")
    public void before(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless","--no-sandbox", "--disable-gpu", "--window-size=1920,1080", "--disable-dev-shm-usage");
        WebDriver driver = new ChromeDriver(options);

        this.warehouseStock = new WarehouseStock(driver);
    }

    @After("@WarehouseStock")
    public void after() {
        this.warehouseStock.close();
    }

    //=============================== CREATE ====================================//

    @Given("I am on the Warehouse Stock page")
    public void onWarehouseStockPage() {
        this.warehouseStock.getWarehoueStockUrl();
        String expectedUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/stocks";
        String actualUrl = this.warehouseStock.checkCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @When("I select the {string}, {string} and input {string} fields")
    public void selectWarehouseStockFields(String candy, String location, String quantity) {
        this.warehouseStock.selectCandy(candy);
        this.warehouseStock.selectLocation(location);
        this.warehouseStock.inputQuantity(quantity);
    }

    @When("I don't select {string}, {string} and input {string} fields")
    public void dontSelectWarehouseStockFields(String candy, String location, String quantity) {
        this.warehouseStock.selectCandy(candy);
        this.warehouseStock.selectLocation(location);
        this.warehouseStock.inputQuantity(quantity);
    }

    @And("I click the Add Stock button")
    public void clickAddStockButton() {
        this.warehouseStock.addStockBtn();
        String expectedUrl = "Added stock successfully!";
        String actualUrl = this.warehouseStock.getAlertMsg();
        Assert.assertEquals(actualUrl, expectedUrl);
    }
    
    @And("I click on the Add Stock button")
    public void clickOnAddStockButton() {
        this.warehouseStock.addStockBtn();
    }

    @Then("I should see the last row with {string}, {string}, and {string}")
    public void shouldSeeNewWarehouseStock(String candy, String location, String quantity) {
        System.out.println(this.warehouseStock.checkNewWarehouseStock(candy, location, quantity));
    }

    @Then("the warehouse stock should not be added with {string}, {string}, and {string}")
    public void shouldNotSeeNewWarehouseStock(String candy, String location, String quantity) {
        System.out.println(this.warehouseStock.checkNewWarehouseStock(candy, location, quantity));
    }

    @And("should see an alert message {string}") 
    public void iShouldSeeAlertMessage(String msg) {
        String expectedUrl = msg;
        String actualUrl = this.warehouseStock.getAlertMsg();
        Assert.assertEquals(actualUrl, expectedUrl);
    }
    //=============================== UPDATE ====================================//
    @When("I click the edit icon")
    public void clickEditIcon() {
        this.warehouseStock.clickEditIcon();
        Assert.assertTrue(this.warehouseStock.isCancelBtn());
    }

    @Then("I select a new {string}, {string} and input {string} fields")
    public void selectNewAndInputFields(String candy, String location, String quantity) {
        this.warehouseStock.selectCandy(candy);
        this.warehouseStock.selectLocation(location);
        this.warehouseStock.clearQuantityField();
        this.warehouseStock.inputQuantity(quantity);
    }

    @And("I don't select a new {string}, {string} and input {string} fields")
    public void doNotSelectWarehouseStockFields(String candy, String location, String quantity) {
        this.warehouseStock.selectCandy(candy);
        this.warehouseStock.selectLocation(location);
        this.warehouseStock.clearQuantityField();
        this.warehouseStock.inputQuantity(quantity);
    }

    @And("I click the Update Stock button")
    public void clickUpdateStockButton() {
        this.warehouseStock.clickUpdateStockButton();
        String expectedUrl = "Updated stock successfully!";
        String actualUrl = this.warehouseStock.getAlertMsg();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @And("I click on the Update Stock button")
    public void clickOnUpdateStockButton() {
        this.warehouseStock.clickUpdateStockButton();
    }

    @Then("the warehouse stock should not be updated with {string}, {string}, and {string}")
    public void shouldNotBeUpdatedWith(String candy, String location, String quantity) {
        Assert.assertFalse(this.warehouseStock.checkNewWarehouseStock(candy, location, quantity));
    }

    //=============================== READ ====================================//
    @Given("I am starting on the home page")
    public void currentlyOnTheHomePage() {
        this.warehouseStock.getHomePageUrl();
        String expectedUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/";
        String actualUrl = this.warehouseStock.checkCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @When("I click on the Warehouse Stock option")
    public void clickOnWarehouseStockOption() {
        this.warehouseStock.clickOnWarehouseStockOption();
        String expectedUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/stocks";
        String actualUrl = this.warehouseStock.checkCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @Then("I should be navigated to the Warehouse Stock page")
    public void shouldBeNavigatedToWarehouseStock() {
        System.out.println(this.warehouseStock.getWarehouseStockUrl());
        String expectedUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/stocks";
        String actualUrl = this.warehouseStock.checkCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @And("I should see a list of all warehouse stocks available")
    public void shouldSeeListOfAllWarehouseStocks() {
        String expectedString = "Warehouse Candy Name Quantity Actions";
        String actualString = this.warehouseStock.getWarehouseTableContents();
        Assert.assertEquals(actualString, expectedString);
    }

    //=============================== DELETE ====================================//
    @When("I click on the delete icon for the Warehouse Stock I want to delete")
    public void clickOnDeleteIconForWarehouseStock() {
        this.warehouseStock.clickDeleteIcon();
        String expectedUrl = "Stock deleted successfully!";
        String actualUrl = this.warehouseStock.getAlertMsg();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @Then("the warehouse stock should not be visible in the table")
    public void warehouseStockShouldNotBeVisible() {
        Assert.assertFalse(this.warehouseStock.confirmDeletion());
    }
}