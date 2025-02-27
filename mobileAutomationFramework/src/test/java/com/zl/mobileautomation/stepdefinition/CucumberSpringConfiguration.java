package com.zl.mobileautomation.stepdefinition;

import config.AppiumConfig;
import config.WebDriverConfig;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration(classes = {AppiumConfig.class, WebDriverConfig.class})
@PropertySource("file:bootstrap.properties")
public class CucumberSpringConfiguration {
}
