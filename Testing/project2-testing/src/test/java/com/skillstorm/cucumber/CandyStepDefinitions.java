package com.skillstorm.cucumber;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.skillstorm.Selenium.CandyList;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

public class CandyStepDefinitions {
    // Instance of CandyList to interact with the web elements
    private CandyList candyList;
    
    // Hook that runs before each scenario tagged with @Candy
    @Before("@Candy")
    public void before(){
        // Setting up ChromeOptions to run Chrome in headless mode (without GUI)
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless","--no-sandbox");
        WebDriver driver = new ChromeDriver(options);

        // Initializing CandyList with the WebDriver instance
        this.candyList = new CandyList(driver);
    }
    
    // Hook that runs after each scenario tagged with @Candy
    @After("@Candy")
    public void after() {
        // Quitting the WebDriver instance to close the browser
        candyList.quit();
    }

    // Step definition for the Given statement to navigate to the Candy Inventory page
    @Given("I am on the Candy Inventory page")
    public void iAmOnTheCandyInventoryPage(){
        this.candyList.getCandyPageUrl();
    }

    // Step definition for the When statement to input candy information
    @When("I input the candy {string}, {string}, {string}, {string}, and, {string}")
    public void iInputTheCandyInformation(String name, String type, String flavor, String price, String weight){
        // Adding the candy information using the CandyList methods
        this.candyList.addCandyName(name);
        this.candyList.addCandyType(type);
        this.candyList.addCandyFlavor(flavor);
        this.candyList.addPrice(price);
        this.candyList.addWeight(weight);
    }

    // Step definition for the And statement to click the ADD CANDY button
    @And("I click the ADD CANDY button")
    public void iClickTheAddCandyButton(){
        this.candyList.clickCandyBtn();
    }

    // Step definition for the Then statement to verify the newly created candy appears on the list
    @Then("I should see the newly created candy on the list")
    public void iShouldSeeTheNewlyCreatedCandyOnTheList(){
        this.candyList.confirmCreation();
    }

    // Step definition for the Given statement to navigate to the Home page
    @Given("I am on the Home page")
    public void iAmOnTheHomePage(){
        this.candyList.getHomeUrl();
    }

    // Step definition for the When statement to navigate to the Candy Inventory page from Home
    @When("I navigate to Candy Inventory")
    public void iNavigateToTheCandyInventory(){
        this.candyList.clickCandyInventory();
    }

    // Step definition for the Then statement to verify all candies are displayed
    @Then("list of all candies should be displayed")
    public void theListOfAllCandiesShouldBeDisplayed(){
        this.candyList.displayCandyTable();
    }

    // Step definition for the When statement to click the delete icon for a candy
    @When("I click the delete icon")
    public void iClickTheDeleteIcon(){
        this.candyList.clickDeleteIcon();
    }

    // Step definition for the Then statement to verify the candy is deleted from the list
    @Then("the candy should be deleted from the list and no longer visible in the table")
    public void theCandyShouldBeDeletedFromTheListAndNoLongerVisibleInTheTable(){
        this.candyList.confirmDeletion();
    }

    // Step definition for the When statement to click the edit icon for a candy
    @When("I click the edit icon for the candy I want to edit")
    public void iClickTheEditIconForTheCandyIWantToEdit(){
        this.candyList.clickEditButton();
    }

    // Step definition for the And statement to change candy information
    @And("I change the {string}, {string}, {string}, {string}, and\\/or {string}")
    public void iChangeCandyInfoWithValidInfo(String name, String type, String flavor, String price, String weight){
        // Updating the candy information
        this.candyList.setNameField(name);
        this.candyList.setTypeField(type);
        this.candyList.setFlavorField(flavor);
        this.candyList.setPriceField(price);
        this.candyList.setWeightField(weight);
    }

    // Step definition for the And statement to click the UPDATE CANDY button
    @And("I click the UPDATE CANDY button")
    public void iClickTheUpdateCandyButton(){
        this.candyList.clickCandyBtn();
    }

    // Step definition for the Then statement to verify the updated candy appears in the list
    @Then("I should see the updated candy in the candy table")
    public void iShouldSeeTheUpdatedCandyInTheCandyTable(){
        this.candyList.confirmUpdation();
    }

    // Step definition for the Then statement to verify an error message is displayed for invalid input
    @Then("I should see an error message indicating invalid input")
    public void iShouldSeeAnErrorMessageIndicatingInvalidInput(){
        candyList.errorMessageDisplayed();
    }
}