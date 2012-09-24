package com.steps.frontend;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;

import com.tools.Constants;
import com.tools.FrontendInterface;
import com.tools.WebDriverConfig;

import cucumber.annotation.Before;
import cucumber.annotation.en.And;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;

public class YoutubeSearchSteps extends WebDriverConfig {
	/**
	 *  Default Setup that runs before any test that imports this step class
	 */
	@Before
	public void setUp() {
		webdriver.get(Constants.BASE_URL);
	}

	/**
	 * Navigate to the URL passed as a String parameter
	 * @param pageURL
	 */
	@Given("^a url link \"([^\"]*)\"$")
	public void navigateToUrl(String pageURL) {
		webdriver.get(pageURL);
	}

	/**
	 *  Search for the string passed as a parameter
	 * @param searchInput
	 */
	@And("^I search for: \"([^\"]*)\" search term$")
	public void searchTextField(String searchInput) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.cssSelector(FrontendInterface.SEARCH_INPUT_FIELD_CSS)));
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.cssSelector(FrontendInterface.SEARCH_BUTTON)));
		webdriver.findElement(
				By.cssSelector(FrontendInterface.SEARCH_INPUT_FIELD_CSS)).sendKeys(
				searchInput);
		webdriver.findElement(By.cssSelector(FrontendInterface.SEARCH_BUTTON))
				.click();
	}

	/**
	 * Verify the presence of the result list
	 */
	@Then("^I should see result list$")
	public void checkForResultList() {
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.cssSelector(FrontendInterface.SEARCH_RESULT_LIST)));
	}

	/**
	 * Select item, passed as a String parameter, from result list 
	 * @param expectedResult
	 */
	@Given("^I select resul list item \"([^\"]*)\"$")
	public void selectResultListItem(String expectedResult) {
		WebElement searchListContainer = webdriver.findElement(By.cssSelector(FrontendInterface.SEARCH_RESULT_LIST));
		List<WebElement> resultList = searchListContainer.findElements(By.cssSelector("li"));
		if(!resultList.isEmpty()){
			for (WebElement elem:resultList){
				if (elem.getText().contains(expectedResult)){
					elem.findElement(By.tagName("h3")).click();
					break;
				}					
			}
		}		
	}

	/**
	 *  Check for flash video player
	 */
	@Then("^I should see the video$")
	public void checkForVideoPlayer() {
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.cssSelector("div#watch-video-container")));	
	}
	
	/**
	 *  Wait msecNr for video to play
	 * @param msecNr
	 * @throws InterruptedException
	 */
	@Then("^wait for (\\d+)$")
	public void waitToPlayVideo(int msecNr) throws InterruptedException{		
		Thread.sleep(msecNr);		
	}
	
	/**
	 * Close browser
	 */
	@Then("^close browser and quit$")
	public void closeBrowser(){
		webdriver.close();
		webdriver.quit();
	}
}
