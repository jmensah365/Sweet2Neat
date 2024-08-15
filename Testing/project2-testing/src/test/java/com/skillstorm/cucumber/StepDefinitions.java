package com.skillstorm.cucumber;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.skillstorm.selenium.WarehouseList;

public class StepDefinitions {

    private WarehouseList warehouseList;


    @Before("@Warehouse")
    public void before(){
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);

        this.warehouseList = new WarehouseList(driver);
    }

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
    }


}
