package com.skillstorm.cucumber;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import com.skillstorm.Selenium.NavigationLinks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

public class NavigationSteps {

    private NavigationLinks navigationLinks;

    @Before("@Navigation")
    public void before() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless","--no-sandbox", "--disable-gpu", "--window-size=1920,1080", "--disable-dev-shm-usage");
        WebDriver driver = new ChromeDriver(options);
        this.navigationLinks = new NavigationLinks(driver);
    }

    @After("@Navigation")
    public void after() {
        navigationLinks.close();
    }

    //================= NAV TO HOME PAGE ==================//
    @Given("I am on any page")
    public void iAmOnAnyPage() {
        navigationLinks.getOrderPage();
    }

    @When("I click on the logo")
    public void clickOnLogo() {
        navigationLinks.clickLogo();
    }

    @Then("I should see the home page")
    public void shouldSeeHomePage() {
        String expectedUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/";
        String actualUrl = navigationLinks.checkCurrentPage();
        Assert.assertEquals(expectedUrl, actualUrl);
    }
    
    //================= NAV TO WAREHOUSE LIST PAGE ==================//
    @Given("I am currently on the home page")
    public void onTheHomePage() {
        this.navigationLinks.getHomePage();
        String expectedUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/";
        String actualUrl = navigationLinks.checkCurrentPage();
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @When("I click on Warehouse List menu option")
    public void clickOnWarehousesMenu() {
        navigationLinks.clickWarehouseMenu();
        String expectedUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/warehouses";
        String actualUrl = navigationLinks.checkCurrentPage();
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @Then("I should see the Warehouse List page")
    public void shouldSeeWarehouseListPage() {
        String expectedHeader = "Warehouse List";
        String actualHeader = navigationLinks.getWarehouseListHeader();
        Assert.assertEquals(expectedHeader, actualHeader);
    }
    
    //================= NAV TO CANDY INVENTORY PAGE ==================//
    @When("I click on the Candy Inventory menu option")
    public void clickOnCandyInventoryOption() {
        navigationLinks.clickOnCandyInventoryMenu();
        String expectedUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/candy";
        String actualUrl = navigationLinks.checkCurrentPage();
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @Then("I should see the Candy Inventory page")
    public void shouldSeeCandyInventoryPage() {
        String expectedHeader = "Candy Inventory";
        String actualHeader = navigationLinks.getCandyInventoryHeader();
        Assert.assertEquals(expectedHeader, actualHeader);
    }

    //================= NAV TO CANDY CATEGORIES PAGE ==================//

    @When("I click on the Candy Categories menu option")
    public void clickOnCandyCategoriesOption() {
        navigationLinks.clickOnCandyCategoriesMenu();
        String expectedUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/candyTypes";
        String actualUrl = navigationLinks.checkCurrentPage();
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @Then("I should see the Candy Categories page")
    public void shouldSeeCandyCategoriesPage() {
        String expectedHeader = "Types of candy";
        String actualHeader = navigationLinks.getCandyCategoriesHeader();
        Assert.assertEquals(expectedHeader, actualHeader);
    }

    //================= NAV TO LIST OF ORDERS PAGE ==================//

    @When("I click on the List of Orders menu option")
    public void clickOnListOfOrdersOption() {
        navigationLinks.clickOnListOfOrdersMenu();
        String expectedUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/orders";
        String actualUrl = navigationLinks.checkCurrentPage();
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @Then("I should see the List of Orders page")
    public void shouldSeeListOfOrders() {
        String expectedHeader = "Order List";
        String actualHeader = navigationLinks.getListOfOrdersHeader();
        Assert.assertEquals(expectedHeader, actualHeader);
    }

    //================= NAV TO ORDER INFO PAGE ==================//

    @When("I click on the Order Info menu option")
    public void clickOnOrderInfoOption() {
        navigationLinks.clickOnOrderInfoMenu();
        String expectedUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/orderInfo";
        String actualUrl = navigationLinks.checkCurrentPage();
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @Then("I should see the Order Info page")
    public void shouldSeeOrderInfo() {
        String expectedHeader = "Order Information";
        String actualHeader = navigationLinks.getOrderInfoHeader();
        Assert.assertEquals(expectedHeader, actualHeader);
    }

    //================= NAV TO ABOUT PAGE ==================//

    @When("I click on the About menu option")
    public void clickOnAboutOption() {
        navigationLinks.clickOnAboutMenu();
        String expectedUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/about";
        String actualUrl = navigationLinks.checkCurrentPage();
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @Then("I should see the About page")
    public void shouldSeeAboutPage() {
        String expectedHeader = "This is your one stop shop for Candy Inventory Management. You will be able add different candy products you wish to store and also delete any you do not want. You can also see how many warehouses you currenly have, how much capacity they each have, how much stock they hold, and their location. In addition you can keep track of people who have ordered from you and associated order information.";
        String actualHeader = navigationLinks.getAboutInfo();
        Assert.assertEquals(expectedHeader, actualHeader);
    }

    //================= NAV TO WAREHOUSE STOCK PAGE ==================//

    @When("I click on the Warehouse Stock menu option")
    public void clickOnWarehouseStockOption() {
        navigationLinks.clickOnWarehouseStockMenu();
        String expectedUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/stocks";
        String actualUrl = navigationLinks.checkCurrentPage();
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @Then("I should see the Warehouse Stock page")
    public void shouldSeeWarehouseStockHeader() {
        String expectedHeader = "Stock Inventory";
        String actualHeader = navigationLinks.getWarehouseStockHeader();
        Assert.assertEquals(expectedHeader, actualHeader);
    }

    //================= NAV USING ADD A WAREHOUSE BUTTON ==================//

    @When("I click on the Add a Warehouse menu button")
    public void clickOnAddAWarehouseMenuButton() {
        navigationLinks.clickOnAddAWarehouseButton();
        String expectedUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/warehouses";
        String actualUrl = navigationLinks.checkCurrentPage();
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @Given("I am on the Candy Categories page")
    public void iAmOnCandyCategoriesPage() {
        this.navigationLinks.candyCategoriesPage();
        String expectedHeader = "Types of candy";
        String actualHeader = navigationLinks.getCandyCategoriesHeader();
        Assert.assertEquals(expectedHeader, actualHeader);
    }

    @When("I click on the desired {string}")
    public void clickOnTheDesiredCandyType(String candyType) {
        this.navigationLinks.selectCandyType(candyType);
    }
    
    @Then("I should see the desired {string} page")
    public void shouldSeeTheDesiredCandyType(String candyType) {
        String expectedHeader = candyType;
        String actualHeader = navigationLinks.checkCandyHeader(candyType);
        Assert.assertEquals(expectedHeader, actualHeader);
    }
}