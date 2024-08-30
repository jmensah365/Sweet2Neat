package com.skillstorm.cucumber;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.skillstorm.Selenium.CandyList;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

public class CandyStepDefinitions {
    private CandyList candyList;
    
    @Before("@Candy")
    public void before(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless","--no-sandbox","--disable-gpu");
        WebDriver driver = new ChromeDriver(options);

        this.candyList = new CandyList(driver);
    }
    
    @After("@Candy")
    public void after() {
        candyList.quit();
    }

    @Given("I am on the Candy Inventory page")
    public void iAmOnTheCandyInventoryPage(){
        this.candyList.getCandyPageUrl();
    }

    @When("I input the candy {string}, {string}, {string}, {string}, and, {string}")
    public void iInputTheCandyInformation(String name, String type, String flavor, String price, String weight){
        this.candyList.addCandyName(name);
        this.candyList.addCandyType(type);
        this.candyList.addCandyFlavor(flavor);
        this.candyList.addPrice(price);
        this.candyList.addWeight(weight);
    }

    @And("I click the ADD CANDY button")
    public void iClickTheAddCandyButton(){
        this.candyList.clickCandyBtn();
    }

    @Then("I should see the newly created candy on the list")
    public void iShouldSeeTheNewlyCreatedCandyOnTheList(){
        this.candyList.confirmCreation();
    }

    @Given("I am on the Home page")
    public void iAmOnTheHomePage(){
        this.candyList.getHomeUrl();
    }

    @When("I navigate to Candy Inventory")
    public void iNavigateToTheCandyInventory(){
        this.candyList.clickCandyInventory();
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