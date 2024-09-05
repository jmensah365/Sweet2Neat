package com.skillstorm.cucumber;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.cucumber.java.lu.an;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.skillstorm.Selenium.WarehouseList;

public class WarehouseStepDefinitions {

    // private instance of WarehouseList to interact with the web elements related to warehouses
    private WarehouseList warehouseList;

    // Hook that runs before each scenario tagged with @Warehouse
    @Before("@Warehouse")
    public void before(){
        // Setting up ChromeOptions to run Chrome in headless mode (without GUI)
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless","--no-sandbox", "--disable-gpu", "--window-size=1920,1080", "--disable-dev-shm-usage");
        WebDriver driver = new ChromeDriver(options);

        // Initializing WarehouseList with the WebDriver instance
        this.warehouseList = new WarehouseList(driver);
    }

    // Hook that runs after each scenario tagged with @Warehouse, to close the browser
    @After("@Warehouse")
    public void tearDown() {
        // Quitting the WebDriver instance to close the browser
        this.warehouseList.close();
    }

    // Step definition for the Given statement to navigate to the Warehouse List page
    @Given("I am on the Warehouse List page")
    public void iAmOnTheWarehouseListPage() {
        this.warehouseList.getUrl();
    }

    // Step definition for the When statement to click the edit icon for a warehouse
    @When("I click on the edit icon for the Warehouse I want to edit")
    public void iClickOnTheEditIconForTheWarehouseIWantToEdit() {
        this.warehouseList.clickEditButton();
    }

    // Step definition for the When statement to click the delete icon for a warehouse
    @When("I click on the delete icon for the Warehouse I want to delete")
    public void iClickOnTheDeleteIconForTheWarehouseIWantToDelete(){
        this.warehouseList.clickDeleteIcon();
    }

    @And("I change the {string} and\\/or {string}")
    public void iChangeWithValidCredentials(String location, String capacity) {
        // Updating the warehouse details
        this.warehouseList.setLocation(location);
        this.warehouseList.setCapacity(capacity);
    }

    // Step definition for the And statement to click the UPDATE WAREHOUSE button
    @And("I click on the UPDATE WAREHOUSE button")
    public void iClickOnTheUpdateWarehouseButton(){
        warehouseList.clickUpdateWarehouse();
    }

    // Step definition for the Then statement to verify the updated warehouse details in the list
    @Then("I should see the updated details for the Warehouse I edited in the list of Warehouses")
    public void iShouldSeeTheUpdatedDetailsForTheWarehouseIEditedInTheListOfWarehouses(){
        this.warehouseList.confirmWarehouseUpdation();
    }

    @And("the warehouse should not be visible in the table")
    public void theWarehouseShouldNotBeVisibleInTheTable(){
        this.warehouseList.confirmDeletion();
    }

     //======================= CREATE =========================
    @When("I fill in the {string} and {string} fields")
    public void fillInTheFields(String location, String capacity) {
        // Setting the location and capacity for the new warehouse
        this.warehouseList.setLocation(location);
        this.warehouseList.setCapacity(capacity);
    }

    // Step definition for the And statement to click the ADD WAREHOUSE button
    @And("I click the ADD WAREHOUSE button")
    public void clickAddWarehouseButton() {
        this.warehouseList.clickAddWarehouseButton();
    }

    @Then("I should see an alert message {string}")
    public void seeAlertErrorMessage(String errorMsg) {
        this.warehouseList.alertMsgText();
    }

    @Then("I should see the newly created Warehouse in the list of Warehouses")
    public void iShouldSeeNewlyCreatedWarehouse() {
        this.warehouseList.getWarehouseLocation();
    }

    @Then("I should see an error messsage on the screen")
    public void iShouldSeeAnErrorMessageOnTheScreen(){
        warehouseList.confirmErrorMessage();
    }

    //======================= READ =========================
    @Given("I am on the home page")
    public void givenIAmOnTheHomePage() {
        this.warehouseList.getHomeURL();
    }

    // Step definition for the When statement to click the Warehouse List option
    @When("I click on the Warehouse List option")
    public void clickOnWarehouseListOption() {
        this.warehouseList.clickWarehouseListOption();
    }

    // Step definition for the Then statement to verify navigation to the Warehouse List page
    @Then("I should be navigated to the Warehouse List page")
    public void iAmNavigatedToWarehouseListPage() {
        this.warehouseList.getUrl();
    }

    // Step definition for the And statement to verify the list of all warehouses is displayed
    @And("I should see a list of all warehouses available")
    public void iSeeListOfWarehouses() {
        this.warehouseList.getWarehouseTableContents();
    }
}