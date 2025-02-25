package com.zl.mobileautomation.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import static io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty",
                "html:target/cucumber.html",
                "summary",
                "json:target/cucumber.json",
                "junit:target/cucumber.xml"}
        ,features = {"src/test/resources/features"}
        ,glue = {"com.zl.mobileautomation.stepdefinition",
         "com.zl.mobileautomation.hooks"}
        ,monochrome=true,
        tags = "@app_login"
)

public class TestRunner {

}
