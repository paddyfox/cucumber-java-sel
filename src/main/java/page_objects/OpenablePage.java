package page_objects;

import config.ProductEnvironmentConfiguration;

/**
 * Any Page which you want to open should implement this interface to allow it to run on different environments.
 */
public interface OpenablePage {

    String getPagePath();

    default void openPage() {
        openAt(ProductEnvironmentConfiguration.getWithDefault().getAppBaseUrl() + this.getPagePath());
    }

    void openAt(String startingUrl);
}
