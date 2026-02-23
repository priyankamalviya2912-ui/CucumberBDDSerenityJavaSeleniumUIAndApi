package pages;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.serenitybdd.core.pages.PageObject;
import utility.HelperMethods;


public class CalculatorPopupPage extends PageObject implements HelperMethods {
	private static final Logger log = LoggerFactory.getLogger(MotorVehicleRegistrationPage.class);
	
	private WebDriver driver = getDriver();
	private By motorVehicleRegistrationPopup = By.xpath("//*[contains(@class,'modal-content')]//*[normalize-space()='Motor vehicle registration']");
	
	
	public void VerifyMotorVehicleRegistrationPopup() {
		log.info("waiting for dialog window to open");
		waitForElementVisible(getDriver(), motorVehicleRegistrationPopup);
		
		String popupText = driver.findElement(motorVehicleRegistrationPopup).getText();
		assertThat("Motor vehicle registration")
		.isEqualToIgnoringCase(popupText.trim());
		
	}
	
	public void VerifyPopupValues(String valueToFind) {
		
		assertThat(!valueToFind.isEmpty())
		.as("Value for popup cant be empty")
		.isTrue();
		
		By popupWindowValue = By.xpath(String.format("//*[contains(@class,'TableApp')]//td[contains(text(),'%s')]", valueToFind));
		log.info("verifying if the text value is found in the dialog window of calculator " +popupWindowValue);
		assertThat($(popupWindowValue).isCurrentlyVisible())
        .as("The calculation result popup window should be visible having ==>"+valueToFind)
        .isTrue();
		
	}
	
	
}
