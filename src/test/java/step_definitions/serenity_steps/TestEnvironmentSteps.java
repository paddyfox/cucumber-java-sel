package step_definitions.serenity_steps;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;

import config.ProductEnvironmentConfiguration;
import page_objects.BasePage;

public class TestEnvironmentSteps extends BasePage {

    private final String ENVIRONMENT = "environment";

    @Step
    public void setupTestEnvironment() throws InterruptedException {

        // Determine the test environment. e.g. local, INT, SIT...
        ProductEnvironmentConfiguration testEnvironment = ProductEnvironmentConfiguration.getWithDefault();

        // Store for future reference
        Serenity.setSessionVariable(ENVIRONMENT).to(testEnvironment);

        // Clear all cookies to prevent any cross browser contamination.
        removeCookies();
    }
}
