package com.skillstorm.cucumber;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import com.skillstorm.Selenium.OrderItems;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class OrderItemsSteps {

    private OrderItems orderItems;
    private String[] orderInfo;

    // setup
    @Before("@OrderItems")
    public void before() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--no-sandbox", "--disable-gpu", "--window-size=1920,1080", "--disable-dev-shm-usage");
        WebDriver driver = new ChromeDriver(options);
        this.orderItems = new OrderItems(driver);
        orderInfo = new String[4];
    }

    // teardown
    @After("@OrderItems")
    public void after() {
        orderItems.close();
    }

    // user starts on order info page check url
    @Given("I am on the order information page")
    public void onTheOrderInformationPage() {
        this.orderItems.getOrderItemsPage();
        String expectedUrl = "http://localhost:5173/order%20information";
        String actualUrl = this.orderItems.checkCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    // user selects available dropdowns 
    @When("I select {string} and {string}")
    public void selectCustomerNameAndCandyName(String customerName, String candyName) {
        orderInfo[0] = this.orderItems.selectCustomerName(customerName);
        orderInfo[1] = this.orderItems.selectCandy(candyName);
    }

    // user inputs valid or possible invalid information
    @And("input {string} and {string} fields")
    public void inputPriceAndQuantityFields(String price, String quantity) {
        this.orderItems.clearPriceField();
        orderInfo[2] = this.orderItems.inputPrice(price);
        this.orderItems.clearQuantityField();
        orderInfo[3] = this.orderItems.inputQuantity(quantity);
    }

    // sim user clicking on add button and check it went through with success modal
    @And("I click the Add Order Item button")
    public void clickTheAddOrderItemButton() {
        this.orderItems.addOrderItemButton();
        String expectedUrl = "Added order item successfully!";
        String actualUrl = this.orderItems.getAlertMsg();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    // sim user clicking on add button but this is for invalid case we will check error message later
    @And("I click on the Add Order Item button")
    public void clickOnTheAddOrderItemButton() {
        this.orderItems.addOrderItemButton();
    }

    // user should see the new order we will check the contents of the newly added row to make sure its there
    @Then("I should see the order added in the list")
    public void shouldSeeOrderItemWith() {
        String actualString = this.orderItems.getOrderItemsContents();
        for(String s : orderInfo) {
            Assert.assertTrue(actualString.contains(s));
        }
    }

    // user should not see it for invalid cases and check its not added to the list
    @Then("I should not see the order added in the list")
    public void shouldNotSeeOrderItemWith() {
        String actualString = this.orderItems.getOrderItemsContents();
        String expectedString = "";
        for(String s : orderInfo) {
            expectedString += s + " ";
        }
        Assert.assertNotEquals(actualString, expectedString.trim());
    }

    //=============================== UPDATE ============================//
    // sim user click on edit icon, check that the cancle edit button appears to be sure
    @When("I click on edit icon")
    public void clickTheEditIcon() {
        this.orderItems.clickEditIcon();
        Assert.assertTrue(this.orderItems.isCancelBtn());
    }

    // sim user click the update button
    @And("I click the Update Order Item button")
    public void iClickTheUpdateOrderItemButton() {
        this.orderItems.updateOrderItemButton();
    }

    //=============================== DELETE ============================//
    // sim user click on delete icon which we can check with the success modal popping up
    @When("I click on delete icon")
    public void clickOnDeleteIcon() {
        this.orderItems.clickOnDeleteIcon();
    }

    @Then("I click the order info delete button")
    public void iClickTheOrderInfoDeleteButton(){
        this.orderItems.clickDeleteBtn();
        String expectedMsg = "Order item deleted successfully!";
        String actualMsg = this.orderItems.getAlertMsg();
        Assert.assertEquals(actualMsg, expectedMsg);
    }

    // check that delete worked properly by iterating through the list and verify its not there
    @Then("the order item should not be visible in the table")
    public void orderItemShouldNotBeVisibleInTheTable() {
        Assert.assertFalse(this.orderItems.confirmDeletion());
    }

    // this lets user know that an invalid input or no dropdowns were selected with specific information
    @And("I should see an error msg {string}")
    public void shouldSeeAnErrorMsg(String error) {
        String expectedUrl = error;
        String actualUrl = this.orderItems.getAlertMsg();
        Assert.assertEquals(actualUrl, expectedUrl);
    }
}
