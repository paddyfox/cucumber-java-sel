package page_objects.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import page_objects.BasePage;

public class AgePage extends BasePage {

    private static final String PAGE_HEADER = "Date of birth";

    @FindBy(className = "govuk-fieldset__heading") private WebElementFacade agePageHeader;

    public void verifyPageHeader() {
        verifyPageHeader(PAGE_HEADER, agePageHeader);
    }
}
