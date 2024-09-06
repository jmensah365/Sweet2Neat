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

    // set up method
    @Before("@Warehouse")
    public void before(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless","--no-sandbox", "--disable-gpu", "--window-size=1920,1080", "--disable-dev-shm-usage");
        WebDriver driver = new ChromeDriver(options);
        this.warehouseList = new WarehouseList(driver);
        warehouse = new String[2];
    }

    // teardown method
    @After("@Warehouse") // added for closing all browsers down after testing
    public void tearDown() {
        this.warehouseList.close();
    }

    // user should start on warehouse list page, check with page header
    @Given("I am on the Warehouse List page")
    public void iAmOnTheWarehouseListPage() {
        this.warehouseList.getUrl();
        String expectedHeader = "Warehouse List";
        String actualHeader = warehouseList.checkPageTitle();
        Assert.assertEquals(actualHeader, expectedHeader);
    }

    // sim user click on edit icon and verify with edit cancel button popping up
    @When("I click on the edit icon for the Warehouse I want to edit")
    public void iClickOnTheEditIconForTheWarehouseIWantToEdit() {
        this.warehouseList.clickEditButton();
        Assert.assertTrue(this.warehouseList.findEditCancelBtn());
    }

    // sim user click on delete icon
    @When("I click on the delete icon for the Warehouse I want to delete")
    public void iClickOnTheDeleteIconForTheWarehouseIWantToDelete(){
        this.warehouseList.clickDeleteIcon();
    }

    // change the input fields and store them so we can verify against the newly created or updated fields
    @And("I change the {string} and\\/or {string}")
    public void iChangeWithValidCredentials(String location, String capacity) {
        warehouse[0] = this.warehouseList.setLocation(location);
        warehouse[1] = this.warehouseList.setCapacity(capacity);
    }

    // user changes fields to invalid fields which we should also save to verify these were the intended inputs
    @And("I change the {string} and\\/or {string} to invalid fields")
    public void iChangeWithInvalidCredentials(String location, String capacity) {
        warehouse[0] = this.warehouseList.setLocation(location);
        warehouse[1] = this.warehouseList.setCapacity(capacity);
    }

    // sim user click on update button and check with success modal for updating
    @And("I click on the UPDATE WAREHOUSE button")
    public void iClickOnTheUpdateWarehouseButton(){
        warehouseList.clickUpdateWarehouse();
        String expectedMsg = "Warehouse updated successfully!";
        String actualMsg = this.warehouseList.alertMsgText();
        Assert.assertEquals(actualMsg, expectedMsg);
    }
    
    // sim user click on update button for invalid input will check error messge later
    @And("I click the UPDATE WAREHOUSE button")
    public void iClickTheUpdateWarehouseButton(){
        warehouseList.clickUpdateWarehouse();
    }

    // user should see the edited details and verify it by checking previously saved details against new one
    @And("I should see the updated details for the Warehouse I edited in the list of Warehouses")
    public void iShouldSeeTheUpdatedDetailsForTheWarehouseIEditedInTheListOfWarehouses(){
        String actualString = this.warehouseList.confirmWarehouseUpdation();
        System.out.println("Actual " + actualString);
        for(String s : warehouse) {
            System.out.println("s " + s);
            Assert.assertTrue(actualString.contains(s));
        }
    }

    // verify that the user doesnt see the deleted item
    @And("the warehouse should not be visible in the table")
    public void theWarehouseShouldNotBeVisibleInTheTable(){
        Assert.assertFalse(this.warehouseList.confirmDeletion());
    }

     //======================= CREATE =========================
     // user fills in the fields with valid data, save for later for comparision
    @When("I fill in the {string} and {string} fields")
    public void fillInTheFields(String location, String capacity) {
        warehouse[0] = this.warehouseList.setLocation(location);
        warehouse[1] = this.warehouseList.setCapacity(capacity);
    }

    // sim user click on warehouse button and check with success modal
    @And("I click the ADD WAREHOUSE button")
    public void clickTheAddWarehouseButton() {
        this.warehouseList.clickAddWarehouseButton();
        String expectedMsg = "Warehouse added successfully!";
        String actualMsg = this.warehouseList.alertMsgText();
        Assert.assertEquals(actualMsg, expectedMsg);
    }

    // sim use click for add button with invalid inputs
    @And("I click ADD WAREHOUSE button")
    public void clickAddWarehouseButton() {
        this.warehouseList.clickAddWarehouseButton();
    }

    // when user inputs invalid data check that the proper error modals are being populated
    @Then("I should see an alert message {string}")
    public void seeAlertErrorMessage(String msg) {
        String expectedMsg = msg;
        String actualMsg = this.warehouseList.alertMsgText();
        Assert.assertEquals(actualMsg, expectedMsg);
    }

    // user should see the new warehouse and check it with previously stored input values and the new warehouse contents
    @Then("I should see the newly created Warehouse in the list of Warehouses")
    public void iShouldSeeNewlyCreatedWarehouse() {
        String actualString = this.warehouseList.confirmWarehouseCreation();
        for(String s : warehouse) {
            Assert.assertTrue(actualString.contains(s));
        }
    }

    //======================= READ =========================
    // user starts on home page check with url
    @Given("I am on the home page")
    public void givenIAmOnTheHomePage() {
        this.warehouseList.getHomeURL();
        String expectedUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/";
        String actualUrl = this.warehouseList.checkCurrentPageUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    // sim user click on warehouse list menu in nav bar
    @When("I click on the Warehouse List option")
    public void clickOnWarehouseListOption() {
        this.warehouseList.clickWarehouseListOption();
        String expectedUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/warehouses";
        String actualUrl = this.warehouseList.checkCurrentPageUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }
    
    // user should now be on warehouse list page check with url
    @Then("I should be navigated to the Warehouse List page")
    public void iAmNavigatedToWarehouseListPage() {
        this.warehouseList.getUrl();
        String expectedUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/warehouses";
        String actualUrl = this.warehouseList.checkCurrentPageUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    // use should see all the warehouses, here we simply check the warehouse table is present
    @And("I should see a list of all warehouses available")
    public void iSeeListOfWarehouses() {
        String expectedString = "Warehouse Id Location Stock/Capacity Actions";
        String actualUrl = this.warehouseList.getWarehouseTableContents();
        Assert.assertEquals(actualUrl, expectedString);
    }

}