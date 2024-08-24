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
        String expectedUrl = "http://localhost:5173/";
        String actualUrl = navigationLinks.checkCurrentPage();
        Assert.assertEquals(expectedUrl, actualUrl);
    }
    
    //================= NAV TO WAREHOUSE LIST PAGE ==================//
    @Given("I am on the home page")
    public void onTheHomePage() {
        navigationLinks.getHomePage();
    }

    @When("I click on Warehouse List menu option")
    public void clickOnWarehousesMenu() {
        navigationLinks.clickWarehouseMenu();
    }

    @Then("I should see the Warehouse List page")
    public void shouldSeeWarehouseListPage() {
        System.out.println(navigationLinks.getWarehouseListHeader());
    }
    
    //================= NAV TO CANDY INVENTORY PAGE ==================//
    @When("I click on the Candy Inventory menu option")
    public void clickOnCandyInventoryOption() {
        navigationLinks.clickOnCandyInventoryMenu();
    }

    @Then("I should see the Candy Inventory page")
    public void shouldSeeCandyInventoryPage() {
        System.out.println(navigationLinks.getCandyInventoryHeader());
    }

    //================= NAV TO CANDY CATEGORIES PAGE ==================//

    @When("I click on the Candy Categories menu option")
    public void clickOnCandyCategoriesOption() {
        navigationLinks.clickOnCandyCategoriesMenu();
    }

    @Then("I should see the Candy Categories page")
    public void shouldSeeCandyCategoriesPage() {
        System.out.println(navigationLinks.getCandyCategoriesHeader());
    }

    //================= NAV TO LIST OF ORDERS PAGE ==================//

    @When("I click on the List of Orders menu option")
    public void clickOnListOfOrdersOption() {
        navigationLinks.clickOnListOfOrdersMenu();
    }

    @Then("I should see the List of Orders page")
    public void shouldSeeListOfOrders() {
        System.out.println(navigationLinks.getListOfOrdersHeader());
    }

    //================= NAV TO ORDER INFO PAGE ==================//

    @When("I click on the Order Info menu option")
    public void clickOnOrderInfoOption() {
        navigationLinks.clickOnOrderInfoMenu();
    }

    @Then("I should see the Order Info page")
    public void shouldSeeOrderInfo() {
        System.out.println(navigationLinks.getOrderInfoHeader());
    }

    //================= NAV TO ABOUT PAGE ==================//

    @When("I click on the About menu option")
    public void clickOnAboutOption() {
        navigationLinks.clickOnAboutMenu();
    }

    @Then("I should see the About page")
    public void shouldSeeAboutPage() {
        System.out.println(navigationLinks.getAboutInfo());
    }

    //================= NAV TO WAREHOUSE STOCK PAGE ==================//

    @When("I click on the Warehouse Stock menu option")
    public void clickOnWarehouseStockOption() {
        navigationLinks.clickOnWarehouseStockMenu();
    }

    @Then("I should see the Warehouse Stock page")
    public void shouldSeeWarehouseStockHeader() {
        System.out.println(navigationLinks.getWarehouseStockHeader());
    }

    //================= NAV USING ADD A WAREHOUSE BUTTON ==================//

    @When("I click on the Add a Warehouse menu button")
    public void clickOnAddAWarehouseMenuButton() {
        navigationLinks.clickOnAddAWarehouseButton();
    }
}
