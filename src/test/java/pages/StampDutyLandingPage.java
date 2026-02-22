package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import utility.*;


@DefaultUrl("https://www.service.nsw.gov.au/transaction/check-motor-vehicle-stamp-duty")
public class StampDutyLandingPage extends PageObject implements HelperMethods {
	
	private WebDriver driver = getDriver();
	private By checkButton = By.xpath("//a[contains(@aria-label,'Check online')]");	
    

    public void clickCheckOnlineButton() {
        waitForElementVisible(driver, checkButton);
        waitForElementClickable(driver, checkButton);
        driver.findElement(checkButton).click();
        System.out.println();
        
    }
    
    

	/*
	 * public boolean isPageLoaded() { return checkOnlineButton.isPresent(); }
	 */
}
