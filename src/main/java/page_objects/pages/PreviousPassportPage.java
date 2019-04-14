package page_objects.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import page_objects.BasePage;

public class PreviousPassportPage extends BasePage {

    private static final String PAGE_HEADER = "Have you had a UK passport before?";

    @FindBy(id = "header") private WebElementFacade previousPassportPageHeader;
    @FindBy(id= "previous-passport-true") private WebElementFacade previousPassportTrueButton;
    @FindBy(id= "previous-passport-false") private WebElementFacade previousPassportFalseButton;

    public void verifyPageHeader() throws Exception {
        verifyPageHeader(PAGE_HEADER, previousPassportPageHeader);
    }

    public void clickNoForPreviousBritishPassport() {
        clickOnElement(previousPassportFalseButton);
    }

    public void clickYesForPreviousBritishPassport() {
        clickOnElement(previousPassportTrueButton);
    }
}
