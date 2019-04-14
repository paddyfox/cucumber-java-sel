package page_objects;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import config.ProductEnvironmentConfiguration;

import static java.util.concurrent.TimeUnit.SECONDS;

public class BasePage extends PageObject {

    @FindBy(id = "submit-button") private WebElementFacade submitButton;

    public final String APP_BASE_URL = ProductEnvironmentConfiguration.getWithDefault().getAppBaseUrl();

    public void closeCurrentTab() {
        ArrayList<String> windowHandles = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(windowHandles.get(1));
        getDriver().close();
        getDriver().switchTo().window(windowHandles.get(0));
    }

    public void navigateBackOnce() {
        getDriver().navigate().back();
    }

    public String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }

    public void removeCookies() throws InterruptedException {
        getDriver().manage().deleteAllCookies();
        Thread.sleep(1000);
    }

    // Convenience functions to allow old code to work.
    public WebElementFacade createPageElementWithCSSPath(String locator) {
        return findElement(locator);
    }

    private WebElementFacade findElement(String locator) {
        return withTimeoutOf(30, SECONDS).find(locator);
    }

    public boolean isElementPresent(WebElementFacade element) {
        try {
            withTimeoutOf(2, SECONDS).waitFor(element);
            return true;
        } catch (NoSuchElementException | org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    public void next() {
        clickOnElement(submitButton);
    }

    public void clickOnButton(String locator) {
        withTimeoutOf(120, SECONDS)
                .find(locator)
                .waitUntilClickable()
                .click();
    }

    public void clickOnElement(WebElementFacade webElement) {
        withTimeoutOf(120, SECONDS)
                .waitFor(webElement)
                .click();
    }

    public String getTextValueOf(WebElementFacade webElement) {
        return withTimeoutOf(120, SECONDS)
                .waitFor(webElement)
                .getText();
    }

    public void clickOnButtonWithXpath(String locator) {
        clickOnButton(locator);
    }

    public String getText(String locator) {
        WebElementFacade el = $(locator);
        el.waitUntilPresent();
        return el.getText();
    }

    public String getValue(String locator) {
        WebElementFacade el = $(locator);
        el.waitUntilPresent();
        return el.getAttribute("value");
    }

    public String getHeader(String locator) {
        return getText(locator);
    }

    public void enterTextIntoTheField(WebElementFacade webElement, String value) {
        WebElementFacade el = $(webElement);
        el.waitUntilClickable();
        el.clear();
        el.sendKeys(value);
    }

    public String getImageSource(String locator, String property) {
        WebElementFacade el = createPageElementWithCSSPath(locator);
        return el.getAttribute(property);
    }

    public void selectValueFromDropdown(WebElementFacade webElement, String value) {
        WebElementFacade el = $(webElement);
        el.selectByVisibleText(value);
    }

    public void pageRefresh() {
        getDriver().navigate().refresh();
    }

    public void verifyPageHeader(String expectedPageHeader, WebElementFacade webElementFacade) throws Exception {
        int RETRY_COUNT = 5;

        while (RETRY_COUNT > 0 && (!isElementPresent(webElementFacade) || !webElementFacade.getText().equalsIgnoreCase(expectedPageHeader))) {
            RETRY_COUNT--;
            System.out.println("WARNING: Did not get correct page title, expected: " + expectedPageHeader + ", but got: " + webElementFacade.getText());
            Thread.sleep(5000);
        }

        if (!webElementFacade.getText().equalsIgnoreCase(expectedPageHeader)) {
            throw new Exception("ERROR: Did not get correct page title, expected: " + expectedPageHeader + ", but got: " + webElementFacade.getText());
        }
    }

    public void verifyTrackingPageStatusHeader(String expectedPageHeader, WebElementFacade webElementFacade) throws Exception {
        int RETRY_COUNT = 10;

        while (RETRY_COUNT > 0 && (!isElementPresent(webElementFacade) || !webElementFacade.getText().equalsIgnoreCase(expectedPageHeader))) {
            RETRY_COUNT--;
            System.out.println("WARNING: Did not get correct page title, expected: " + expectedPageHeader + ", but got: " + webElementFacade.getText());
            Thread.sleep(5000);
            pageRefresh();
        }

        if (!webElementFacade.getText().equalsIgnoreCase(expectedPageHeader)) {
            throw new Exception("ERROR: Did not get correct page title, expected: " + expectedPageHeader + ", but got: " + webElementFacade.getText());
        }
    }

    //THIS SWITCHES TO NEWLY OPENED TAB
    public void switchToNewTab() throws InterruptedException {
        WebDriver driver = getDriver();
        Thread.sleep(5000);
        String currentWindowHandle = driver.getWindowHandle();
        Set s = driver.getWindowHandles();
        Iterator ite = s.iterator();
        String handle;

        while (ite.hasNext()) {
            handle = ite.next().toString();
            if (handle.equals(currentWindowHandle)) break;
        }
        handle = ite.next().toString();

        driver.switchTo().window(handle);
    }

    //THIS SWITCHES BETWEEN TWO TABS
    public void switchBetweenTwoTabs() {
        ArrayList tabs = new ArrayList(getDriver().getWindowHandles());
        getDriver().switchTo().window((String) tabs.get(0));
        getDriver().findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"\t");
        getDriver().switchTo().defaultContent();
    }
}
