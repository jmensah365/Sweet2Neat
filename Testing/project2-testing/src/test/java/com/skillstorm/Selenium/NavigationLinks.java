package com.skillstorm.Selenium;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.skillstorm.RunCucumberTest;


public class NavigationLinks {
    
    private WebDriver driver;
    
    private static final String homeUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/";
    private static final String orderUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/orders";
    private static final String candyCategoriesUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/candyTypes";

    @FindBy(name = "logo")
    private WebElement logo;

    @FindBy(name = "warehouses")
    private WebElement warehousesMenu;

    @FindBy(name = "warehousesRoute")
    private WebElement warehousesListMenu;

    @FindBy(name = "warehouseListTitle")
    private WebElement warehouseListHeader;

    @FindBy(name = "candy")
    private WebElement candyMenu;

    @FindBy(name = "candyRoute")
    private WebElement candyInventoryMenu;

    @FindBy(name = "candyTypesRoute")
    private WebElement candyCategoriesMenu;

    @FindBy(name = "candyInventoryTitle")
    private WebElement candyInventoryHeader;

    @FindBy(name = "candyTypesHeader")
    private WebElement candyCategoriesHeader;

    @FindBy(name = "orders")
    private WebElement ordersMenu;

    @FindBy(name = "orderListRoute")
    private WebElement listOfOrdersMenu;

    @FindBy(name = "orderListTitle")
    private WebElement listOfOrdersHeader;

    @FindBy(name = "orderInfoRoute")
    private WebElement orderInfoMenu;

    @FindBy(name = "orderInfoTitle")
    private WebElement orderInfoHeader;

    @FindBy(name = "about")
    private WebElement aboutMenu;

    @FindBy(name = "aboutInfo")
    private WebElement aboutInfo;

    @FindBy(name = "warehousesStockRoute")
    private WebElement warehouseStockMenu;

    @FindBy(name = "warehouseStockTitle")
    private WebElement warehouseStockHeader;

    @FindBy(name = "homeAddAWarehouseBtn")
    private WebElement addWarehouseButton;

    @FindBy(name = "gummyCardButton")
    private WebElement gummyCardButton;

    @FindBy(name = "chocolateCardButton")
    private WebElement chocolateCardButton;

    @FindBy(name = "sourCandyCardButton")
    private WebElement sourCandyCardButton;

    @FindBy(name = "taffyCardButton")
    private WebElement taffyCardButton;

    @FindBy(name = "lollipopCardButton")
    private WebElement lollipopCardButton;

    @FindBy(tagName = "h3")
    private WebElement candyHeader;

    // Setup driver
    public NavigationLinks(WebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    public void close() {
        this.driver.close();
    }

    //================= NAV TO HOME PAGE ==================//
    public void getOrderPage() {
        RunCucumberTest.sleepThread();
        this.driver.get(orderUrl);
    }

    public void clickLogo() {
        RunCucumberTest.sleepThread();
        logo.click();
    }

    public String checkCurrentPage() {
        RunCucumberTest.sleepThread();
        return this.driver.getCurrentUrl();
    }

    //================= NAV TO WAREHOUSE LIST PAGE ==================//
    public void getHomePage() {
        RunCucumberTest.sleepThread();
        this.driver.get(homeUrl);
    }

    public void clickWarehouseMenu() {
        RunCucumberTest.sleepThread();
        warehousesMenu.click();        
        RunCucumberTest.sleepThread();
        warehousesListMenu.click();        
    }

    public String getWarehouseListHeader() {
        RunCucumberTest.sleepThread();
        return warehouseListHeader.getText();
    }

    //================= NAV TO CANDY INVENTORY PAGE ==================//
    public void clickOnCandyInventoryMenu() {
        RunCucumberTest.sleepThread();
        candyMenu.click();
        RunCucumberTest.sleepThread();
        candyInventoryMenu.click();        
    }

    public String getCandyInventoryHeader() {
        RunCucumberTest.sleepThread();
        return candyInventoryHeader.getText();
    }

    //================= NAV TO CANDY CATEGORIES PAGE ==================//
    public void clickOnCandyCategoriesMenu() {
        RunCucumberTest.sleepThread();
        candyMenu.click();
        RunCucumberTest.sleepThread();
        candyCategoriesMenu.click();
    }

    public String getCandyCategoriesHeader() {
        RunCucumberTest.sleepThread();
        return candyCategoriesHeader.getText();
    }
    
    //================= NAV TO LIST OF ORDERS PAGE ==================//
    public void clickOnListOfOrdersMenu() {
        RunCucumberTest.sleepThread();
        ordersMenu.click();
        RunCucumberTest.sleepThread();
        listOfOrdersMenu.click();
    }

    public String getListOfOrdersHeader() {
        RunCucumberTest.sleepThread();
        return listOfOrdersHeader.getText();
    }

    //================= NAV TO ORDER INFO PAGE ==================//
    public void clickOnOrderInfoMenu() {
        RunCucumberTest.sleepThread();
        ordersMenu.click();
        RunCucumberTest.sleepThread();
        orderInfoMenu.click();
    }

    public String getOrderInfoHeader() {
        RunCucumberTest.sleepThread();
        return orderInfoHeader.getText();
    }

    //================= NAV TO ABOUT PAGE ==================//
    public void clickOnAboutMenu() {
        RunCucumberTest.sleepThread();
        aboutMenu.click();
    }

    public String getAboutInfo() {
        RunCucumberTest.sleepThread();
        return aboutInfo.getText();
    }
    
    //================= NAV TO WAREHOUSE STOCK PAGE ==================//
    public void clickOnWarehouseStockMenu() {
        RunCucumberTest.sleepThread();
        warehousesMenu.click();
        RunCucumberTest.sleepThread();
        
        warehouseStockMenu.click();
    }

    public String getWarehouseStockHeader() {
        RunCucumberTest.sleepThread();
        return warehouseStockHeader.getText();
    }

    //================= NAV USING ADD A WAREHOUSE BUTTON ==================//
    public void clickOnAddAWarehouseButton() {
        RunCucumberTest.sleepThread();
        addWarehouseButton.click();
    }

    //================= NAV TO DIFFERENT CANDY TYPES ==================//
    public void candyCategoriesPage() {
        RunCucumberTest.sleepThread();
        this.driver.get(candyCategoriesUrl);
    }

    public void selectCandyType(String candyType) {
        RunCucumberTest.sleepThread();
        switch(candyType) {
            case "Gummy Candy":
            gummyCardButton.click();
                break;
            case "Chocolate Candy":
            chocolateCardButton.click();
                break;
            case "Sour Candy":
            sourCandyCardButton.click();
                break;
            case "Taffy Candy":
            taffyCardButton.click();
                break;
            case "Lollipop Candy":
            lollipopCardButton.click();
                break;
            default:
        }
    }

    public boolean checkCandyHeader(String candyType) {
        RunCucumberTest.sleepThread();
        return candyHeader.getText().equals(candyType);
    }
}
