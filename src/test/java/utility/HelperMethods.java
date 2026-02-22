package utility;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;

import java.time.Duration;

public interface  HelperMethods {
	
	EnvironmentVariables vars = SystemEnvironmentVariables.createEnvironmentVariables();
    int timeout = vars.getPropertyAsInteger("webdriver.wait.for.timeout", 5000);
    
	
	  // Helper to read serenity.timeout from properties
    default int getSerenityTimeout() {
        EnvironmentVariables env = SystemEnvironmentVariables.createEnvironmentVariables();
        // default to 5 seconds if not set
        return Integer.parseInt(env.getProperty("serenity.timeout", "5000"));
    }
    
    default void scrollIntoView(WebDriver driver, By locator) {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", driver.findElement(locator));
    }
    
    default void waitForPageLoad(WebDriver driver) {
    	new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                webDriver -> ((JavascriptExecutor) webDriver)
                    .executeScript("return document.readyState").equals("complete")
            );
        }
    

    // Wait for element to be visible using serenity.timeout
    default WebElement waitForElementVisible(WebDriver driver, By locator) {
        int timeoutInMillis = getSerenityTimeout(); // fetch value from serenity.properties
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(timeoutInMillis));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait for element to be clickable (optional override)
    default WebElement waitForElementClickable(WebDriver driver, By locator) {
        int timeoutInMillis = getSerenityTimeout();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(timeoutInMillis));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
 // Wait for element to be visible using serenity.timeout
    default void waitForTitle(WebDriver driver, String expectedTitle) {
        int timeoutInMillis = getSerenityTimeout(); // fetch value from serenity.properties
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(timeoutInMillis));
        wait.until(ExpectedConditions.titleIs(expectedTitle));
    }
}
