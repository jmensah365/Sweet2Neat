package com.skillstorm.Selenium;

import org.openqa.selenium.JavascriptExecutor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.deque.axe.AXE;

public class AccessibilityTesting {
    private WebDriver driver;
    private static final URL scriptUrl = AccessibilityTesting.class.getResource("/axe.min.js");

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("http://localhost:5173/about");
    }

    @Test
    public void testAccessibility() throws IOException {
        // Load axe.min.js from resources
        String axeScript = new String(Files.readAllBytes(Paths.get("src/test/resources/axe.min.js")));
        ((JavascriptExecutor) driver).executeScript(axeScript);

        // Run Axe analysis
        JSONObject responseJSON = new AXE.Builder(driver, scriptUrl).analyze();
        JSONArray violations = responseJSON.getJSONArray("violations");

        if (violations.length() == 0) {
            System.out.println("No accessibility violations found");
        } else {
            AXE.writeResults("target/axe", responseJSON);
            Assert.fail(AXE.report(violations));
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

