package page_objects;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.remote.BrowserType.CHROME;
import static org.openqa.selenium.remote.BrowserType.FIREFOX;
import static org.openqa.selenium.remote.BrowserType.IE;

import static java.util.concurrent.TimeUnit.SECONDS;

public class BasePage extends PageObject {

    public void removeCookies() {
        getDriver().manage().deleteAllCookies();
    }

    //TODO: Correctly configure DesiredCapabilities options
    public static DesiredCapabilities getBrowserCapabilities(String driverParameter, boolean headless) {
        DesiredCapabilities capabilities = null;
        if (driverParameter == null || driverParameter.equalsIgnoreCase(FIREFOX)) {
            capabilities = DesiredCapabilities.firefox();
            FirefoxOptions options = new FirefoxOptions();
            options.setHeadless(headless);
            capabilities.merge(options);
        }
        else if (driverParameter.equalsIgnoreCase(IE)) {
            capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        }
        else if (driverParameter.equalsIgnoreCase(CHROME)) {
            capabilities = DesiredCapabilities.chrome();
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(headless);
            options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
            capabilities.merge(options);
        }
        return capabilities;
    }

    public void clickOnElement(WebElementFacade webElement) {
        withTimeoutOf(120, SECONDS)
                .waitFor(webElement)
                .click();
    }

    public void verifyPageHeader(String expectedPageHeader, WebElementFacade webElementFacade) {
        assertEquals(webElementFacade.getText(), expectedPageHeader);
        }
    }
