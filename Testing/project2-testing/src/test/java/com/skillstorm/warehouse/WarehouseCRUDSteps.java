package com.skillstorm.warehouse;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WarehouseCRUDSteps {
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

    @When("I click on the delete icon for the Warehouse I want to delete")
    public void iClickOnTheDeleteIconForTheWarehouseIWantToDelete(){
        this.warehouseList.clickDeleteIcon();
    }

    @And("I change the {string} and\\/or {string} with valid information")
    public void iChangeWithValidCredentials(String location, String capacity) {
        this.warehouseList.setLocation(location);
        this.warehouseList.setCapacity(capacity);
    }

    @And("I click on the UPDATE WAREHOUSE button")
    public void iClickOnTheUpdateWarehouseButton(){
        warehouseList.clickUpdateWarehouse();
    }

    @Then("I should see the updated details for the Warehouse I edited in the list of Warehouses")
    public void iShouldSeeTheUpdatedDetailsForTheWarehouseIEditedInTheListOfWarehouses(){
        this.warehouseList.confirmWarehouseUpdation();
        this.driver.close();
    }

    @Then("the warehouse should be removed from the list")
    public void warehouseRemovedFromList() {

    }


    //======================= CREATE =========================
    @When("I fill in the {string}, {string}, and {string} fields with valid information")
    public void fillInTheFields(String location, String capacity, String currentStock) {
    this.warehouseList.setLocation(location);
    this.warehouseList.setCapacity(capacity);
    }

    @And("I click the ADD WAREHOUSE button")
    public void clickAddWarehouseButton() {
    this.warehouseList.clickAddWarehouseButton();
    }

    @Then("I should see the newly created Warehouse in the list of Warehouses")
    public void iShouldSeeNewlyCreatedWarehouse() {
        this.warehouseList.getWarehouseLocation();
        this.driver.close();
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
        this.warehouseList.getWarehouseTableContents();
        this.driver.close();
    }
}