package com.skillstorm.cucumber;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

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
        WebDriver driver = new ChromeDriver(options);

        this.warehouseStock = new WarehouseStock(driver);
    }

    @After("@WarehouseStock")
    public void after() {
        this.warehouseStock.close();
    }

    @Given("I am on the Warehouse Stock page")
    public void onWarehouseStockPage() {
        this.warehouseStock.getWarehoueStockUrl();
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
    }

    @Then("I should see the last row with {string}, {string}, and {string}")
    public void shouldSeeNewWarehouseStock(String candy, String location, String quantity) {
        System.out.println(this.warehouseStock.checkNewWarehouseStock(candy, location, quantity));
    }

    @Then("the warehouse stock should not be added with {string}, {string}, and {string}")
    public void shouldNotSeeNewWarehouseStock(String candy, String location, String quantity) {
        System.out.println(this.warehouseStock.checkNewWarehouseStock(candy, location, quantity));
    }
}
