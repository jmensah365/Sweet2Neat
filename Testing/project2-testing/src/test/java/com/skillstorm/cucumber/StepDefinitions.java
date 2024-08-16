package com.skillstorm.cucumber;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import java.time.Duration;

import org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.skillstorm.selenium.WarehouseList;

public class StepDefinitions {
    private WebDriver driver;
    private WarehouseList warehouseList;

    @Before("@Warehouse")
    public void before(){
        ChromeOptions options = new ChromeOptions();
        this.driver = new ChromeDriver(options);
        this.warehouseList = new WarehouseList(driver);
    }

    //======================= UPDATE =========================
    @Given("I am on the Warehouse List page")
    public void iAmOnTheWarehouseListPage() {
        this.warehouseList.getUrl();
    }

    @When("I click on the edit icon for the Warehouse I want to edit")
    public void iClickOnTheEditIconForTheWarehouseIWantToEdit() {
        this.warehouseList.clickEditButton();
    }

    @And("I change the {string}, {string}, and\\/or {string} with valid information")
    public void iChangeWithValidCredentials(String location, String capacity, String stock) {
        this.warehouseList.setLocation(location);
        this.warehouseList.setCapacity(capacity);
        this.warehouseList.setStock(stock);
    }

    @And("I click on the UPDATE WAREHOUSE button")
    public void iClickOnTheUpdateWarehouseButton(){
        warehouseList.clickUpdateWarehouse();
    }

    @Then("I should see the updated details for the Warehouse I edited in the list of Warehouses")
    public void iShouldSeeTheUpdatedDetailsForTheWarehouseIEditedInTheListOfWarehouses(){
        this.warehouseList.displayWarehouseTable();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        this.driver.quit();
    }

    //======================= CREATE =========================
    @When("I fill in the {string}, {string}, and {string} fields with valid information")
    public void fillInTheFields(String location, String capacity, String currentStock) {
    this.warehouseList.setLocation(location);
    this.warehouseList.setCapacity(capacity);
    this.warehouseList.setCurrentStock(currentStock);
    }

    @And("I click the ADD WAREHOUSE button")
    public void clickAddWarehouseButton() {
    this.warehouseList.clickAddWarehouseButton();
    }

    @Then("I should see the newly created Warehouse in the list of Warehouses")
    public void iShouldSeeNewlyCreatedWarehouse() {
        this.warehouseList.getWarehouseLocation();
    }

    //======================= READ =========================
    @Given("I am on the home page")
    public void iAmOnTheHomePage() {
        this.warehouseList.getHomeURL();
    }

    @When("I click on the Warehouse List option")
    public void clickOnWarehouseListOption() {
        this.warehouseList.clickWarehouseListOption();
    }
    
    @Then("I should be navigated to the Warehouse List page")
    public void iAmNavigatedToWarehouseListPage() {
        this.warehouseList.getUrl();
    }

    @And("I should see a list of all warehouses available")
    public void iSeeListOfWarehouses() {
    }
}
