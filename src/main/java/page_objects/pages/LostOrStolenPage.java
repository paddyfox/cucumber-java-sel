package page_objects.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import page_objects.BasePage;

public class LostOrStolenPage extends BasePage {

    private static final String PAGE_HEADER = "Is your passport lost or stolen?";

    @FindBy(id = "header") private WebElementFacade lostOrStolenPageHeader;
    @FindBy(id= "passport-lost-true") private WebElementFacade lostStolenPassportTrueButton;
    @FindBy(id= "passport-lost-false") private WebElementFacade lostStolenPassportFalseButton;

    public void verifyPageHeader() throws Exception {
        verifyPageHeader(PAGE_HEADER, lostOrStolenPageHeader);
    }

    public void clickYesForLostOrStolenPassport() {
        clickOnElement(lostStolenPassportTrueButton);
    }

    public void clickNoForLostOrStolenPassport() {
        clickOnElement(lostStolenPassportFalseButton);
    }
}
