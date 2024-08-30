package com.skillstorm.Selenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CandyList {

    // WebDriver instance to control the browser
    private WebDriver driver;

    // URLs for the candy page and home page
    private static final String url = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/candy";
    private static final String homeUrl = "http://cim-frontend.s3-website-us-east-1.amazonaws.com/";

    // Element locators for candy information fields
    @FindBy(name = "name")
    private WebElement nameField;

    @FindBy(name = "type")
    private WebElement typeField;

    @FindBy(name = "flavor")
    private WebElement flavorField;

    @FindBy(name = "price")
    private WebElement priceFeild;

    @FindBy(name = "weight")
    private WebElement weightField;

    // Element locator for the ADD CANDY button
    @FindBy(name = "candyBtn")
    private WebElement candyBtn;

    // Element locator for the candy button on the navigation bar
    @FindBy(name = "candy")
    private WebElement candyNavBtn;

    // Element locator for the candy inventory button
    @FindBy(name = "candyRoute")
    private WebElement candyInventoryBtn;

    // Element locator for the candy table
    @FindBy(name = "candyTable")
    private WebElement candyTable;

    // Element locator for the last row in the candy table to get the candy ID
    @FindBy(xpath = "//table[@name='candyTable']//tr[last()]/td[1]")
    private WebElement lastRowCandyId;

    // Element locator for the first row in the candy table to get the candy ID
    @FindBy(xpath = "//table[@name='candyTable']//tr[1]/td[1]")
    private WebElement firstRowCandyId;

    // Element locator for the delete button
    @FindBy(name = "deleteIcon")
    private WebElement deleteBtn;

    // Element locator for the edit button
    @FindBy(name = "editIcon")
    private WebElement editBtn;

    // Element locator for the candy type dropdown selector
    @FindBy(id = "candyTypeSelect")
    private WebElement candyTypeSelect;

    // Element locator for error message related to candy
    @FindBy(name = "candyListSnackbarError")
    private WebElement candyErrorMessage;

    // Variable to store the candy ID
    String candyId = "";

    // Constructor to initialize the WebDriver and PageFactory elements
    public CandyList(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    // Method to quit the WebDriver and close the browser
    public void quit() {
        this.driver.quit();
    }

    // Method to navigate to the candy page URL
    public void getCandyPageUrl() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.driver.get(url);
    }

    //========================CREATE==========================//
    
    // Method to add candy name
    public void addCandyName(String candyName) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        nameField.sendKeys(candyName);
    }

    // Method to select candy type from a dropdown
    public void addCandyType(String candyType) {
        try {
            Thread.sleep(2000);

            //Pass in candyType into select drop down to pick desired candy type if candyType is not null
            if (candyType != null && !candyType.isEmpty()) {
                candyTypeSelect.click();
                Thread.sleep(2000);
                WebElement option = driver.findElement(By.xpath("//li[@data-value='" + candyType + "']"));
                option.click();
            } else {
                //else if candyType is null click the empty value in the drop down
                candyTypeSelect.click();
                WebElement option = driver.findElement(By.xpath("//li[@data-value='']"));
                option.click();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Method to add candy flavor
    public void addCandyFlavor(String candyFlavor) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flavorField.sendKeys(candyFlavor);
    }

    // Method to add candy price
    public void addPrice(String price) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        priceFeild.sendKeys(price);
    }

    // Method to add candy weight
    public void addWeight(String weight) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        weightField.sendKeys(weight);
    }

    // Method to click the ADD CANDY button
    public void clickCandyBtn() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        candyBtn.click();
    }

    // Method to confirm the creation of a candy by printing the last row's candy ID
    public void confirmCreation() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(lastRowCandyId.getText());
    }

    // Method to check if an error message is displayed
    public void errorMessageDisplayed() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(candyErrorMessage.isDisplayed());
    }

    //========================CREATE==========================//

    //========================READ==========================//

    // Method to navigate to the home URL
    public void getHomeUrl() {
        this.driver.get(homeUrl);
    }

    // Method to navigate to the candy inventory page
    public void clickCandyInventory() {
        candyNavBtn.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        candyInventoryBtn.click();
    }

    // Method to display all rows in the candy table
    public void displayCandyTable() {
        //Iterate through candy table and print out all the table rows
        List<WebElement> tableRows = candyTable.findElements(By.xpath(".//tr"));

        for (WebElement tr : tableRows) {
            System.out.println(tr.getText());
        }
    }

    //========================READ==========================//

    //========================UPDATE==========================//

    // Method to click the edit button for a candy
    public void clickEditButton() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        editBtn.click();
    }

    // Method to update the candy name field
    public void setNameField(String name) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Find the previous value in the text field and backspace the whole value to ensure it does not pop up again
        String prevValue = nameField.getAttribute("value");
        for (int i = 0; i < prevValue.length(); i++) {
            nameField.sendKeys(Keys.BACK_SPACE);
        }
        nameField.sendKeys(name);
    }

    // Method to update the candy type field
    public void setTypeField(String type) {
        try {
            Thread.sleep(2000);

            //click into dropdown to view options
            candyTypeSelect.click();

           //Pass in type into select drop down to pick desired candy type if type is not null
            if (type != null && !type.isEmpty()) {
                Thread.sleep(2000);
                WebElement option = driver.findElement(By.xpath("//li[@data-value='" + type + "']"));
                option.click();
            } else {
                //if type is null look for the empty value
                WebElement option = driver.findElement(By.xpath("//li[@data-value='']"));
                option.click();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Method to update the candy flavor field
    public void setFlavorField(String flavor) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Find the previous value in the text field and backspace the whole value to ensure it does not pop up again
        String prevValue = flavorField.getAttribute("value");
        for (int i = 0; i < prevValue.length(); i++) {
            flavorField.sendKeys(Keys.BACK_SPACE);
        }
        flavorField.sendKeys(flavor);
    }

    // Method to update the candy price field
    public void setPriceField(String price) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Find the previous value in the text field and backspace the whole value to ensure it does not pop up again
        String prevValue = priceFeild.getAttribute("value");
        for (int i = 0; i < prevValue.length(); i++) {
            priceFeild.sendKeys(Keys.BACK_SPACE);
        }
        priceFeild.sendKeys(price);
    }

    // Method to update the candy weight field
    public void setWeightField(String weight) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Find the previous value in the text field and backspace the whole value to ensure it does not pop up again
        String prevValue = weightField.getAttribute("value");
        for (int i = 0; i < prevValue.length(); i++) {
            weightField.sendKeys(Keys.BACK_SPACE);
        }
        weightField.sendKeys(weight);
    }

    //Method to confirm that the candy has been updated
    public void confirmUpdation(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Printing out the first row's text to confirm which candy was updated
        System.out.println("Updated Candy ID " + firstRowCandyId.getText());
    }

    //========================UPDATE==========================//




//========================DELETE==========================//

// Method to click the delete icon for the last row in the candy table
public void clickDeleteIcon() {
    try {
        Thread.sleep(1000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    // Get the candy ID from the last row
    candyId = lastRowCandyId.getText();

    // Find and click the delete button for the last row
    WebElement lastRowDeleteBtn = driver.findElement(By.xpath("//table[@name='candyTable']//tbody/tr[last()]/td[last()]/button[@name='deleteIcon']"));
    lastRowDeleteBtn.click();
}

// Method to confirm deletion by checking if the candy ID is no longer in the table
public void confirmDeletion() {
    try {
        Thread.sleep(1000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    //Iterating through the candy table and checking to see if the deleted ID is still in the table
    //If not return False, if it is throw an Assertion
    List<WebElement> tableRows = candyTable.findElements(By.xpath(".//tr/td[1]"));
    for (WebElement tr : tableRows) {
        if (tr.getText().contains(candyId)) {
            throw new AssertionError("Candy with ID " + candyId + " was found in the table");
        } else {
            System.err.println("False");
        }
    }
}

//========================DELETE==========================//
}
