package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import utility.HelperMethods;

public class MotorVehicleRegistrationPage extends PageObject implements HelperMethods {

	private WebDriver driver = getDriver();
	
	private By registrationRadio = By.xpath("//input[@id='passenger_Y']");
	private By purchasePriceText = By.id("purchasePrice");
	private By calculateButton = By.xpath("//button[@type='submit']");
	private By pageLink = By.xpath("//a[contains(@href,'motorsimple')]");
	
	private String pageTitle = "Motor vehicle registration duty calculator";
	
	public void verifyMotorRegistrationPageIsDisplayed() {
		waitForTitle(driver, pageTitle);
	}
	
	public void navigateToMotorVehicleRegistration() {
		find(pageLink).click();
	}
	
	public void selectYesOnRegistrationRadio() {
		
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", driver.findElement(registrationRadio));
	    
		waitForElementVisible(driver, registrationRadio);
		
        waitForElementClickable(driver, registrationRadio);
        driver.findElement(registrationRadio).click();
        System.out.println();
	}
	
	public void inputPurchasePriceText(String purchaseValue) {
		waitForElementVisible(driver, purchasePriceText);
        waitForElementClickable(driver, purchasePriceText);
        driver.findElement(purchasePriceText).sendKeys(purchaseValue);
        System.out.println();
	}
	
	public void clickCalculateButton() {
		waitForElementVisible(driver, calculateButton);
        waitForElementClickable(driver, calculateButton);
        driver.findElement(calculateButton).click();
        System.out.println();
	}
}
