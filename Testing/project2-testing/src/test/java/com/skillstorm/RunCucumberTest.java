package com.skillstorm;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

// @Suite
// @IncludeEngines("cucumber")
// @SelectClasspathResource("com/skillstorm/navigation.feature")
// @ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
// @ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "html:target/cucumber-reports.html")
@CucumberOptions(
    features = "classpath:com/skillstorm/orderItem.feature", 
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
