package com.skillstorm;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

// cucumberoptions used for testng
@CucumberOptions(
    features = "classpath:com/skillstorm/", 
    glue = "com.skillstorm",
    plugin = {"pretty", "html:target/cucumber-reports.html"} 
)
public class RunCucumberTest extends AbstractTestNGCucumberTests{

    public static void sleepThread() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
