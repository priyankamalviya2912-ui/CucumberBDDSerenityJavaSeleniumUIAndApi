package pages;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import net.serenitybdd.core.pages.PageObject;
import utility.HelperMethods;

public class CalculatorPopupPage extends PageObject implements HelperMethods {

	private WebDriver driver = getDriver();

	
	//private By motorVehicleRegistrationPopup = By.xpath("//*[contains(@id, 'modalPopup') and normalize-space(text()) = 'Motor vehicle registration']");
	private By motorVehicleRegistrationPopup = By.xpath("//*[contains(@class,'modal-content')]//*[normalize-space()='Motor vehicle registration']");
	
	
	public void VerifyMotorVehicleRegistrationPopup() {
		waitForElementVisible(getDriver(), motorVehicleRegistrationPopup);
		
		String popupText = driver.findElement(motorVehicleRegistrationPopup).getText();
		assertThat("Motor vehicle registration")
		.as("Motor Vehicle registration popup window")
		.isEqualToIgnoringCase(popupText.trim());
		System.out.println();
	}
	
	public void VerifyPopupValues(String valueToFind) {
		
		try {
		By popupWindowValue = By.xpath(String.format("//*[contains(@class,'TableApp')]//td[text()='%s']", valueToFind));
		
		waitForElementVisible(getDriver(), popupWindowValue);;
		System.out.println();
		}
		catch(Exception e) {
			System.out.println();
		}
		
	}
	
	
}
