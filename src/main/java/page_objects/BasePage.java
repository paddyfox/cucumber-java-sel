package page_objects;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Collections;
import java.util.NoSuchElementException;

import static org.openqa.selenium.remote.BrowserType.CHROME;
import static org.openqa.selenium.remote.BrowserType.FIREFOX;
import static org.openqa.selenium.remote.BrowserType.IE;

import static java.util.concurrent.TimeUnit.SECONDS;

public class BasePage extends PageObject {

    private static Logger logger = LogManager.getLogger(BasePage.class);

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

    public boolean isElementPresent(WebElementFacade element) {
        try {
            withTimeoutOf(2, SECONDS).waitFor(element);
            return true;
        } catch (NoSuchElementException | org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    public void clickOnElement(WebElementFacade webElement) {
        withTimeoutOf(120, SECONDS)
                .waitFor(webElement)
                .click();
    }

    public void verifyPageHeader(String expectedPageHeader, WebElementFacade webElementFacade) throws Exception {
        int RETRY_COUNT = 5;

        while (RETRY_COUNT > 0 &&
                (isElementPresent(webElementFacade) || !webElementFacade.getText().equalsIgnoreCase(expectedPageHeader))) {
            RETRY_COUNT--;
            logger.warn("Did not get correct page title: " + expectedPageHeader + ", " + RETRY_COUNT + " tries remaining");
            Thread.sleep(1000);
        }

        if (!isElementPresent(webElementFacade) || !webElementFacade.getText().equalsIgnoreCase(expectedPageHeader)) {
            throw new Exception("Page title: " + expectedPageHeader + " did not appear");
        }
        else {
            logger.info("Loaded page: " + expectedPageHeader);
        }
    }
}
