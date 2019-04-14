package page_objects.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import java.time.LocalDate;

import page_objects.BasePage;

public class AgePage extends BasePage {

    private static final String PAGE_HEADER = "Date of birth";

    @FindBy(id = "header") private WebElementFacade agePageHeader;
    @FindBy(id = "date-of-birth-day") private WebElementFacade dobDayField;
    @FindBy(id = "date-of-birth-month") private WebElementFacade dobMonthField;
    @FindBy(id = "date-of-birth-year") private WebElementFacade dobYearField;

    public void verifyPageHeader() throws Exception {
        verifyPageHeader(PAGE_HEADER, agePageHeader);
    }

    private void setDateOfBirthDay (String dateOfBirthDay) {
        enterTextIntoTheField(dobDayField, dateOfBirthDay);
    }

    private void setDateOfBirthMonth (String dateOfBirthMonth) {
        enterTextIntoTheField(dobMonthField, dateOfBirthMonth);
    }

    private void setDateOfBirthYear (String dateOfBirthYear) {
        enterTextIntoTheField(dobYearField, dateOfBirthYear);
    }

    /**
     * Parse an ISO-8601 date and set the values on the DOB page.
     * https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html
     * @param date
     */
    public void setDateOfBirth(String date) {
        LocalDate dob = LocalDate.parse(date);

        setDateOfBirthDay(Integer.toString(dob.getDayOfMonth()));
        setDateOfBirthMonth(Integer.toString(dob.getMonthValue()));
        setDateOfBirthYear(Integer.toString(dob.getYear()));
    }

    public void setDateOfBirth(String dobDay, String dobMonth, String dobYear) {
        setDateOfBirthDay(dobDay);
        setDateOfBirthMonth(dobMonth);
        setDateOfBirthYear(dobYear);
    }
}
