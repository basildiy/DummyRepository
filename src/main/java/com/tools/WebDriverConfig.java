package com.tools;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverConfig {

	public static WebDriver webdriver = null;
	static {
		if (webdriver == null) {

			System.setProperty("firefox","C:/Program Files (x86)/Mozilla Firefox/firefox.exe");

			webdriver = new FirefoxDriver();
			webdriver.get(Constants.BASE_URL);
		}
	}
	public WebDriverWait wait = new WebDriverWait(webdriver, 10);	
}
