package com.skillstorm.cucumber;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import com.skillstorm.Selenium.CandyList;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

public class CandyStepDefinitions {
    // Instance of CandyList to interact with the web elements
    private CandyList candyList;
    private String [] candyContents;

    @Before("@Candy")
    public void before(){
        // Setting up ChromeOptions to run Chrome in headless mode (without GUI)
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless","--no-sandbox", "--disable-gpu", "--window-size=1920,1080", "--disable-dev-shm-usage");
        options.addArguments("window-size=1920,1080");
        WebDriver driver = new ChromeDriver(options);

        // Initializing CandyList with the WebDriver instance
        this.candyList = new CandyList(driver);
        candyContents = new String[5];
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
        String expectedUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/candy";
        String actualUrl = this.candyList.checkCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    // Step definition for the When statement to input candy information
    @When("I input the candy {string}, {string}, {string}, {string}, and, {string}")
    public void iInputTheCandyInformation(String name, String type, String flavor, String price, String weight){
        candyContents[0] = this.candyList.addCandyName(name);
        candyContents[1] = this.candyList.addCandyType(type);
        candyContents[2] = this.candyList.addCandyFlavor(flavor);
        String priceString = formatString(this.candyList.addPrice(price));
        candyContents[3] = priceString;
        String weightString = formatString(this.candyList.addWeight(weight));
        candyContents[4] = weightString;
    }

    // Step definition for the And statement to click the ADD CANDY button
    @And("I click the ADD CANDY button")
    public void iClickTheAddCandyButton(){
        this.candyList.clickCandyBtn();
        String expectedUrl = "Successfully added candy!";
        String actualUrl = this.candyList.getAlertMsg();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @And("I click on the ADD CANDY button")
    public void iClickOnTheAddCandyButton(){
        this.candyList.clickCandyBtn();
    }

    // Step definition for the Then statement to verify the newly created candy appears on the list
    @Then("I should see the newly created candy on the list")
    public void iShouldSeeTheNewlyCreatedCandyOnTheList(){
        String actualString = this.candyList.confirmCreation();
        for(String s : candyContents) {
            Assert.assertTrue(actualString.contains(s));
        }
    }

    // Step definition for the Given statement to navigate to the Home page
    @Given("I am on the Home page")
    public void iAmOnTheHomePage(){
        this.candyList.getHomeUrl();
        String expectedUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/";
        String actualUrl = this.candyList.checkCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    // Step definition for the When statement to navigate to the Candy Inventory page from Home
    @When("I navigate to Candy Inventory")
    public void iNavigateToTheCandyInventory(){
        this.candyList.clickCandyInventory();
        String expectedUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/candy";
        String actualUrl = this.candyList.checkCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    // Step definition for the Then statement to verify all candies are displayed
    @Then("list of all candies should be displayed")
    public void theListOfAllCandiesShouldBeDisplayed(){
        String expectedString = "Candy ID Candy Name Candy Type Flavor Price ($) Weight (oz.)";
        String actualString = this.candyList.displayCandyTable();
        Assert.assertEquals(actualString, expectedString);
    }

    // Step definition for the When statement to click the delete icon for a candy
    @When("I click the delete icon")
    public void iClickTheDeleteIcon(){
        this.candyList.clickDeleteIcon();
        String expectedUrl = "Candy was deleted successfully!";
        String actualUrl = this.candyList.getAlertMsg();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    // Step definition for the Then statement to verify the candy is deleted from the list
    @Then("the candy should be deleted from the list and no longer visible in the table")
    public void theCandyShouldBeDeletedFromTheListAndNoLongerVisibleInTheTable(){
        Assert.assertFalse(this.candyList.confirmDeletion());
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
        candyContents[0] = this.candyList.setNameField(name);
        candyContents[1] = this.candyList.setTypeField(type);
        candyContents[2] = this.candyList.setFlavorField(flavor);
        String priceString = formatString(this.candyList.setPriceField(price));
        candyContents[3] = priceString;
        String weightString = formatString(this.candyList.setWeightField(weight));
        candyContents[4] = weightString;
    }

    // Step definition for the And statement to click the UPDATE CANDY button
    @And("I click the UPDATE CANDY button")
    public void iClickTheUpdateCandyButton(){
        this.candyList.clickCandyBtn();
        String expectedUrl = "Successfully updated candy!";
        String actualUrl = this.candyList.getAlertMsg();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @And("I click on the UPDATE CANDY button")
    public void iClickOnTheUpdateCandyButton(){
        this.candyList.clickCandyBtn();
    }

    // Step definition for the Then statement to verify the updated candy appears in the list
    @Then("I should see the updated candy in the candy table")
    public void iShouldSeeTheUpdatedCandyInTheCandyTable(){
        String expectedString = this.candyList.confirmUpdation();
        for(String s : candyContents) {
            Assert.assertTrue(expectedString.contains(s));
        }
    }
    
    // after user clicks button after invalid input error modal should appear
    @Then("I should see an error message {string}")
    public void shouldSeeErrorMsg(String errormsg) {
        String expectedUrl = errormsg;
        String actualUrl = this.candyList.getAlertMsg();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    // helper function proper formatting for price and weight
    public String formatString(String input) {
        if(input.isEmpty()) {
            return "";
        }
        if(input.matches("\\d+(\\.\\d+)?")) {
            float num = Float.parseFloat(input);
            return String.format("%.2f", num);
        }
        return input;
    }
}