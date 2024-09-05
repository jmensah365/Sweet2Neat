package com.skillstorm.cucumber;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import com.skillstorm.Selenium.OrdersList;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

public class OrdersStepDefinition {
    private OrdersList ordersList;
    private String[] order;
    
    @Before("@Orders")
    public void before(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless","--no-sandbox", "--disable-gpu", "--window-size=1920,1080", "--disable-dev-shm-usage");
        WebDriver driver = new ChromeDriver(options);
        this.ordersList = new OrdersList(driver);
        order = new String[3];
    }

    @After("@Orders")
    public void quit(){
        ordersList.quit();
    }

    @Given("I am on the order list page")
    public void iAmOnTheOrderListPage(){
        ordersList.getOrderListPageUrl();
        String expectedUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/orders";
        String actualUrl = this.ordersList.checkCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @When("I fill in {string}, {string}, and {string}")
    public void iFillIn(String customerName, String status, String customerAddress){
        order[0] = ordersList.getCustomerName(customerName);
        order[1] = ordersList.getStatus(status);
        order[2] = ordersList.getCustomerAddress(customerAddress);
    }

    @And("I click the Add order button")
    public void iClickTheAddOrderButton(){
        ordersList.clickAddOrderBtn();
        String expectedUrl = "Order added successfully!";
        String actualUrl = this.ordersList.getAlertMsg();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @And("I click on the Add order button")
    public void iClickOnTheAddOrderButton(){
        ordersList.clickAddOrderBtn();
    }

    @Then("new order information should be in the table")
    public void newOrderInformationShouldBeInTheTable(){
        String actualString = ordersList.confirmOrderCreation();
        for(String s : order) {
            Assert.assertTrue(actualString.contains(s));
        }
    }

    @Given("I am on the base page")
    public void iAmOnTheBasePage(){
        ordersList.getHomeUrl();
        String expectedUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/";
        String actualUrl = this.ordersList.checkCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @When("I navigate to the order list page")
    public void iNavigateToTheOrderListPage(){
        ordersList.clickListOfOrdersBtn();
        String expectedUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/orders";
        String actualUrl = this.ordersList.checkCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @Then("I should see the list of orders")
    public void iShouldSeeTheListOfOrders(){
        String expectedString = "Order Id Customer Name Order Date Status Customer Address";
        String actualString = ordersList.displayOrderListTable();
        Assert.assertEquals(actualString, expectedString);
    }

    @When("I click the delete button")
    public void iClickTheDeleteIcon(){
        ordersList.clickDeleteIcon();
        String expectedString = "Order deleted successfully!";
        String actualString = ordersList.getAlertMsg();
        Assert.assertEquals(actualString, expectedString);
    }

    @Then("The order should not be visible in the order list page")
    public void theOrderShouldNotBeVisibleInTheOrderListPage(){
        Assert.assertFalse(ordersList.confirmDeletion());
    }

    @When("I click the edit button")
    public void iClickTheEditButton(){
        ordersList.clickEditButton();
        Assert.assertTrue(this.ordersList.isCancelBtn());
    }

    @And("I modify {string}, {string}, and\\/or {string}")
    public void iModifyInformation(String customerName, String status, String customerAddress){
        order[0] = ordersList.setCustomerName(customerName);
        order[1] = ordersList.setStatus(status);
        order[2] = ordersList.setCustomerAddress(customerAddress);
    }

    @And("I click the update order button")
    public void iClickTheUpdateOrderButton(){
        ordersList.clickAddOrderBtn();
        String expectedString = "Order updated successfully!";
        String actualString = ordersList.getAlertMsg();
        Assert.assertEquals(actualString, expectedString);
    }
    
    @And("I click on the update order button")
    public void iClickOnTheUpdateOrderButton(){
        ordersList.clickAddOrderBtn();
    }

    @Then("I should see the updated order in the list of orders")
    public void iShouldSeeTheUpdatedOrderInTheListOfOrders(){
        String actualString = this.ordersList.confirmOrderUpdation();
        System.out.println(actualString);
        for(String s : order) {
            System.out.println(s);
            Assert.assertTrue(actualString.contains(s));
        }
    }

    @Then("I should see the error message {string}")
    public void iShouldSeeAnErrorMessage(String error){
        String expectedUrl = error;
        String actualUrl = this.ordersList.getAlertMsg();
        Assert.assertEquals(actualUrl, expectedUrl);
    }
}