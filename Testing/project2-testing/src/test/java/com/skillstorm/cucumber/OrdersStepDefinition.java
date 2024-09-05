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
    // Private instance of OrdersList to interact with the web elements related to orders
    private OrdersList ordersList;
    
    // Hook that runs before each scenario tagged with @Orders
    @Before("@Orders")
    public void before(){
        // Setting up ChromeOptions to run Chrome in headless mode (without GUI)
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless","--no-sandbox");
        WebDriver driver = new ChromeDriver(options);

        // Initializing OrdersList with the WebDriver instance
        this.ordersList = new OrdersList(driver);
    }

    // Hook that runs after each scenario tagged with @Orders
    @After("@Orders")
    public void quit(){
        // Quitting the WebDriver instance to close the browser
        ordersList.quit();
    }

    // Step definition for the Given statement to navigate to the order list page
    @Given("I am on the order list page")
    public void iAmOnTheOrderListPage(){
        ordersList.getOrderListPageUrl();
    }

    // Step definition for the When statement to fill in order information
    @When("I fill in {string}, {string}, and {string}")
    public void iFillIn(String customerName, String status, String customerAddress){
        ordersList.getCustomerName(customerName);
        ordersList.getStatus(status);
        ordersList.getCustomerAddress(customerAddress);
    }

    // Step definition for the And statement to click the Add order button
    @And("I click the Add order button")
    public void iClickTheAddOrderButton(){
        ordersList.clickAddOrderBtn();
    }

    // Step definition for the Then statement to verify the new order appears in the list
    @Then("new order information should be in the table")
    public void newOrderInformationShouldBeInTheTable(){
        ordersList.confirmOrderCreation();
    }

    // Step definition for the Given statement to navigate to the base (home) page
    @Given("I am on the base page")
    public void iAmOnTheBasePage(){
        ordersList.getHomeUrl();
    }

    // Step definition for the When statement to navigate to the order list page from the base page
    @When("I navigate to the order list page")
    public void iNavigateToTheOrderListPage(){
        ordersList.clickListOfOrdersBtn();
    }

    // Step definition for the Then statement to verify the list of orders is displayed
    @Then("I should see the list of orders")
    public void iShouldSeeTheListOfOrders(){
        ordersList.displayOrderListTable();
    }

    // Step definition for the When statement to click the delete button for an order
    @When("I click the delete button")
    public void iClickTheDeleteIcon(){
        ordersList.clickDeleteIcon();
    }

    // Step definition for the Then statement to verify the order is no longer visible in the list
    @Then("The order should not be visible in the order list page")
    public void theOrderShouldNotBeVisibleInTheOrderListPage(){
        ordersList.confirmDeletion();
    }

    // Step definition for the When statement to click the edit button for an order
    @When("I click the edit button")
    public void iClickTheEditButton(){
        ordersList.clickEditButton();
    }

    // Step definition for the And statement to modify the order information
    @And("I modify {string}, {string}, and\\/or {string}")
    public void iModifyInformation(String customerName, String status, String customerAddress){
        ordersList.setCustomerName(customerName);
        ordersList.setStatus(status);
        ordersList.setCustomerAddress(customerAddress);
    }

    // Step definition for the And statement to click the update order button
    @And("I click the update order button")
    public void iClickTheUpdateOrderButton(){
        ordersList.clickAddOrderBtn(); // Reusing the Add order button method for updating the order
    }

    // Step definition for the Then statement to verify the updated order appears in the list
    @Then("I should see the updated order in the list of orders")
    public void iShouldSeeTheUpdatedOrderInTheListOfOrders(){
        ordersList.confirmOrderUpdation();
    }

    // Step definition for the Then statement to verify an error message is displayed for invalid input
    @Then("I should see an error message")
    public void iShouldSeeAnErrorMessage(){
        ordersList.displayErrorMessage();
    }
}