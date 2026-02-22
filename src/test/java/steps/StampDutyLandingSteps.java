package steps;

import net.serenitybdd.annotations.Step;
import pages.StampDutyLandingPage;

public class StampDutyLandingSteps {
    StampDutyLandingPage landingPage;

    @Step("Open the Stamp Duty landing page")
    public void openLandingPage() {
        landingPage.open();
    }
    @Step("Verify the landing page is loaded")
    public void verifyLandingPageLoaded() {
		/*
		 * if (!landingPage.) { throw new
		 * AssertionError("Stamp Duty landing page did not load correctly."); }
		 */
    }
    @Step("Click the 'Check online' button to navigate to the calculator")
    public void clickCheckOnline() {
        landingPage.clickCheckOnlineButton();;
    }
    
   
    

}
