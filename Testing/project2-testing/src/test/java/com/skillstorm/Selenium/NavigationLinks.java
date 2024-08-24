package com.skillstorm.Selenium;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class NavigationLinks {
    
    private WebDriver driver;
    
    private static final String homeUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/";
    private static final String orderUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/orders";

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
        this.driver.get(orderUrl);
    }

    public void clickLogo() {
        logo.click();
    }

    public String checkCurrentPage() {
        return this.driver.getCurrentUrl();
    }

    //================= NAV TO WAREHOUSE LIST PAGE ==================//
    public void getHomePage() {
        this.driver.get(homeUrl);
    }

    public void clickWarehouseMenu() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        warehousesMenu.click();        
        warehousesListMenu.click();        
    }

    public String getWarehouseListHeader() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return warehouseListHeader.getText();
    }

    //================= NAV TO CANDY INVENTORY PAGE ==================//
    public void clickOnCandyInventoryMenu() {
        candyMenu.click();
        candyInventoryMenu.click();        
    }

    public String getCandyInventoryHeader() {
        return candyInventoryHeader.getText();
    }

    //================= NAV TO CANDY CATEGORIES PAGE ==================//
    public void clickOnCandyCategoriesMenu() {
        candyMenu.click();
        candyCategoriesMenu.click();
    }

    public String getCandyCategoriesHeader() {
        return candyCategoriesHeader.getText();
    }
    
    //================= NAV TO LIST OF ORDERS PAGE ==================//
    public void clickOnListOfOrdersMenu() {
        ordersMenu.click();
        listOfOrdersMenu.click();
    }

    public String getListOfOrdersHeader() {
        return listOfOrdersHeader.getText();
    }

    //================= NAV TO ORDER INFO PAGE ==================//
    public void clickOnOrderInfoMenu() {
        ordersMenu.click();
        orderInfoMenu.click();
    }

    public String getOrderInfoHeader() {
        return orderInfoHeader.getText();
    }

    //================= NAV TO ABOUT PAGE ==================//
    public void clickOnAboutMenu() {
        aboutMenu.click();
    }

    public String getAboutInfo() {
        return aboutInfo.getText();
    }
    
    //================= NAV TO WAREHOUSE STOCK PAGE ==================//
    public void clickOnWarehouseStockMenu() {
        warehousesMenu.click();
        warehouseStockMenu.click();
    }

    public String getWarehouseStockHeader() {
        return warehouseStockHeader.getText();
    }

    //================= NAV USING ADD A WAREHOUSE BUTTON ==================//
    public void clickOnAddAWarehouseButton() {
        addWarehouseButton.click();
    }
}

