package step_definitions.serenity_steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import page_objects.pages.AgePage;
import page_objects.pages.OverseasPage;
import page_objects.pages.LostOrStolenPage;
import page_objects.pages.PexHomePage;
import page_objects.pages.PreviousPassportPage;

import static page_objects.constants.StepDefinitionAssertionConstants.*;

public class SpecificationsSteps extends ScenarioSteps {

    private PexHomePage pexHomePage;
    private OverseasPage overseasPage;
    private PreviousPassportPage previousPassportPage;
    private LostOrStolenPage lostOrStolenPage;
    private AgePage agePage;

    @Step
    public SpecificationsSteps opensTheService() {

        pexHomePage.openPage();

        return this;
    }

    @Step
    public SpecificationsSteps navigatesTheFilter(String passportType) throws Exception {
        overseasPage.verifyPageHeader();
        overseasPage.chooseApplicationLocation();
        overseasPage.next();

        previousPassportPage.verifyPageHeader();
        if (passportType.contains("renewal")) {
            previousPassportPage.clickYesForPreviousBritishPassport();
            previousPassportPage.next();

            lostOrStolenPage.verifyPageHeader();
            lostOrStolenPage.clickNoForLostOrStolenPassport();
            lostOrStolenPage.next();
        }
        else {
            previousPassportPage.clickNoForPreviousBritishPassport();
            previousPassportPage.next();
        }

        agePage.verifyPageHeader();
        if (passportType.contains("adult")) {
            agePage.setDateOfBirth(ADULT_DATE_OF_BIRTH);
        }
        else {
            agePage.setDateOfBirth(CHILD_DATE_OF_BIRTH);
        }
        agePage.next();

        return this;
    }
}
