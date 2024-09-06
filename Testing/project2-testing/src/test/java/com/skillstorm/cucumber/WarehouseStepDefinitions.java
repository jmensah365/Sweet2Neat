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


    // This hook runs before any scenario tagged with @Warehouse, initializing the WebDriver
    @Before("@Warehouse")
    public void before(){
        ChromeOptions options = new ChromeOptions();
        // Headless Chrome setup to run tests without opening a browser window
        options.addArguments("--headless","--no-sandbox", "--disable-gpu", "--window-size=1920,1080", "--disable-dev-shm-usage");
        WebDriver driver = new ChromeDriver(options); // Initialize ChromeDriver
        this.warehouseList = new WarehouseList(driver); // Initialize the WarehouseList page object
        warehouse = new String[2]; // Array to store location and capacity values
    }

    // This hook runs after any scenario tagged with @Warehouse, closing the browser
    @After("@Warehouse")
    public void tearDown() {
        this.warehouseList.close(); // Closes the browser instance after the test
    }

    // Step definition for navigating to the Warehouse List page and verifying the title
    @Given("I am on the Warehouse List page")
    public void iAmOnTheWarehouseListPage() {
        this.warehouseList.getUrl(); // Navigate to Warehouse List URL
        String expectedHeader = "Warehouse List"; // Expected title of the page
        String actualHeader = warehouseList.checkPageTitle(); // Fetch the actual page title
        Assert.assertEquals(actualHeader, expectedHeader); // Assert the page title is as expected
    }

    // Step definition for clicking the edit icon of a specific warehouse
    @When("I click on the edit icon for the Warehouse I want to edit")
    public void iClickOnTheEditIconForTheWarehouseIWantToEdit() {
        this.warehouseList.clickEditButton(); // Click the edit button for the selected warehouse
        Assert.assertTrue(this.warehouseList.findEditCancelBtn()); // Verify the cancel button appears after editing
    }

    // Step definition for clicking the delete icon of a specific warehouse
    @When("I click on the delete icon for the Warehouse I want to delete")
    public void iClickOnTheDeleteIconForTheWarehouseIWantToDelete(){
        this.warehouseList.clickDeleteIcon(); // Click the delete button for the selected warehouse
    }

    // Step definition for changing warehouse location and/or capacity with valid inputs
    @And("I change the {string} and\\/or {string}")
    public void iChangeWithValidCredentials(String location, String capacity) {
        warehouse[0] = this.warehouseList.setLocation(location); // Update location field
        warehouse[1] = this.warehouseList.setCapacity(capacity); // Update capacity field
    }

    // Step definition for changing warehouse location and/or capacity with invalid inputs
    @And("I change the {string} and\\/or {string} to invalid fields")
    public void iChangeWithInvalidCredentials(String location, String capacity) {
        warehouse[0] = this.warehouseList.setLocation(location); // Update location with invalid input
        warehouse[1] = this.warehouseList.setCapacity(capacity); // Update capacity with invalid input
    }

    // Step definition for clicking the UPDATE WAREHOUSE button and verifying a success message
    @And("I click on the UPDATE WAREHOUSE button")
    public void iClickOnTheUpdateWarehouseButton(){
        warehouseList.clickUpdateWarehouse(); // Click the update warehouse button
        String expectedMsg = "Warehouse updated successfully!"; // Expected success message
        String actualMsg = this.warehouseList.alertMsgText(); // Get the actual alert message
        Assert.assertEquals(actualMsg, expectedMsg); // Assert that the alert message matches
    }
    
    // Step definition for clicking the UPDATE WAREHOUSE button
    @And("I click the UPDATE WAREHOUSE button")
    public void iClickTheUpdateWarehouseButton(){
        warehouseList.clickUpdateWarehouse(); // Click the update warehouse button
    }

    // Step definition for verifying the updated warehouse details in the warehouse list
    @And("I should see the updated details for the Warehouse I edited in the list of Warehouses")
    public void iShouldSeeTheUpdatedDetailsForTheWarehouseIEditedInTheListOfWarehouses(){
        String actualString = this.warehouseList.confirmWarehouseUpdation(); // Get the updated warehouse details
        for(String s : warehouse) { // Loop through updated location and capacity
            Assert.assertTrue(actualString.contains(s)); // Assert that each field is updated
        }
    }

    // Step definition for verifying that a warehouse has been deleted from the list
    @And("the warehouse should not be visible in the table")
    public void theWarehouseShouldNotBeVisibleInTheTable(){
        Assert.assertFalse(this.warehouseList.confirmDeletion()); // Assert that the warehouse is no longer in the table
    }

    //======================= CREATE =========================
    
    // Step definition for filling in the warehouse creation form
    @When("I fill in the {string} and {string} fields")
    public void fillInTheFields(String location, String capacity) {
        warehouse[0] = this.warehouseList.setLocation(location); // Set the location field
        warehouse[1] = this.warehouseList.setCapacity(capacity); // Set the capacity field
    }

    // Step definition for clicking the ADD WAREHOUSE button and verifying a success message
    @And("I click the ADD WAREHOUSE button")
    public void clickTheAddWarehouseButton() {
        this.warehouseList.clickAddWarehouseButton(); // Click the add warehouse button
        String expectedMsg = "Warehouse added successfully!"; // Expected success message
        String actualMsg = this.warehouseList.alertMsgText(); // Get the actual alert message
        Assert.assertEquals(actualMsg, expectedMsg); // Assert that the alert message matches
    }

    // Step definition for clicking the ADD WAREHOUSE button
    @And("I click ADD WAREHOUSE button")
    public void clickAddWarehouseButton() {
        this.warehouseList.clickAddWarehouseButton(); // Click the add warehouse button
    }

    // Step definition for verifying an alert message after a form submission
    @Then("I should see an alert message {string}")
    public void seeAlertErrorMessage(String msg) {
        String actualMsg = this.warehouseList.alertMsgText(); // Get the actual alert message
        Assert.assertEquals(actualMsg, msg); // Assert that the alert message matches the expected message
    }

    // Step definition for verifying the newly created warehouse is in the list
    @Then("I should see the newly created Warehouse in the list of Warehouses")
    public void iShouldSeeNewlyCreatedWarehouse() {
        String actualString = this.warehouseList.confirmWarehouseCreation(); // Get the warehouse details from the table
        for(String s : warehouse) { // Loop through location and capacity
            Assert.assertTrue(actualString.contains(s)); // Assert that the new warehouse appears in the list
        }
    }

    //======================= READ =========================
    
    // Step definition for navigating to the home page
    @Given("I am on the home page")
    public void givenIAmOnTheHomePage() {
        this.warehouseList.getHomeURL(); // Navigate to the home page
        String expectedUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/"; // Expected home URL
        String actualUrl = this.warehouseList.checkCurrentPageUrl(); // Get the current page URL
        Assert.assertEquals(actualUrl, expectedUrl); // Assert that the user is on the home page
    }

    // Step definition for clicking the Warehouse List option in the menu
    @When("I click on the Warehouse List option")
    public void clickOnWarehouseListOption() {
        this.warehouseList.clickWarehouseListOption(); // Click the warehouse list option in the menu
        String expectedUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/warehouses"; // Expected URL for the Warehouse List page
        String actualUrl = this.warehouseList.checkCurrentPageUrl(); // Get the current page URL
        Assert.assertEquals(actualUrl, expectedUrl); // Assert that the user is on the Warehouse List page
    }
    
    // Step definition for verifying navigation to the Warehouse List page
    @Then("I should be navigated to the Warehouse List page")
    public void iAmNavigatedToWarehouseListPage() {
        this.warehouseList.getUrl(); // Navigate to the Warehouse List page
        String expectedUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/warehouses"; // Expected URL for the page
        String actualUrl = this.warehouseList.checkCurrentPageUrl(); // Get the current page URL
        Assert.assertEquals(actualUrl, expectedUrl); // Assert that the user is on the correct page
    }

    // Step definition for verifying the list of warehouses is visible on the page
    @And("I should see a list of all warehouses available")
    public void iSeeListOfWarehouses() {
        String expectedString = "Warehouse Id Location Stock/Capacity Actions"; // Expected table header
        String actualUrl = this.warehouseList.getWarehouseTableContents(); // Get the table contents
        Assert.assertEquals(actualUrl, expectedString); // Assert that the table contents
    }
}
