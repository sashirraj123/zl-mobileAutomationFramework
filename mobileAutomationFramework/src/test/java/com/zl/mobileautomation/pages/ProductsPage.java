package com.zl.mobileautomation.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import java.net.MalformedURLException;

public class ProductsPage extends BasePage {
	@AndroidFindBy (xpath = "//android.widget.TextView[@text='PRODUCTS']")
	@iOSXCUITFindBy (xpath ="//XCUIElementTypeOther[@name=\"test-Toggle\"]/parent::*[1]/preceding-sibling::*[1]")
	private WebElement titleTxt;

	@iOSXCUITFindBy (xpath = "//XCUIElementTypeOther[@name=\"test-PRODUCTS\"]/XCUIElementTypeScrollView")
	private WebElement iOSSCrollView;

	public ProductsPage() throws MalformedURLException {
	}

	public String getTitle() {
		return getText(titleTxt, "product page title is - ");
	}

}
