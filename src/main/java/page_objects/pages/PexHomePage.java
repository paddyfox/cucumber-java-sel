package page_objects.pages;

import page_objects.BasePage;
import page_objects.OpenablePage;

public class PexHomePage extends BasePage implements OpenablePage {

    @Override
    public String getPagePath() {
        return "/filter";
    }

}
