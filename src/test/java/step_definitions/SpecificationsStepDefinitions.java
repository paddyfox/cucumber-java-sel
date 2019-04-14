package step_definitions;

import net.thucydides.core.annotations.Steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import step_definitions.serenity_steps.SpecificationsSteps;
import step_definitions.serenity_steps.TestEnvironmentSteps;

public class SpecificationsStepDefinitions {

    @Steps
    TestEnvironmentSteps testEnvironment;

    @Steps
    SpecificationsSteps theApplicant;

    @Given("^an applicant of type (.*?)$")
    public void anApplicant(String applicantType) throws Throwable {

        /*
          Configure the test environment
         */
        testEnvironment.setupTestEnvironment();
    }

    @When("^they apply for a (.*?) passport$")
    public void theyApplyForAPassport(String passportType) throws Throwable {

        /*
          Enter the applicant data and choices into the website.
         */
        theApplicant
                .opensTheService()
                .navigatesTheFilter(passportType);
    }
}
