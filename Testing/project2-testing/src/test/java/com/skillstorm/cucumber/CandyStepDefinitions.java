package com.skillstorm.cucumber;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.skillstorm.Selenium.CandyList;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;

public class CandyStepDefinitions {
    private CandyList candyList;
    
    @Before("candy")
    public void before(){
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);

        this.candyList = new CandyList(driver);
    }

    @Given("I am on the Candy Inventory page")
    public void iAmOnTheCandyInventoryPage(){}

    @When("I input the candy {string}, {string}, {string}, {string}, and, {string}")
    public void iInputTheCandyInformation(){}

    @And("I click the ADD CANDY button")
    public void iClickTheAddCandyButton(){}

    @Then("I should see the newly created candy on the list")
    public void iShouldSeeTheNewlyCreatedCandyOnTheList(){
    }


}
