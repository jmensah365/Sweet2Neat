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

    // set up
    @Before("@Navigation")
    public void before() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless","--no-sandbox", "--disable-gpu", "--window-size=1920,1080", "--disable-dev-shm-usage");
        WebDriver driver = new ChromeDriver(options);
        this.navigationLinks = new NavigationLinks(driver);
    }

    // teardown
    @After("@Navigation")
    public void after() {
        navigationLinks.close();
    }

    //================= NAV TO HOME PAGE ==================//
    // can start any page we decided to go with order page
    @Given("I am on any page")
    public void iAmOnAnyPage() {
        navigationLinks.getOrderPage();
    }

    // simulate click on logo
    @When("I click on the logo")
    public void clickOnLogo() {
        navigationLinks.clickLogo();
    }

    // user should be on home page
    @Then("I should see the home page")
    public void shouldSeeHomePage() {
        String expectedUrl = "http://localhost:5173/";
        String actualUrl = navigationLinks.checkCurrentPage();
        Assert.assertEquals(expectedUrl, actualUrl);
    }
    
    //================= NAV TO WAREHOUSE LIST PAGE ==================//
    //check starting page is home
    @Given("I am currently on the home page")
    public void onTheHomePage() {
        this.navigationLinks.getHomePage();
        String expectedUrl = "http://localhost:5173/";
        String actualUrl = navigationLinks.checkCurrentPage();
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    // click on menu option in nav bar
    @When("I click on Warehouse List menu option")
    public void clickOnWarehousesMenu() {
        navigationLinks.clickWarehouseMenu();
        String expectedUrl = "http://localhost:5173/warehouses";
        String actualUrl = navigationLinks.checkCurrentPage();
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    // user should be on warehouse list page after menu option click
    @Then("I should see the Warehouse List page")
    public void shouldSeeWarehouseListPage() {
        String expectedHeader = "Warehouse List";
        String actualHeader = navigationLinks.getWarehouseListHeader();
        Assert.assertEquals(expectedHeader, actualHeader);
    }
    
    //================= NAV TO CANDY INVENTORY PAGE ==================//
    // simulate user click on candy inv menu in nav bar
    @When("I click on the Candy Inventory menu option")
    public void clickOnCandyInventoryOption() {
        navigationLinks.clickOnCandyInventoryMenu();
        String expectedUrl = "http://localhost:5173/candy";
        String actualUrl = navigationLinks.checkCurrentPage();
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    // user should now be on candy inv page and check page header
    @Then("I should see the Candy Inventory page")
    public void shouldSeeCandyInventoryPage() {
        String expectedHeader = "Candy Inventory 🍬";
        String actualHeader = navigationLinks.getCandyInventoryHeader();
        Assert.assertEquals(expectedHeader, actualHeader);
    }

    //================= NAV TO CANDY CATEGORIES PAGE ==================//

    // simulate user click on candy cat menu in nav bar
    @When("I click on the Candy Categories menu option")
    public void clickOnCandyCategoriesOption() {
        navigationLinks.clickOnCandyCategoriesMenu();
        String expectedUrl = "http://localhost:5173/candy%20types";
        String actualUrl = navigationLinks.checkCurrentPage();
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    // user should now be on candy cat page check with header
    @Then("I should see the Candy Categories page")
    public void shouldSeeCandyCategoriesPage() {
        String expectedHeader = "Types of Candy";
        String actualHeader = navigationLinks.getCandyCategoriesHeader();
        Assert.assertEquals(expectedHeader, actualHeader);
    }

    //================= NAV TO LIST OF ORDERS PAGE ==================//

    // simulate user click on order list menu in nav bar
    @When("I click on the List of Orders menu option")
    public void clickOnListOfOrdersOption() {
        navigationLinks.clickOnListOfOrdersMenu();
        String expectedUrl = "http://localhost:5173/orders";
        String actualUrl = navigationLinks.checkCurrentPage();
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    // should be on order list page check with header
    @Then("I should see the List of Orders page")
    public void shouldSeeListOfOrders() {
        String expectedHeader = "Order List";
        String actualHeader = navigationLinks.getListOfOrdersHeader();
        Assert.assertEquals(expectedHeader, actualHeader);
    }

    //================= NAV TO ORDER INFO PAGE ==================//

    // simulate user click on order info page
    @When("I click on the Order Info menu option")
    public void clickOnOrderInfoOption() {
        navigationLinks.clickOnOrderInfoMenu();
        String expectedUrl = "http://localhost:5173/order%20information";
        String actualUrl = navigationLinks.checkCurrentPage();
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    // user should be on order info page check with header
    @Then("I should see the Order Info page")
    public void shouldSeeOrderInfo() {
        String expectedHeader = "Order Information";
        String actualHeader = navigationLinks.getOrderInfoHeader();
        Assert.assertEquals(expectedHeader, actualHeader);
    }

    //================= NAV TO ABOUT PAGE ==================//
    // simulate user click on about menu in nav bar
    @When("I click on the About menu option")
    public void clickOnAboutOption() {
        navigationLinks.clickOnAboutMenu();
        String expectedUrl = "http://localhost:5173/about";
        String actualUrl = navigationLinks.checkCurrentPage();
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    // user should be on about page check with header
    @Then("I should see the About page")
    public void shouldSeeAboutPage() {
        String expectedHeader = "🍬 Welcome to Candy Inventory Management";
        String actualHeader = navigationLinks.getAboutInfo();
        Assert.assertEquals(expectedHeader, actualHeader);
    }

    //================= NAV TO WAREHOUSE STOCK PAGE ==================//
    // simulate user click on stock menu in nav bar
    @When("I click on the Warehouse Stock menu option")
    public void clickOnWarehouseStockOption() {
        navigationLinks.clickOnWarehouseStockMenu();
        String expectedUrl = "http://localhost:5173/stocks";
        String actualUrl = navigationLinks.checkCurrentPage();
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    // user should be on stock page check with header
    @Then("I should see the Warehouse Stock page")
    public void shouldSeeWarehouseStockHeader() {
        String expectedHeader = "Stock Inventory";
        String actualHeader = navigationLinks.getWarehouseStockHeader();
        Assert.assertEquals(expectedHeader, actualHeader);
    }

    //================= NAV USING ADD A WAREHOUSE BUTTON ==================//

    // simulate user click on warehouse menu in nav bar
    @When("I click on the Add a Warehouse menu button")
    public void clickOnAddAWarehouseMenuButton() {
        navigationLinks.clickOnAddAWarehouseButton();
        String expectedUrl = "http://localhost:5173/warehouses";
        String actualUrl = navigationLinks.checkCurrentPage();
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    // check with header that user is on candy cat page
    @Given("I am on the Candy Categories page")
    public void iAmOnCandyCategoriesPage() {
        this.navigationLinks.candyCategoriesPage();
        String expectedHeader = "Types of Candy";
        String actualHeader = navigationLinks.getCandyCategoriesHeader();
        Assert.assertEquals(expectedHeader, actualHeader);
    }

    // in candy cat page multiple cads should pop up simulate user click on a candy type
    @When("I click on the desired {string}")
    public void clickOnTheDesiredCandyType(String candyType) {
        this.navigationLinks.selectCandyType(candyType);
    }
    
    // user should be directed to correct candy type page check with header
    @Then("I should see the desired {string} page")
    public void shouldSeeTheDesiredCandyType(String candyType) {
        String expectedHeader = candyType;
        String actualHeader = navigationLinks.checkCandyHeader(candyType);
        Assert.assertEquals(expectedHeader, actualHeader);
    }
}