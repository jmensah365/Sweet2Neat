package com.skillstorm.Selenium;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CandyList {
    
    private WebDriver driver;
    private static final String url = "http://localhost:5173/candy";
    private static final String homeUrl = "http://localhost:5173/";


        public CandyList(WebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }


    /*
     * Finding name, type, flavor, price, and weight text fields
     */

    //@FindBy()
}
