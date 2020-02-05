package config;

import com.google.common.base.Strings;

import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;

/**
 * Get the URL config from file based on environment variables.
 *
 * The environment variable and the config files can legitimately not exist.
 * If the environment variable doesn't exist or there is an IO
 * exception then log the issue and return early.
 */
public class ProductEnvironmentConfiguration {

    private FileBasedConfiguration configuration;

    private boolean hasConfig = false;

    private final String configDirectory = "src/test/resources/config/";
    private final String configFileNamePattern = "config-XXX.properties";
    private final String environmentReplaceMarker = "XXX";

    // Static method to return current config using default environment where required.
    public static ProductEnvironmentConfiguration getWithDefault() {
        String environmentName = Environment.getName();
        return new ProductEnvironmentConfiguration(environmentName);
    }

    public ProductEnvironmentConfiguration(String environmentName) {
        if (environmentName == null || environmentName.isEmpty()) {
            throw new IllegalArgumentException("Test environment must be specified.");
        }

        Parameters params = new Parameters();

        String configFilePath = configDirectory + configFileNamePattern.replace(environmentReplaceMarker, environmentName);
        File propertiesFile = new File(configFilePath);

        FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                        .configure(params.fileBased()
                                .setFile(propertiesFile));
        try
        {
            configuration = builder.getConfiguration();
            // config contains all properties read from the file
        }
        catch (ConfigurationException cex)
        {
            throw new IllegalStateException("Error loading environment configuration: ", cex);
        }

        // File has been read and properties loaded, set hasConfig flag to true.
        this.hasConfig = true;
    }

    private String getProperty() {
        if (!this.hasConfig) {
            System.out.println("Config for key '" + "appBaseUrl" + "' requested but no config exists.");
            return "";
        }
        String prop = this.configuration.getString("appBaseUrl");
        if (Strings.isNullOrEmpty(prop)) {
            System.out.println("Key  '" + "appBaseUrl" + "' could not be found in config properties object.");
            return "";
        }
        return prop;
    }

    public String getAppBaseUrl() {
        return getProperty();
    }
}
