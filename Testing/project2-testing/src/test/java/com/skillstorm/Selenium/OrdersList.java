package com.skillstorm.Selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
public class OrdersList {
    private WebDriver driver;
    private static final String url = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/orders";
    private static final String homeUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/";

    @FindBy(name = "customerName")
    private WebElement customerNameField;

    @FindBy(name = "orderDatePicker")
    private WebElement orderDateField;

    @FindBy(name = "status")
    private WebElement statusField;

    @FindBy(name = "customerAddress")
    private WebElement customerAddressField;

    @FindBy(name = "orderListBtn")
    private WebElement orderListBtn;

}