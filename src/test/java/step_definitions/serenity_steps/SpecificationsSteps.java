package step_definitions.serenity_steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import page_objects.pages.AgePage;
import page_objects.pages.OverseasPage;
import page_objects.pages.PexHomePage;

public class SpecificationsSteps extends ScenarioSteps {

    private PexHomePage pexHomePage;
    private OverseasPage overseasPage;
    private AgePage agePage;

    @Step
    public SpecificationsSteps opensTheService() {
        pexHomePage.openPage();

        return this;
    }

    @Step
    public void navigatesTheFilter(String passportType) throws Exception {
        overseasPage.verifyPageHeader();
        overseasPage.selectIfApplyingFromTheUK(passportType);
        overseasPage.clickNext();

        agePage.verifyPageHeader();
    }
}
