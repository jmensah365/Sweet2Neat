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
    private CandyList candyList;
    private String [] candyContents;

    @Before("@Candy")
    public void before(){
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless","--no-sandbox", "--disable-gpu", "--window-size=1920,1080", "--disable-dev-shm-usage");
        WebDriver driver = new ChromeDriver(options);
        this.candyList = new CandyList(driver);
        candyContents = new String[5];
    }
    
    @After("@Candy")
    public void after() {
        candyList.quit();
    }

    @Given("I am on the Candy Inventory page")
    public void iAmOnTheCandyInventoryPage(){
        this.candyList.getCandyPageUrl();
        String expectedUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/candy";
        String actualUrl = this.candyList.checkCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @When("I input the candy {string}, {string}, {string}, {string}, and, {string}")
    public void iInputTheCandyInformation(String name, String type, String flavor, String price, String weight){
        candyContents[0] = this.candyList.addCandyName(name);
        candyContents[1] = this.candyList.addCandyType(type);
        candyContents[2] = this.candyList.addCandyFlavor(flavor);
        candyContents[3] = this.candyList.addPrice(price);
        candyContents[4] = this.candyList.addWeight(weight);
    }

    @And("I click the ADD CANDY button")
    public void iClickTheAddCandyButton(){
        this.candyList.clickCandyBtn();
        String expectedUrl = "Successfully added candy!";
        String actualUrl = this.candyList.getAlertMsg();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @Then("I should see the newly created candy on the list")
    public void iShouldSeeTheNewlyCreatedCandyOnTheList(){
        String expectedString = this.candyList.confirmCreation();
        for(String s : candyContents) {
            Assert.assertTrue(expectedString.contains(s));
        }
    }

    @Given("I am on the Home page")
    public void iAmOnTheHomePage(){
        this.candyList.getHomeUrl();
        String expectedUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/";
        String actualUrl = this.candyList.checkCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @When("I navigate to Candy Inventory")
    public void iNavigateToTheCandyInventory(){
        this.candyList.clickCandyInventory();
        String expectedUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/candy";
        String actualUrl = this.candyList.checkCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @Then("list of all candies should be displayed")
    public void theListOfAllCandiesShouldBeDisplayed(){
        this.candyList.displayCandyTable();
    }

    @When("I click the delete icon")
    public void iClickTheDeleteIcon(){
        this.candyList.clickDeleteIcon();
    }

    @Then("the candy should be deleted from the list and no longer visible in the table")
    public void theCandyShouldBeDeletedFromTheListAndNoLongerVisibleInTheTable(){
        this.candyList.confirmDeletion();
    }

    @When("I click the edit icon for the candy I want to edit")
    public void iClickTheEditIconForTheCandyIWantToEdit(){
        this.candyList.clickEditButton();
    }

    @And("I change the {string}, {string}, {string}, {string}, and\\/or {string}")
    public void iChangeCandyInfoWithValidInfo(String name, String type, String flavor, String price, String weight){
        this.candyList.setNameField(name);
        this.candyList.setTypeField(type);
        this.candyList.setFlavorField(flavor);
        this.candyList.setPriceField(price);
        this.candyList.setWeightField(weight);
    }

    @And("I click the UPDATE CANDY button")
    public void iClickTheUpdateCandyButton(){
        this.candyList.clickCandyBtn();
    }

    @Then("I should see the updated candy in the candy table")
    public void iShouldSeeTheUpdatedCandyInTheCandyTable(){
        this.candyList.confirmUpdation();
    }

    @Then("I should see an error message indicating invalid input")
    public void iShouldSeeAnErrorMessageIndicatingInvalidInput(){
        candyList.errorMessageDisplayed();
    }


}