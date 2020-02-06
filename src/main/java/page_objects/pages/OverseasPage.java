package page_objects.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import org.openqa.selenium.support.ui.Select;

import page_objects.BasePage;

public class OverseasPage extends BasePage {

    private static final String PAGE_HEADER = "Do you live in the UK?";

    @FindBy(className = "govuk-fieldset__heading") private WebElementFacade overseasPageHeader;
    @FindBy(id = "is-uk-application-true-label") private WebElementFacade applyFromUkButton;
    @FindBy(id = "is-uk-application-false-label") private WebElementFacade applyFromOverseasButton;
    @FindBy(name = "country-of-application") private WebElementFacade countryOfApplicationSelectDropdown;
    @FindBy(className = "govuk-button") private WebElementFacade nextButton;

    public void verifyPageHeader() {
        verifyPageHeader(PAGE_HEADER, overseasPageHeader);
    }

    public void selectIfApplyingFromTheUK(String passportType) {
        if (passportType.contains("UK")) {
            clickOn(applyFromUkButton);
        }
        else {
            clickOn(applyFromOverseasButton);
            selectCountryOfApplication("FR");
        }
    }

    private void selectCountryOfApplication(String countryCode) {
        Select se = new Select(countryOfApplicationSelectDropdown);
        se.selectByValue(countryCode);
    }

    public void clickNext() {
        clickOnElement(nextButton);
    }
}
