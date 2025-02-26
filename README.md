# Zl - Mobile & Web Api Test Automation Framework

This project is a test automation framework designed to run both web and mobile app tests. It uses Java, Spring Boot, and Maven for managing dependencies and building the project.


## Prerequisites

- Java 11 or higher
- Maven 3.6.0 or higher
- ChromeDriver (for web tests)
- Appium Server (for mobile app tests)


## Project Structure

- `src/main/java`: Contains the main application code.
- `src/test/java`: Contains the test code.
- `config`: Configuration classes for WebDriver and Appium.
- `hooks`: Cucumber hooks for managing test lifecycle events.
- `pages`: Page Object Model (POM) classes for web and mobile app pages.
- `stepdefinition`: Step definitions for Cucumber tests.
- `utils`: Utility classes for additional functionalities like video recording.


## Setup

**1. Install dependencies:**  
  mvn clean install

**2. Configure ChromeDriver:**
   Ensure that ChromeDriver is installed and its path is set in the WebDriverConfig class:  
    System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
    
**3. Configure Appium:**
   Ensure that Appium Server is running and its configuration is set in the AppiumConfig class. 


## Running Tests

**Web Tests**

1. Run Cucumber web tests:
   Before running Web test, Spring-boot application needs to be run to start the mock Api service where data will get retrieved.
   commands to run the spring-boot:
   **mvn spring-boot:run**
   Once server is running in terminal, web tests can be triggered by: 
   **mvn test -Dcucumber.options="--tags @web_login"**

**Mobile App Tests**

2. Run Cucumber App tests:
 **mvn test -Dcucumber.options="--tags @app_login"**

## Running with JUnit runner

1. web test
    **Run "TestRunner-Web"**

2. App test
   **Run "TestRunner-App"**








   
