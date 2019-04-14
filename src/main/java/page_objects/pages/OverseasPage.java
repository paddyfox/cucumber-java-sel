package page_objects.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import org.openqa.selenium.support.ui.Select;

import page_objects.BasePage;

public class OverseasPage extends BasePage {

    private static final String PAGE_HEADER = "Are you applying from the UK?";

    @FindBy(id = "header") private WebElementFacade filterPageHeader;
    @FindBy(id = "is-uk-application-true") private WebElementFacade applyFromUkButton;
    @FindBy(id = "is-uk-application-false") private WebElementFacade applyFromOverseasButton;
    @FindBy(name = "country-of-application") private WebElementFacade countryOfApplicationSelectDropdown;

    public void verifyPageHeader() throws Exception {
        verifyPageHeader(PAGE_HEADER, filterPageHeader);
    }

    public void clickYesForApplyingFromUk() {
        clickOn(applyFromUkButton);
    }

    public void clickNoForApplyingFromUk() {
        clickOnElement(applyFromOverseasButton);
    }

    public void chooseApplicationLocation() {
        clickYesForApplyingFromUk();
    }

    private void selectCountryOfApplication(String countryCode) {
        Select se = new Select(countryOfApplicationSelectDropdown);
        se.selectByValue(countryCode);
    }
}
