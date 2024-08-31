package com.skillstorm.cucumber;

import org.junit.jupiter.api.Order;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.skillstorm.Selenium.OrdersList;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

public class OrdersStepDefinition {
    private OrdersList ordersList;
    
    @Before("@Orders")
    public void before(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless","--no-sandbox");
        WebDriver driver = new ChromeDriver(options);

        this.ordersList = new OrdersList(driver);
    }

    @After("@Orders")
    public void quit(){
        ordersList.quit();
    }

    @Given("I am on the order list page")
    public void iAmOnTheOrderListPage(){
        ordersList.getOrderListPageUrl();
    }

    @When("I fill in {string}, {string}, {string}, and {string}")
    public void iFillIn(String customerName, String orderDate, String status, String customerAddress){
        ordersList.getCustomerName(customerName);
        ordersList.getOrderDate(orderDate);
        ordersList.getStatus(status);
        ordersList.getCustomerAddress(customerAddress);
    }

    @And("I click the Add order button")
    public void iClickTheAddOrderButton(){
        ordersList.clickAddOrderBtn();
    }

    @Then("new order information should be in the table")
    public void newOrderInformationShouldBeInTheTable(){
        ordersList.confirmOrderCreation();
    }

    @Given("I am on the base page")
    public void iAmOnTheBasePage(){
        ordersList.getHomeUrl();
    }

    @When("I navigate to the order list page")
    public void iNavigateToTheOrderListPage(){
        ordersList.clickListOfOrdersBtn();
    }

    @Then("I should see the list of orders")
    public void iShouldSeeTheListOfOrders(){
        ordersList.displayOrderListTable();
    }

    @When("I click the delete button")
    public void iClickTheDeleteIcon(){
        ordersList.clickDeleteIcon();
    }

    @Then("The order should not be visible in the order list page")
    public void theOrderShouldNotBeVisibleInTheOrderListPage(){
        ordersList.confirmDeletion();
    }

    @When("I click the edit button")
    public void iClickTheEditButton(){
        ordersList.clickEditButton();
    }

    @And("I modify {string}, {string}, {string}, and\\/or {string}")
    public void iModifyInformation(String customerName, String orderDate, String status, String customerAddress){
        ordersList.setCustomerName(customerName);
        ordersList.setOrderDate(orderDate);
        ordersList.setStatus(status);
        ordersList.setCustomerAddress(customerAddress);
    }

    @And("I click the update order button")
    public void iClickTheUpdateOrderButton(){
        ordersList.clickAddOrderBtn();
    }

    @Then("I should see the updated order in the list of orders")
    public void iShouldSeeTheUpdatedOrderInTheListOfOrders(){
        ordersList.confirmOrderUpdation();
    }

    @Then("I should see an error message")
    public void iShouldSeeAnErrorMessage(){
        ordersList.displayErrorMessage();
    }

    
}