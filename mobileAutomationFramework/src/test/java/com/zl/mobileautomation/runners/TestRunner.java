package com.zl.mobileautomation.runners;

import com.zl.mobileAutomation.MobileAutomationFrameworkApplication;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MobileAutomationFrameworkApplication.class)
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty",
                "html:target/cucumber.html",
                "summary",
                "json:target/cucumber.json",
                "junit:target/cucumber.xml"}
        ,features = {"src/test/resources/features"}
        ,glue = {"com.zl.mobileautomation.stepdefinition"
         }
        ,monochrome=true,
        tags = "@api_test"
)

public class TestRunner {

}
