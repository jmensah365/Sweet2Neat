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

    @Before("@OrderItems")
    public void before() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new","--no-sandbox", "--disable-gpu", "--window-size=1920,1080", "--disable-dev-shm-usage");
        WebDriver driver = new ChromeDriver(options);
        this.orderItems = new OrderItems(driver);
        orderInfo = new String[4];
    }

    @After("@OrderItems")
    public void after() {
        orderItems.close();
    }

    @Given("I am on the order information page")
    public void onTheOrderInformationPage() {
        this.orderItems.getOrderItemsPage();
        String expectedUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/orderInfo";
        String actualUrl = this.orderItems.checkCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @When("I select {string} and {string}")
    public void selectCustomerNameAndCandyName(String customerName, String candyName) {
        orderInfo[0] = this.orderItems.selectCustomerName(customerName);
        orderInfo[1] = this.orderItems.selectCandy(candyName);
    }

    @And("input {string} and {string} fields")
    public void inputPriceAndQuantityFields(String price, String quantity) {
        this.orderItems.clearPriceField();
        orderInfo[2] = this.orderItems.inputPrice(price);
        this.orderItems.clearQuantityField();
        orderInfo[3] = this.orderItems.inputQuantity(quantity);
    }

    @And("I click the Add Order Item button")
    public void clickTheAddOrderItemButton() {
        this.orderItems.addOrderItemButton();
        String expectedUrl = "Added order item successfully!";
        String actualUrl = this.orderItems.getAlertMsg();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @And("I click on the Add Order Item button")
    public void clickOnTheAddOrderItemButton() {
        this.orderItems.addOrderItemButton();
    }

    @Then("I should see the order added in the list")
    public void shouldSeeOrderItemWith() {
        String actualString = this.orderItems.getOrderItemsContents();
        for(String s : orderInfo) {
            Assert.assertTrue(actualString.contains(s));
        }
    }

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
    @When("I click on edit icon")
    public void clickTheEditIcon() {
        this.orderItems.clickEditIcon();
        Assert.assertTrue(this.orderItems.isCancelBtn());
    }

    @And("I click the Update Order Item button")
    public void iClickTheUpdateOrderItemButton() {
        this.orderItems.updateOrderItemButton();
    }

    //=============================== DELETE ============================//
    @When("I click on delete icon")
    public void clickOnDeleteIcon() {
        this.orderItems.clickOnDeleteIcon();
        String expectedUrl = "Order item deleted successfully!";
        String actualUrl = this.orderItems.getAlertMsg();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @Then("the order item should not be visible in the table")
    public void orderItemShouldNotBeVisibleInTheTable() {
        Assert.assertFalse(this.orderItems.confirmDeletion());
    }

    @And("I should see an error msg {string}")
    public void shouldSeeAnErrorMsg(String error) {
        String expectedUrl = error;
        String actualUrl = this.orderItems.getAlertMsg();
        Assert.assertEquals(actualUrl, expectedUrl);
    }
}
