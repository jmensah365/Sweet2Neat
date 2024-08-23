package com.skillstorm.Selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrdersList {
    private WebDriver driver;
    private static final String url = "http://localhost:5173/orders";
    private static final String homeUrl = "http://localhost:5173/";

    @FindBy(name = "customerName")
    private WebElement customerNameField;

    @FindBy(name = "orderDate")
    private WebElement orderDateField;

    @FindBy(name = "status")
    private WebElement statusField;

    @FindBy(name = "customerAddress")
    private WebElement customerAddressField;

    @FindBy(name = "orderListBtn")
    private WebElement orderListBtn;



}
