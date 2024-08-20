package com.skillstorm.Navigation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;

public class NavigationSteps {

    private NavigationLinks navigationLinks;

    @Before("@Navigation")
    public void before() {
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);
        this.navigationLinks = new NavigationLinks(driver);
    }

    @After
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

    //================= NAV TO CANDY INVENTORY PAGE ==================//
}
