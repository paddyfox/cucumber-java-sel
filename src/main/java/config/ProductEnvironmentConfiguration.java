package config;

import com.google.common.base.Strings;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.FileConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

/**
 * Get the URL config from file based on environment variables.
 *
 * The environment variable and the config files can legitimately not exist.
 * If the environment variable doesn't exist or there is an IO
 * exception then log the issue and return early.
 */
public class ProductEnvironmentConfiguration {

    private FileConfiguration properties;

    private boolean hasConfig = false;

    private final String configDirectory = "src/test/resources/config/";
    private final String configFileNamePattern = "config-XXX.properties";
    private final String environmentReplaceMarker = "XXX";

    // Static method to return current config using default environment where required.
    public static ProductEnvironmentConfiguration getWithDefault() {
        String environmentName = Environment.getName();
        return new ProductEnvironmentConfiguration(environmentName);
    }

    final static Logger logger = Logger.getLogger(ProductEnvironmentConfiguration.class);

    public ProductEnvironmentConfiguration(String environmentName) {
        if (environmentName == null || environmentName.isEmpty()) {
            throw new IllegalArgumentException("Test environment must be specified.");
        }

        try {
            String configFilePath = configDirectory + configFileNamePattern.replace(environmentReplaceMarker, environmentName);
            properties = new PropertiesConfiguration(configFilePath);
            properties.load();
        } catch (ConfigurationException e) {
            throw new IllegalStateException("Error loading environment configuration: ", e);
        }

        // File has been read and properties loaded, set hasConfig flag to true.
        this.hasConfig = true;
    }

    private String getProperty(String key) {
        if (!this.hasConfig) {
            System.out.println("Config for key '" + key + "' requested but no config exists.");
            return "";
        }
        String prop = this.properties.getString(key);
        if (Strings.isNullOrEmpty(prop)) {
            System.out.println("Key  '" + key + "' could not be found in config properties object.");
            return "";
        }
        return prop;
    }

    public String getAppBaseUrl() {
        return getProperty("appBaseUrl");
    }
}
