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

    // setup
    @Before("@WarehouseStock")
    public void before(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless","--no-sandbox", "--disable-gpu", "--window-size=1920,1080", "--disable-dev-shm-usage");
        WebDriver driver = new ChromeDriver(options);

        this.warehouseStock = new WarehouseStock(driver);
    }

    // teardown
    @After("@WarehouseStock")
    public void after() {
        this.warehouseStock.close();
    }

    //=============================== CREATE ====================================//

    // user starts on warehouse stock page check with url
    @Given("I am on the Warehouse Stock page")
    public void onWarehouseStockPage() {
        this.warehouseStock.getWarehoueStockUrl();
        String expectedUrl = "http://localhost:5173/stocks";
        String actualUrl = this.warehouseStock.checkCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    // user selects and inputs valid fields
    @When("I select the {string}, {string} and input {string} fields")
    public void selectWarehouseStockFields(String candy, String location, String quantity) {
        this.warehouseStock.selectCandy(candy);
        this.warehouseStock.selectLocation(location);
        this.warehouseStock.inputQuantity(quantity);
    }

    // user doesnt select and/or inputs invalid inputs
    @When("I don't select {string}, {string} and input {string} fields")
    public void dontSelectWarehouseStockFields(String candy, String location, String quantity) {
        this.warehouseStock.selectCandy(candy);
        this.warehouseStock.selectLocation(location);
        this.warehouseStock.inputQuantity(quantity);
    }

    // sim user click on add button and check with success modal
    @And("I click the Add Stock button")
    public void clickAddStockButton() {
        this.warehouseStock.addStockBtn();
        String expectedUrl = "Added stock successfully!";
        String actualUrl = this.warehouseStock.getAlertMsg();
        Assert.assertEquals(actualUrl, expectedUrl);
    }
    
    // sim user click add button this is for invalid fields we will check modal later
    @And("I click on the Add Stock button")
    public void clickOnAddStockButton() {
        this.warehouseStock.addStockBtn();
    }

    // user should see the newly update warehouse check against all warehouses
    @Then("I should see the last row with {string}, {string}, and {string}")
    public void shouldSeeNewWarehouseStock(String candy, String location, String quantity) {
        System.out.println(this.warehouseStock.checkNewWarehouseStock(candy, location, quantity));
    }

    // user should not see the warehouse this is for invalid inputs from earlier
    @Then("the warehouse stock should not be added with {string}, {string}, and {string}")
    public void shouldNotSeeNewWarehouseStock(String candy, String location, String quantity) {
        System.out.println(this.warehouseStock.checkNewWarehouseStock(candy, location, quantity));
    }

    // user should see an error modal when invalid inputs or dropdowns havent been selected
    @And("should see an alert message {string}") 
    public void iShouldSeeAlertMessage(String msg) {
        String expectedUrl = msg;
        String actualUrl = this.warehouseStock.getAlertMsg();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    //=============================== UPDATE ====================================//
    // sim user click on edit icon check that edit cancel button populated
    @When("I click the edit icon")
    public void clickEditIcon() {
        this.warehouseStock.clickEditIcon();
        Assert.assertTrue(this.warehouseStock.isCancelBtn());
    }

    // user selectes and inputs valid data for fields
    @Then("I select a new {string}, {string} and input {string} fields")
    public void selectNewAndInputFields(String candy, String location, String quantity) {
        this.warehouseStock.selectCandy(candy);
        this.warehouseStock.selectLocation(location);
        this.warehouseStock.clearQuantityField();
        this.warehouseStock.inputQuantity(quantity);
    }

    // user doesnt select or inputs invalid datat for fields
    @And("I don't select a new {string}, {string} and input {string} fields")
    public void doNotSelectWarehouseStockFields(String candy, String location, String quantity) {
        this.warehouseStock.selectCandy(candy);
        this.warehouseStock.selectLocation(location);
        this.warehouseStock.clearQuantityField();
        this.warehouseStock.inputQuantity(quantity);
    }

    // sim user click on update button check with success modal
    @And("I click the Update Stock button")
    public void clickUpdateStockButton() {
        this.warehouseStock.clickUpdateStockButton();
        String expectedUrl = "Updated stock successfully!";
        String actualUrl = this.warehouseStock.getAlertMsg();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    // sim user click on upate button will check with error modal later on
    @And("I click on the Update Stock button")
    public void clickOnUpdateStockButton() {
        this.warehouseStock.clickUpdateStockButton();
    }

    // user should not see warehouse in list check against all other warehouses
    @Then("the warehouse stock should not be updated with {string}, {string}, and {string}")
    public void shouldNotBeUpdatedWith(String candy, String location, String quantity) {
        Assert.assertFalse(this.warehouseStock.checkNewWarehouseStock(candy, location, quantity));
    }

    //=============================== READ ====================================//
    // user starts on the home page
    @Given("I am starting on the home page")
    public void currentlyOnTheHomePage() {
        this.warehouseStock.getHomePageUrl();
        String expectedUrl = "http://localhost:5173/";
        String actualUrl = this.warehouseStock.checkCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    // sim user click on warehouse stock menu in nav bar
    @When("I click on the Warehouse Stock option")
    public void clickOnWarehouseStockOption() {
        this.warehouseStock.clickOnWarehouseStockOption();
        String expectedUrl = "http://localhost:5173/stocks";
        String actualUrl = this.warehouseStock.checkCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    // user should be on warehouse stock page check with url
    @Then("I should be navigated to the Warehouse Stock page")
    public void shouldBeNavigatedToWarehouseStock() {
        System.out.println(this.warehouseStock.getWarehouseStockUrl());
        String expectedUrl = "http://localhost:5173/stocks";
        String actualUrl = this.warehouseStock.checkCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    // user should see the warehouse stock table in the page
    @And("I should see a list of all warehouse stocks available")
    public void shouldSeeListOfAllWarehouseStocks() {
        String expectedString = "Warehouse Candy Name Quantity Actions";
        String actualString = this.warehouseStock.getWarehouseTableContents();
        Assert.assertEquals(actualString, expectedString);
    }

    //=============================== DELETE ====================================//
    // sim user click for delete icon check with success modal
    @When("I click on the delete icon for the Warehouse Stock I want to delete")
    public void clickOnDeleteIconForWarehouseStock() {
        this.warehouseStock.clickDeleteIcon();
        String expectedUrl = "Stock deleted successfully!";
        String actualUrl = this.warehouseStock.getAlertMsg();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @Then("I click the confirm delete button on the warehouse stock page")
    public void iClickTheConfirmDeleteButtonOnTheWarehouseStockPage(){
        this.warehouseStock.clickConfirmDeleteBtn();
    }

    // user should not see the warehouse stock item any more and check against all items in warehouse stock list
    @Then("the warehouse stock should not be visible in the table")
    public void warehouseStockShouldNotBeVisible() {
        Assert.assertFalse(this.warehouseStock.confirmDeletion());
    }
}