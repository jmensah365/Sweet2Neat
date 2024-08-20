package com.skillstorm.Navigation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

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
}
